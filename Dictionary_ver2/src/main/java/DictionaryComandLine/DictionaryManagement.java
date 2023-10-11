package DictionaryComandLine;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DictionaryManagement {
    private final static String URL = "jdbc:sqlite:./src/main/resources/data/dict_hh.db";

    public static boolean dbHasWord(String word, String table) {
        Connection con = null;
        Statement statement = null;
        int cnt = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(URL);
            statement = con.createStatement();
            String sql = "SELECT COUNT(word) FROM " + table + " WHERE word = '" + word +"'";
            ResultSet res = statement.executeQuery(sql);
            cnt = res.getInt(1);

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
            String sql_query = "SELECT * FROM " + table + "WHERE word LIKE " + word_target + " ORDER BY word";

            ResultSet res = statement.executeQuery(sql_query);
            while(res.next()) {
                Word word = new Word(res.getString("word"),
                        res.getString("description"),
                        res.getString("pronounce")
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

    public static void dbAdd(String word, String description, String pronounce, String table) {
        Connection con = null;
        Statement statement = null;
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(URL);
            con.setAutoCommit(false);
            statement = con.createStatement();

            String sql = "INSERT INTO " + table + " (word, pronounce, description) values ('" + word + "', '"
                    + pronounce + "', '"
                    + description + "')";
            statement.executeUpdate(sql);
            statement.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
