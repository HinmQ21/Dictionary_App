package Controller.Game_Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class GameHangMan {
    private BufferedReader bufferedReader;
    private static String randomWord ;
    private static List<String> words = new ArrayList<>();
    private static char[] secretWord;
    private int hangManLives;
    public static int found;

    public int getHangManLives() {
        return hangManLives;
    }

    public void setHangManLives(int hangManLives) {
        this.hangManLives = hangManLives;
    }

    public static String getRandomWord() {
        return randomWord;
    }

    public static void setRandomWord(String randomWord) {
        GameHangMan.randomWord = randomWord;
    }

    public static List<String> getWords() {
        return words;
    }

    public static void setWords(List<String> words) {
        GameHangMan.words = words;
    }

    public static char[] getSecretWord() {
        return secretWord;
    }
    public static void setSecretWord(char[] secretWord) {
        GameHangMan.secretWord = secretWord;
    }




    public void readFiles (String fileName)  {
        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));
            String word ;
            while((word = bufferedReader.readLine()) != null) {
                if (word.length()!=0) {
                    words.add(word.toLowerCase());
                }
            }
            bufferedReader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String chooseRandomWords() {
        int check = 0;
        Random random = new Random();
        int randomNumber = random.nextInt(words.size());
        randomWord = words.get(randomNumber);
        secretWord = randomWord.toCharArray();
        for (int i = 0; i < randomWord.length(); i++){
            if(randomWord.charAt(i)!=' ') {
                secretWord[i] = '-';
            }
            else {
                secretWord[i] = ' ';
                check++;
            }
        }
        found = check;

        return randomWord;
    }

    public String letterMatch(char letterClick) {
        System.out.println(letterClick);
        String newWord = "";
        boolean check = true;
        for (int i=0; i <randomWord.length();i++) {
            if(randomWord.charAt(i) == letterClick) {
                newWord += letterClick;
                check = false;
            }
            else {
                newWord += secretWord[i];
            }
        }
        if(check == true) {
            hangManLives--;
        }
        secretWord = newWord.toCharArray();
        return newWord;
    }
}
