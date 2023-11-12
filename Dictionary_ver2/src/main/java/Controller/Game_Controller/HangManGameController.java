package Controller.Game_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class HangManGameController {
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

    public static GameHM game = new GameHM();

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
            game.readFiles("src/main/resources/Ultis/txts/animals.txt");
            game.setRandomWord(game.chooseRandomWords());
        }
        else if (actionEvent.getSource() == body) {
            game.readFiles("src/main/resources/Ultis/txts/body.txt");
            game.setRandomWord(game.chooseRandomWords());
        }
        else if (actionEvent.getSource() == clothes) {
            game.readFiles("src/main/resources/Ultis/txts/clothes.txt");
            game.setRandomWord(game.chooseRandomWords());
        }
        else if (actionEvent.getSource() == colors) {
            game.readFiles("src/main/resources/Ultis/txts/colors.txt");
            game.setRandomWord(game.chooseRandomWords());
        }
        else if (actionEvent.getSource() == countries) {
            game.readFiles("src/main/resources/Ultis/txts/countries.txt");
            game.setRandomWord(game.chooseRandomWords());
        }
        else if (actionEvent.getSource() == food) {
            game.readFiles("src/main/resources/Ultis/txts/food.txt");
            game.setRandomWord(game.chooseRandomWords());
        }
        else if (actionEvent.getSource() == home) {
            game.readFiles("src/main/resources/Ultis/txts/home.txt");
            game.setRandomWord(game.chooseRandomWords());
        }
        else if (actionEvent.getSource() == jobs) {
            game.readFiles("src/main/resources/Ultis/txts/jobs.txt");
            game.setRandomWord(game.chooseRandomWords());
        }
        else if (actionEvent.getSource() == movies) {
            game.readFiles("src/main/resources/Ultis/txts/movies.txt");
            game.setRandomWord(game.chooseRandomWords());
        }
        else if (actionEvent.getSource() == music) {
            game.readFiles("src/main/resources/Ultis/txts/music.txt");
            game.setRandomWord(game.chooseRandomWords());
        }
        else if (actionEvent.getSource() == numbers) {
            game.readFiles("src/main/resources/Ultis/txts/numbers.txt");
            game.setRandomWord(game.chooseRandomWords());
        }
        else if (actionEvent.getSource() == personal) {
            game.readFiles("src/main/resources/Ultis/txts/personal.txt");
            game.setRandomWord(game.chooseRandomWords());
        }
        else if (actionEvent.getSource() == sports) {
            game.readFiles("src/main/resources/Ultis/txts/sports.txt");
            game.setRandomWord(game.chooseRandomWords());
        }
        else if (actionEvent.getSource() == subjects) {
            game.readFiles("src/main/resources/Ultis/txts/subjects.txt");
            game.setRandomWord(game.chooseRandomWords());
        }
        else if (actionEvent.getSource() == transport) {
            game.readFiles("src/main/resources/Ultis/txts/transport.txt");
            game.setRandomWord(game.chooseRandomWords());
        }
        System.out.println(game.getRandomWord());
        showComponent("/fxml/HMGame.fxml");
    }
}
