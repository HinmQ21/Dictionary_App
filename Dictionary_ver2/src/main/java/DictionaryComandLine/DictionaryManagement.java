package DictionaryComandLine;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class DictionaryManagement {
    private final static String URL = "jdbc:sqlite:./src/main/resources/data/dict_hh.db";

    private static final Map<String, Word> data = new HashMap<String, Word>();


    public static Map<String, Word> getData() {
        return data;
    }

    public static void readData (String dataTable) {
        data.clear();
        Connection con = null;
        Statement statement = null;
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(URL);
            con.setAutoCommit(false);
            statement = con.createStatement();

            String sql_query = "SELECT * FROM " + dataTable + " ORDER BY word";
            ResultSet res = statement.executeQuery(sql_query);
            while(res.next()) {
                Word word = new Word(res.getString("word"),
                        res.getString("html")
                );
                data.put(word.getWord_target(), word);
            }

            statement.close();
            con.close();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static boolean dbHasWord(String word, String table) {
        Connection con = null;
        Statement statement = null;
        int cnt = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(URL);
            con.setAutoCommit(false);
            statement = con.createStatement();
            String sql = "SELECT COUNT(word) FROM " + table + " WHERE word = '" + word +"'";
            ResultSet res = statement.executeQuery(sql);
            cnt = res.getInt(1);
            res.close();
            statement.close();
            con.close();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return cnt != 0;
    }

    public static ObservableList<String> dbSearch(String word_target, String table) {
        ObservableList<String> list = FXCollections.observableArrayList();
        Connection con = null;
        Statement statement = null;

        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(URL);
            con.setAutoCommit(false);

            statement = con.createStatement();
            String sql_query = "SELECT * FROM " + table + " WHERE word LIKE " + word_target + " ORDER BY word";

            ResultSet res = statement.executeQuery(sql_query);
            while(res.next()) {
                Word word = new Word(res.getString("word"),
                        res.getString("html")
                );
                list.add(word.getWord_target());
            }
            res.close();
            statement.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public static void dbAdd(String word, String pronounce, String description, String table) {
        Connection con = null;
        Statement statement = null;
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(URL);
            statement = con.createStatement();

            String h1 = "<h1>" + word + "</h1>";
            String h3 = "<h3> // </h3>";
            description = h1 + h3 + "<ul>" + description + "</ul>";

            String sql = "INSERT INTO " + table + " (word, pronounce, html) values ('" + word + "', '"
                    + pronounce + "', '"
                    + description + "')";
            statement.executeUpdate(sql);
            statement.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void dbDelete(String word, String table) {
        Connection con = null;
        Statement statement = null;
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(URL);
            statement = con.createStatement();

            String sql = "DELETE FROM " + table + " WHERE word = '" + word +"'";
            statement.executeUpdate(sql);
            data.remove(word);
            statement.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void dbUpdate(String word, String new_def, String table) {
        Connection con = null;
        Statement statement = null;
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(URL);
            statement = con.createStatement();

            String h1 = "<h1>" + word + "</h1>";
            String h3 = "<h3> // </h3>";
            new_def = h1 + h3 + "<ul>" + new_def + "</ul>";

            String sql = "UPDATE " + table + " set html = '" +new_def
                    + "' WHERE word = " + "'"+ word + "'";
            statement.executeUpdate(sql);
            data.put(word, new Word(word, new_def));

            statement.close();
            con.close();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void TextToSpeech(String word, String language) {
        String apiKey = "946fd1d2d4814f1c8b946cfa011ea466";

        try {
            String apiUrl = "http://api.voicerss.org/?";
            String apiKeyParam = "key=" + URLEncoder.encode(apiKey, StandardCharsets.UTF_8);
            String textParam = "src=" + URLEncoder.encode(word, StandardCharsets.UTF_8);
            String langParam = language;

            URL url = new URL(apiUrl + apiKeyParam + "&" + textParam + "&" + langParam);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();

            if (responseCode == 200) {
                InputStream inputStream = conn.getInputStream();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bufferedInputStream);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();

                Thread.sleep(clip.getMicrosecondLength() / 500);

                audioInputStream.close();
                bufferedInputStream.close();
                inputStream.close();
            }

        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String translate(String langFrom, String langTo, String text) throws IOException {
        String urlStr = "https://script.google.com/macros/s/AKfycbyXtVyrIooqNlmO-MZniN7nexchzL_IVP9508e-GeEkWHwCIeT19x1iXkx7Qju9y-ps/exec" +
                "?q=" + URLEncoder.encode(text, StandardCharsets.UTF_8) +
                "&target=" + langTo +
                "&source=" + langFrom;
        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
}
