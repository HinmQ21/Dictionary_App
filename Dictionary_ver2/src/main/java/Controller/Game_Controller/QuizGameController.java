package Controller.Game_Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class QuizGameController {
    @FXML
    AnchorPane quizContainer;

    private void showComponent(String path) {
        try {
            BorderPane component = FXMLLoader.load(getClass().getResource(path));
            quizContainer.getChildren().clear();
            quizContainer.getChildren().add(component);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onActionStartMulti() {
        showComponent("/fxml/multichoiceGame.fxml");
    }

}
