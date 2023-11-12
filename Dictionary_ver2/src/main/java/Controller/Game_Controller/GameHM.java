package Controller.Game_Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameHM {
    @FXML
    public BorderPane HMGame;

    private BufferedReader bufferedReader;
    private static String randomWord ;
    private static List<String> words = new ArrayList<>();
    private static char[] secretWord;
    private int hangManLives;
    public static int found;
    //private BorderPane container;

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
        GameHM.randomWord = randomWord;
    }

    public static List<String> getWords() {
        return words;
    }

    public static void setWords(List<String> words) {
        GameHM.words = words;
    }

    public static char[] getSecretWord() {
        return secretWord;
    }
    public static void setSecretWord(char[] secretWord) {
        GameHM.secretWord = secretWord;
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
        if(newWord.equals(randomWord)){
            System.out.println("You win");
            gameWin();
        }
        if(check == true) {
            hangManLives--;
        }
        if(hangManLives == 0) {
            System.out.println("You lose");
            gameOver();
        }
        secretWord = newWord.toCharArray();
        System.out.println(randomWord);
        System.out.println(newWord);
        System.out.println(secretWord);
        return newWord;
    }

    public void gameWin () {
        try {
            BorderPane component = FXMLLoader.load(getClass().getResource("/fxml/WinHangManGame.fxml"));
            HMGame.getChildren().clear();
            HMGame.getChildren().add(component);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gameOver () {
        try {
            BorderPane component = FXMLLoader.load(getClass().getResource("/fxml/LoseHangManGame.fxml"));
            HMGame.getChildren().clear();
            HMGame.getChildren().add(component);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
