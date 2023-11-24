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
        randomQuestions();
        displayQuestion(curQuestion);
        resultPane.setVisible(false);
        answerPane.setVisible(false);
    }

    private final FillQuestion[] allQuestions = {
            new FillQuestion("She ??? to the store when she realized she forgot her wallet.", "went"),
            new FillQuestion("The students ??? hard for the upcoming exams.   ", "studied"),
            new FillQuestion("He couldn't find his keys, so he had to ??? for them everywhere.", "search"),
            new FillQuestion("The weather was so ??? that we decided to stay indoors.", "bad"),
            new FillQuestion("I need to buy some ??? for the recipe I'm making for dinner.", "ingredients"),
            new FillQuestion("The cat is sitting ??? the table and the chair.", "between"),
            new FillQuestion("We have a meeting ??? Monday morning at 9 AM.", "on"),
            new FillQuestion("The concert was amazing; the band played ??? songs from their new album.", "many"),
            new FillQuestion("She opened the door and saw a ??? bouquet of flowers on the doorstep.", "beautiful"),
            new FillQuestion("My grandmother always tells me stories from her ???.", "youth"),
            new FillQuestion("The ??? was too high for him to reach without a ladder.", "shelf"),
            new FillQuestion("I can't believe I ??? my phone at home again.", "forgot"),
            new FillQuestion("The ??? was covered in a thick layer of snow.", "landscape"),
            new FillQuestion("After the rain, there was a ??? in the sky.", "rainbow"),
            new FillQuestion("She ??? her favorite movie for the tenth time.", "watched"),
            new FillQuestion("The ??? was filled with ancient artifacts.", "museum"),
            new FillQuestion("He ??? his car before the road trip.", "checked"),
            new FillQuestion("We had to ??? the event due to bad weather.", "cancel"),
            new FillQuestion("She loves to read books ??? bed.", "before"),
            new FillQuestion("The cat is hiding ??? the sofa.", "behind"),
            new FillQuestion("We decided to go for a walk ??? the beautiful garden.", "through"),
            new FillQuestion("The teacher asked the students to write their names ??? the top of the page.", "at"),
            new FillQuestion("They had to climb a steep hill ??? they could see the entire city.", "before"),
            new FillQuestion("He enjoys listening to music ??? he works.", "while"),
            new FillQuestion("I will meet you at the cafe ??? 3:00 PM.", "at"),
            new FillQuestion("She is allergic to peanuts, so she always checks the ingredients ??? buying snacks.", "before"),
            new FillQuestion("We gathered ??? the fireplace to stay warm.", "around"),
            new FillQuestion("The company organized a team-building workshop ??? improve communication.", "to"),
            new FillQuestion("The sun sets in the west, painting the sky with shades of ??? and orange.", "pink"),
            new FillQuestion("She always takes a break to have a cup of coffee ??? starting her work.", "before"),
            new FillQuestion("The students were fascinated by the teacher's ??? explanation of the scientific experiment.", "detailed"),
            new FillQuestion("The train arrived at the station ??? time, and we boarded quickly.", "on"),
            new FillQuestion("He is known for his ability to remain calm and composed ??? stressful situations.", "during"),
            new FillQuestion("The museum displays a wide collection of ancient ??? from different civilizations.", "artifacts"),
            new FillQuestion("The children played ??? in the backyard, laughing and enjoying the sunshine.", "happily"),
            new FillQuestion("We need to make a reservation ??? advance to secure a table at the restaurant.", "in"),
            new FillQuestion("The detective carefully examined the crime scene ??? finding any clues.", "before"),
            new FillQuestion("She always carries a small notebook ??? jot down her ideas throughout the day.", "to"),
            new FillQuestion("The road was blocked, so we had to take a detour ??? reach our destination.", "to"),
            new FillQuestion("The baby slept peacefully ??? her mother's comforting lullaby.", "to")
    };

    private FillQuestion[] quesList = new FillQuestion[10];

    private void reformatQuestion(FillQuestion fillQuestion) {
        if (fillQuestion != null) {
            String blank = "_ ".repeat(Math.max(0, fillQuestion.getAnswer().length() - 1)) +
                    "_";
            String newFormat = fillQuestion.getQuestionText().replace("???", blank);
            fillQuestion.setQuestionText(newFormat);
        }
    }

    private void randomQuestions() {
        int random = (int) (Math.random() * 4 + 1);
        for (int i = 0; i < 10; i++) {
            quesList[i] = allQuestions[random];
            reformatQuestion(quesList[i]);
            random += (int) (Math.random() * 4 + 1);
        }
    }

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
