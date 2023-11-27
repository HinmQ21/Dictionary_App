package Controller.Game_Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.awt.*;
import java.io.IOException;

public class WinHangManGame {

    private GameHangMan gameHangMan = new GameHangMan();
    @FXML
    AnchorPane hangManContainer;
    public void onActionPlayAgain() {
        try {
            BorderPane component = FXMLLoader.load(getClass().getResource("/fxml/MenuHangManGame.fxml"));
            hangManContainer.getChildren().clear();
            hangManContainer.getChildren().add(component);
        } catch (IOException e) {
            e.printStackTrace();
        }
        gameHangMan.getWords().clear();
    }
}
