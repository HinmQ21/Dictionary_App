package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class GameController {

    @FXML
    AnchorPane gameContainer;

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

    public void onActionStartHangMan() {

    }
}
