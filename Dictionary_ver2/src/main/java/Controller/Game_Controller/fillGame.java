package Controller.Game_Controller;

import QuizGame.FillQuestion;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class fillGame implements Initializable {
    @FXML
    AnchorPane quizContainer;

    @FXML
    Label quesText, quesNum, scoreBoard;

    @FXML
    TextField input;

    @FXML
    private AnchorPane resultPane, answerPane;

    @FXML
    private Label C1,C2,C3,C4,C5,C6,C7,C8,C9,C10;

    @FXML
    private Label resLabel, resComment;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        curQuestion = 0;
        curScore = 0;
        displayQuestion(curQuestion);
        resultPane.setVisible(false);
        answerPane.setVisible(false);
    }

    private final FillQuestion[] quesList = {
            new FillQuestion("She _ _ _ _ to the store when she realized she forgot her wallet.", "went"),
            new FillQuestion("The students _ _ _ _ _ _ _ hard for the upcoming exams.", "studied"),
            new FillQuestion("He couldn't find his keys, so he had to _ _ _ _ _ _ for them everywhere.", "search"),
            new FillQuestion("The weather was so _ _ _ that we decided to stay indoors.", "bad"),
            new FillQuestion("I need to buy some _ _ _ _ _ _ _ _ _ _ _ for the recipe I'm making for dinner.", "ingredients"),
            new FillQuestion("The cat is sitting _ _ _ _ _ _ _ the table and the chair.", "between"),
            new FillQuestion("We have a meeting _ _ Monday morning at 9 AM.", "on"),
            new FillQuestion("The concert was amazing; the band played _ _ _ _ songs from their new album.", "many"),
            new FillQuestion("She opened the door and saw a _ _ _ _ _ _ _ _ _ bouquet of flowers on the doorstep.", "beautiful"),
            new FillQuestion("My grandmother always tells me stories from her _ _ _ _ _.", "youth")
    };

    private void displayQuestion(int th) {
        quesNum.setText("Question : " + (curQuestion + 1) + "/10");
        quesText.setText(quesList[th].getQuestionText());
        scoreBoard.setText(curScore + "/100");
    }

    public void onActionSubmit() {
        String myAns = input.getText().trim().toLowerCase();
        if (myAns.equals(quesList[curQuestion].getAnswer())) {
            curScore += 10;
        }
        if(curQuestion < 9) displayQuestion(++curQuestion);
        input.setText("");
        if (curQuestion == 9) showResults();
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
        C1.setText("Q1: " + quesList[0].getAnswer());
        C2.setText("Q2: " + quesList[1].getAnswer());
        C3.setText("Q3: " + quesList[2].getAnswer());
        C4.setText("Q4: " + quesList[3].getAnswer());
        C5.setText("Q5: " + quesList[4].getAnswer());
        C6.setText("Q6: " + quesList[5].getAnswer());
        C7.setText("Q7: " + quesList[6].getAnswer());
        C8.setText("Q8: " + quesList[7].getAnswer());
        C9.setText("Q9: " + quesList[8].getAnswer());
        C10.setText("Q10: " + quesList[9].getAnswer());
    }
}
