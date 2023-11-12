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


import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class MultichoiceGame implements Initializable{
    @FXML
    private AnchorPane quizContainer;

    @FXML
    private AnchorPane resultPane, answerPane;

    @FXML
    private Label quesNum;

    @FXML
    private Label quesText;

    @FXML
    private Label scoreBoard;

    @FXML
    private Button butA, butB, butC, butD;

    @FXML
    private Label resLabel, resComment;

    @FXML
    private Label C1,C2,C3,C4,C5,C6,C7,C8,C9,C10;

    private int curQuestion, curScore;

    private boolean[] done;

    public void onActionHome() {
        try {
            BorderPane component = FXMLLoader.load(getClass().getResource("/fxml/QuizGame.fxml"));
            quizContainer.getChildren().clear();
            quizContainer.getChildren().add(component);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private final MultiChoiceQuestion[] quesList = {
            new MultiChoiceQuestion("The weather is so _______ that we decided to have a picnic in the park.", new String[]{"good", "sunny", "well", "fine"}, 1),
            new MultiChoiceQuestion("She is allergic _______ cats.", new String[]{"on", "with", "to", "for"}, 2),
            new MultiChoiceQuestion("Yesterday, she _______ to the store and _______ some groceries.", new String[]{"go / buy", "went / buy", "goes / buys", "gone / buying"}, 1),
            new MultiChoiceQuestion("The movie was so boring that I fell _______ asleep.", new String[]{"almost", "hardly", "nearly", "scarcely"}, 2),
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
        done = new boolean[10];
        displayQuestion(curQuestion);
        Arrays.fill(done, false);
        resultPane.setVisible(false);
        answerPane.setVisible(false);
    }

    private void displayQuestion(int th) {
        quesNum.setText("Question : " + (curQuestion + 1) + "/10");
        scoreBoard.setText(curScore + "/100");
        quesText.setText(quesList[th].getQuestionText());
        butA.setText(quesList[th].getOpts()[0]);
        butB.setText(quesList[th].getOpts()[1]);
        butC.setText(quesList[th].getOpts()[2]);
        butD.setText(quesList[th].getOpts()[3]);
    }

    public void onActionHandleAns(ActionEvent event) {
        Button selectedButton = (Button) event.getSource();
        String selectedAnswer = selectedButton.getText();

        if (selectedAnswer.equals(quesList[curQuestion].getCorrectAnswer()) && !done[curQuestion]) {
            curScore += 10;
        }
        scoreBoard.setText(curScore + "/100");
        done[curQuestion] = true;
        if (curQuestion < 9) {
            curQuestion++;
        } else {
            showResults();
        }
        displayQuestion(curQuestion);
    }

    private void showResults() {
        resultPane.setVisible(true);
        if (curScore > 50) {
            resLabel.setText("Wow! Congratulations!!");
            resComment.setText("Your english is relatively good!");
        } else {
            resLabel.setText("Poor!!!");
            resComment.setText("You should improve your English, right now!");
        }
    }

    public void showAnswers() {
        answerPane.setVisible(true);
        quesNum.setText("");
        quesText.setText("");
        butA.setVisible(false);
        butB.setVisible(false);
        butC.setVisible(false);
        butD.setVisible(false);
        C1.setText("Q1: " + quesList[0].getCorrectOpt());
        C2.setText("Q2: " + quesList[1].getCorrectOpt());
        C3.setText("Q3: " + quesList[2].getCorrectOpt());
        C4.setText("Q4: " + quesList[3].getCorrectOpt());
        C5.setText("Q5: " + quesList[4].getCorrectOpt());
        C6.setText("Q6: " + quesList[5].getCorrectOpt());
        C7.setText("Q7: " + quesList[6].getCorrectOpt());
        C8.setText("Q8: " + quesList[7].getCorrectOpt());
        C9.setText("Q9: " + quesList[8].getCorrectOpt());
        C10.setText("Q10: " + quesList[9].getCorrectOpt());
    }
}
