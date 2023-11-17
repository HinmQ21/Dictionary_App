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
    //public AnchorPane hangManContainer = HangManGameController.hangManContainer;
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
        if(newWord.equals(randomWord)){
            System.out.println("You win");
            //gameWin();
        }
        if(check == true) {
            hangManLives--;
        }
        if(hangManLives == 0) {
            System.out.println("You lose");
            //gameOver();
        }
        secretWord = newWord.toCharArray();
        System.out.println(randomWord);
        System.out.println(newWord);
        System.out.println(secretWord);
        return newWord;
    }
    /*public void gameWin () {
        try {
            BorderPane component = FXMLLoader.load(getClass().getResource("/fxml/WinHangManGame.fxml"));
            hangManContainer.getChildren().clear();
            hangManContainer.getChildren().add(component);
            *//*FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/WinHangManGame.fxml"));
            Parent root = loader.load();

            // Nếu có logic cần thực hiện trên controller của Màn hình Chiến thắng, bạn có thể lấy nó như sau:
            WinHangManGame winHangManGame = loader.getController();

            Stage winScreenStage = new Stage();
            winScreenStage.setScene(new Scene(root));
            winScreenStage.show();*//*
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gameOver () {
        try {
            *//*AnchorPane component = FXMLLoader.load(getClass().getResource("/fxml/LoseHangManGame.fxml"));
//            hangManContainer.getChildren().clear();
//            hangManContainer.getChildren().add(component);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/HangManGame.fxml"));
            //Parent root = loader.load();
            HangManGameController hangManGameController = loader.getController();
            hangManGameController.setHangManContainer(new AnchorPane());
            hangManGameController.getHangManContainer().getChildren().clear();
            hangManGameController.getHangManContainer().getChildren().add(component);*//*
            BorderPane component = FXMLLoader.load(getClass().getResource("/fxml/LoseHangManGame.fxml"));
            hangManContainer.getChildren().clear();
            hangManContainer.getChildren().add(component);
            *//*FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LoseHangManGame.fxml"));
            Parent root = loader.load();

            // Nếu có logic cần thực hiện trên controller của Màn hình Chiến thắng, bạn có thể lấy nó như sau:
            LoseHangManGame loseHangManGame = loader.getController();

            Stage lose = new Stage();
            lose.setScene(new Scene(root));
            lose.show();*//*
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
