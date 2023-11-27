package Controller.Game_Controller;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HangManGameController implements Initializable {
    @FXML
    private AnchorPane hangManContainer;
    @FXML
    private TextField keyWord;
    @FXML
    private Label lives;
    @FXML
    private Line base;
    @FXML
    private Line vertical;
    @FXML
    private Line  horizontal;
    @FXML
    private Line  upperCorner;
    @FXML
    private Line  bottomCorner;
    @FXML
    private Line holder;
    @FXML
    private Circle head;
    @FXML
    private Line leftArm;
    @FXML
    private Line rightArm;
    @FXML
    private Line body;
    @FXML
    private Line leftLeg;
    @FXML
    private Line rightLeg;

    private GameHangMan gameHangMan;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        keyWord.setEditable(false);
        keyWord.setText(String.copyValueOf(gameHangMan.getSecretWord()));
        base.setVisible(true);
        vertical.setVisible(true);
        horizontal.setVisible(true);
        upperCorner.setVisible(true);
        bottomCorner.setVisible(true);
        holder.setVisible(true);
        head.setVisible(false);
        leftArm.setVisible(false);
        rightArm.setVisible(false);
        body.setVisible(false);
        leftLeg.setVisible(false);
        rightLeg.setVisible(false);
        gameHangMan = new GameHangMan();
        gameHangMan.setHangManLives(6);
        lives.setText("6");
    }

    private void showComponent(String path) {
        try {
            BorderPane component = FXMLLoader.load(getClass().getResource(path));
            hangManContainer.getChildren().clear();
            hangManContainer.getChildren().add(component);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateImage(int hangManLives){
        if (hangManLives == 5 ){
            head.setVisible(true);
        }
        if (hangManLives == 4){
            body.setVisible(true);
        }
        if (hangManLives == 3) {
            leftArm.setVisible(true);
        }
        if (hangManLives == 2) {
            rightArm.setVisible(true);
        }
        if (hangManLives == 1) {
            leftLeg.setVisible(true);
        }
        if (hangManLives == 0) {
            rightLeg.setVisible(true);
        }
        lives.setText(String.valueOf(hangManLives));
    }

    public void onActionLetterClick(ActionEvent actionEvent) {
        Button selectLetter = (Button) actionEvent.getSource();
        String input = selectLetter.getId();
        String newWord = gameHangMan.letterMatch(input.charAt(0));
        if(gameHangMan.getHangManLives() == 0) {
            showComponent("/fxml/LoseHangManGame.fxml");
        }
        if(GameHangMan.getRandomWord().equals(String.valueOf(GameHangMan.getSecretWord()))){
            showComponent("/fxml/WinHangManGame.fxml");
        }
        keyWord.setText(newWord);
        updateImage(gameHangMan.getHangManLives());

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5),selectLetter);
        selectLetter.setOpacity(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();

        selectLetter.setDisable(true);
    }

    public void onActionHomeHangMan() {
        showComponent("/fxml/MenuHangManGame.fxml");
        gameHangMan.getWords().clear();
    }

}
