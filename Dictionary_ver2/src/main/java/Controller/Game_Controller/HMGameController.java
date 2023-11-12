package Controller.Game_Controller;

//import Controller.Game_Controller.GameHM;
import Controller.Game_Controller.GameHM;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class HMGameController implements Initializable {
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

    private GameHM game;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        keyWord.setEditable(false);
        keyWord.setText(String.copyValueOf(game.getSecretWord()));
        base.setVisible(true);
        vertical.setVisible(true);
        horizontal.setVisible(true);
        holder.setVisible(true);
        head.setVisible(false);
        leftArm.setVisible(false);
        rightArm.setVisible(false);
        body.setVisible(false);
        leftLeg.setVisible(false);
        rightLeg.setVisible(false);
        game = new GameHM();
        game.setHangManLives(6);
    }

    // update images for hangman body parts
    private void updateImage(int lives){
        if (lives == 5 ){
            head.setVisible(true);
        }
        if (lives == 4){
            body.setVisible(true);
        }
        if (lives == 3) {
            rightArm.setVisible(true);
        }
        if (lives == 2) {
            rightLeg.setVisible(true);
        }
        if (lives == 1) {
            leftArm.setVisible(true);
        }
        if (lives == 0) {
            leftLeg.setVisible(true);
        }
    }

    public void onActionLetterClick(ActionEvent actionEvent) {
        Button selectLetter = (Button) actionEvent.getSource();
        String input = selectLetter.getText();
        String newWord = game.letterMatch(input.charAt(0));
        keyWord.setText(newWord);
        updateImage(game.getHangManLives());

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5),selectLetter);
        selectLetter.setOpacity(1);
        fadeTransition.setToValue(0);

        fadeTransition.play();
    }

}
