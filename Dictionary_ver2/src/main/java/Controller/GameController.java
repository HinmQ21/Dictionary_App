package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import Controller.Game_Controller.GameHangMan;

import java.io.IOException;

public class GameController {

    @FXML
    AnchorPane gameContainer;

    public GameHangMan gameHangMan = new GameHangMan();

    private void showComponent(String path) {
        try {
            BorderPane component = FXMLLoader.load(getClass().getResource(path));
            gameContainer.getChildren().clear();
            gameContainer.getChildren().add(component);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onActionStartQuizGame() {
        showComponent("/fxml/QuizGame.fxml");
    }

    public void onActionStartHangManGame() {
        showComponent("/fxml/MenuHangManGame.fxml");
        gameHangMan.getWords().clear();
    }
}
