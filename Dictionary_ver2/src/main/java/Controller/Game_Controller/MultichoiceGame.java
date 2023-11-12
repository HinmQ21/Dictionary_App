package Controller.Game_Controller;


import QuizGame.MultiChoiceQuestion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import javafx.fxml.Initializable;
import javafx.scene.layout.Region;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MultichoiceGame implements Initializable{
    @FXML
    private AnchorPane quizContainer;

    @FXML
    private Label quesNum;

    @FXML
    private Label quesText;

    @FXML
    private Label scoreBoard;

    @FXML
    private Button butA, butB, butC, butD;

    private int curQuestion, curScore;

    public void onActionHome() {
        try {
            BorderPane component = FXMLLoader.load(getClass().getResource("/fxml/QuizGame.fxml"));
            quizContainer.getChildren().clear();
            quizContainer.getChildren().add(component);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private MultiChoiceQuestion[] quesList = {
            new MultiChoiceQuestion("The weather is so _______ that we decided to have a picnic in the park.", new String[]{"good", "sunny", "well", "fine"}, 1),
            new MultiChoiceQuestion("She is allergic _______ cats.", new String[]{"on", "with", "to", "for"}, 2),
            new MultiChoiceQuestion("Yesterday, she _______ to the store and _______ some groceries.", new String[]{"go / buy", "went / buy", "goes / buys", "gone / buying"}, 1),
            new MultiChoiceQuestion("The chef prepared a delicious **exquisite** meal.", new String[]{"ordinary", "splendid", "simple", "complex"}, 1),
            new MultiChoiceQuestion("You _______ finish your homework before going out to play.", new String[]{"can", "should", "might", "must"}, 3),
            new MultiChoiceQuestion("This book is more interesting, but that one is _______.", new String[]{"interesting", "interestinger", "interested", "more interesting"}, 3),
            new MultiChoiceQuestion("By the time we get to the cinema, the movie _______.", new String[]{"will start", "started", "starts", "will have started"}, 3),
            new MultiChoiceQuestion("I have two _______.", new String[]{"child", "childs", "childes", "children"}, 3),
            new MultiChoiceQuestion("her / birthday / we / a surprise / gave", new String[]{"We gave a surprise her birthday.", "We gave her a surprise birthday.", "A surprise we gave her birthday.", "Her birthday gave we a surprise."}, 1),
            new MultiChoiceQuestion("She loves to read, _______ she also enjoys watching movies.", new String[]{"or", "so", "but", "because"}, 2)
    };



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        curQuestion = 0;
        curScore = 0;
        displayQuestion(curQuestion);
    }

    private void displayQuestion(int th) {
        quesNum.setText("Question : " + (curQuestion + 1) + "/10");
        scoreBoard.setText(curScore + "/100");
        quesText.setText(quesList[th].getQuestionText());
        quesText.setMinHeight(Region.USE_PREF_SIZE);
        butA.setText(quesList[th].getOpts()[0]);
        butB.setText(quesList[th].getOpts()[1]);
        butC.setText(quesList[th].getOpts()[2]);
        butD.setText(quesList[th].getOpts()[3]);
    }

    public void onActionHandleAns(ActionEvent event) {
        Button selectedButton = (Button) event.getSource();
        String selectedAnswer = selectedButton.getText();

        if (selectedAnswer.equals(quesList[curQuestion].getCorrectOpt())) {
            curScore += 10;
        }
        scoreBoard.setText(curScore + "/100");
        onActionNext();
    }

    public void onActionBack() {
        if (curQuestion > 0) {
            curQuestion--;
        }
        displayQuestion(curQuestion);
    }

    public void onActionNext() {
        if (curQuestion < 9) {
            curQuestion++;
        }
        displayQuestion(curQuestion);
    }
}
