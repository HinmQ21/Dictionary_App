package Controller.Game_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MenuHangManGameController {
    @FXML
    AnchorPane hangManContainer;
    @FXML
    private Button animals;
    @FXML
    private Button body;
    @FXML
    private Button clothes;
    @FXML
    private Button colors;
    @FXML
    private Button countries;
    @FXML
    private Button food;
    @FXML
    private Button home;
    @FXML
    private Button jobs;
    @FXML
    private Button movies;
    @FXML
    private Button music;
    @FXML
    private Button numbers;
    @FXML
    private Button personal;
    @FXML
    private Button sports;
    @FXML
    private Button subjects;
    @FXML
    private Button transport;

    public static GameHangMan gameHangMan = new GameHangMan();

    private void showComponent(String path) {
        try {
            BorderPane component = FXMLLoader.load(getClass().getResource(path));
            hangManContainer.getChildren().clear();
            hangManContainer.getChildren().add(component);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onActionChooseGenre(ActionEvent actionEvent) {
        if (actionEvent.getSource() == animals) {
            gameHangMan.readFiles("src/main/resources/Ultis/txts/animals.txt");
            gameHangMan.setRandomWord(gameHangMan.chooseRandomWords());
        }
        else if (actionEvent.getSource() == body) {
            gameHangMan.readFiles("src/main/resources/Ultis/txts/body.txt");
            gameHangMan.setRandomWord(gameHangMan.chooseRandomWords());
        }
        else if (actionEvent.getSource() == clothes) {
            gameHangMan.readFiles("src/main/resources/Ultis/txts/clothes.txt");
            gameHangMan.setRandomWord(gameHangMan.chooseRandomWords());
        }
        else if (actionEvent.getSource() == colors) {
            gameHangMan.readFiles("src/main/resources/Ultis/txts/colors.txt");
            gameHangMan.setRandomWord(gameHangMan.chooseRandomWords());
        }
        else if (actionEvent.getSource() == countries) {
            gameHangMan.readFiles("src/main/resources/Ultis/txts/countries.txt");
            gameHangMan.setRandomWord(gameHangMan.chooseRandomWords());
        }
        else if (actionEvent.getSource() == food) {
            gameHangMan.readFiles("src/main/resources/Ultis/txts/food.txt");
            gameHangMan.setRandomWord(gameHangMan.chooseRandomWords());
        }
        else if (actionEvent.getSource() == home) {
            gameHangMan.readFiles("src/main/resources/Ultis/txts/home.txt");
            gameHangMan.setRandomWord(gameHangMan.chooseRandomWords());
        }
        else if (actionEvent.getSource() == jobs) {
            gameHangMan.readFiles("src/main/resources/Ultis/txts/jobs.txt");
            gameHangMan.setRandomWord(gameHangMan.chooseRandomWords());
        }
        else if (actionEvent.getSource() == movies) {
            gameHangMan.readFiles("src/main/resources/Ultis/txts/movies.txt");
            gameHangMan.setRandomWord(gameHangMan.chooseRandomWords());
        }
        else if (actionEvent.getSource() == music) {
            gameHangMan.readFiles("src/main/resources/Ultis/txts/music.txt");
            gameHangMan.setRandomWord(gameHangMan.chooseRandomWords());
        }
        else if (actionEvent.getSource() == numbers) {
            gameHangMan.readFiles("src/main/resources/Ultis/txts/numbers.txt");
            gameHangMan.setRandomWord(gameHangMan.chooseRandomWords());
        }
        else if (actionEvent.getSource() == personal) {
            gameHangMan.readFiles("src/main/resources/Ultis/txts/personal.txt");
            gameHangMan.setRandomWord(gameHangMan.chooseRandomWords());
        }
        else if (actionEvent.getSource() == sports) {
            gameHangMan.readFiles("src/main/resources/Ultis/txts/sports.txt");
            gameHangMan.setRandomWord(gameHangMan.chooseRandomWords());
        }
        else if (actionEvent.getSource() == subjects) {
            gameHangMan.readFiles("src/main/resources/Ultis/txts/subjects.txt");
            gameHangMan.setRandomWord(gameHangMan.chooseRandomWords());
        }
        else if (actionEvent.getSource() == transport) {
            gameHangMan.readFiles("src/main/resources/Ultis/txts/transport.txt");
            gameHangMan.setRandomWord(gameHangMan.chooseRandomWords());
        }
        System.out.println(gameHangMan.getRandomWord());
        showComponent("/fxml/HangManGame.fxml");
    }
}
