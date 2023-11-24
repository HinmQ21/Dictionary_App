package Controller.Game_Controller;


import QuizGame.MultiChoiceQuestion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import javafx.fxml.Initializable;


import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class MultichoiceGame implements Initializable {
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


    public void onActionHome() {
        try {
            BorderPane component = FXMLLoader.load(getClass().getResource("/fxml/QuizGame.fxml"));
            quizContainer.getChildren().clear();
            quizContainer.getChildren().add(component);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private final MultiChoiceQuestion[] allQuestions = {
            new MultiChoiceQuestion("The weather is so _______ that we decided to have a picnic in the park.", new String[]{"good", "sunny", "well", "fine"}, 1),
            new MultiChoiceQuestion("She is allergic _______ cats.", new String[]{"on", "with", "to", "for"}, 2),
            new MultiChoiceQuestion("Yesterday, she _______ to the store and _______ some groceries.", new String[]{"go / buy", "went / buy", "goes / buys", "gone / buying"}, 1),
            new MultiChoiceQuestion("The movie was so boring that I fell _______ asleep.", new String[]{"almost", "hardly", "nearly", "scarcely"}, 2),
            new MultiChoiceQuestion("You _______ finish your homework before going out to play.", new String[]{"can", "should", "might", "must"}, 3),
            new MultiChoiceQuestion("This book is more interesting, but that one is _______.", new String[]{"interesting", "interestinger", "interested", "more interesting"}, 3),
            new MultiChoiceQuestion("By the time we get to the cinema, the movie _______.", new String[]{"will start", "started", "starts", "will have started"}, 3),
            new MultiChoiceQuestion("I have two _______.", new String[]{"child", "childs", "childes", "children"}, 3),
            new MultiChoiceQuestion("her / birthday / we / a surprise / gave", new String[]{"We gave a surprise her birthday.", "We gave her a surprise birthday.", "A surprise we gave her birthday.", "Her birthday gave we a surprise."}, 1),
            new MultiChoiceQuestion("She loves to read, _______ she also enjoys watching movies.", new String[]{"or", "so", "but", "because"}, 2),
            new MultiChoiceQuestion("The children played in the park _______ the rain stopped.", new String[]{"until", "while", "when", "since"}, 0),
            new MultiChoiceQuestion("He is always _______ time for his morning meditation.", new String[]{"take", "takes", "taken", "taking"}, 1),
            new MultiChoiceQuestion("The cat jumped _______ the table.", new String[]{"above", "on", "over", "across"}, 2),
            new MultiChoiceQuestion("I have never been to that city, but I have heard it's a beautiful _______.", new String[]{"place", "spaces", "location", "spot"}, 0),
            new MultiChoiceQuestion("The students were asked to work _______ on their group project.", new String[]{"together", "alone", "single", "singly"}, 0),
            new MultiChoiceQuestion("The scientist conducted several experiments to _______ his hypothesis.", new String[]{"prove", "approval", "acceptance", "support"}, 0),
            new MultiChoiceQuestion("The concert tickets sold out quickly, so we couldn't get any _______.", new String[]{"ones", "it", "them", "tickets"}, 3),
            new MultiChoiceQuestion("My parents have been married for 25 years; they are celebrating their _______ anniversary.", new String[]{"silver", "golden", "diamond", "ruby"}, 1),
            new MultiChoiceQuestion("The road was blocked due to a _______ accident.", new String[]{"serious", "seriously", "seriousness", "serios"}, 0),
            new MultiChoiceQuestion("She prefers to travel by train _______ by bus.", new String[]{"then", "over", "rather", "more"}, 2),
            new MultiChoiceQuestion("The teacher asked the students to submit their assignments _______ the end of the week.", new String[]{"on", "at", "in", "by"}, 3),
            new MultiChoiceQuestion("The chef prepared a delicious meal with a variety of fresh _______ and spices.", new String[]{"herbs", "herbses", "herb", "herbals"}, 0),
            new MultiChoiceQuestion("He is known for his great sense of _______ and always makes people laugh.", new String[]{"humor", "funny", "comedic", "laughter"}, 0),
            new MultiChoiceQuestion("The company organized a team-building workshop to _______ communication among employees.", new String[]{"enhance", "increase", "improve", "develop"}, 2),
            new MultiChoiceQuestion("The baby slept peacefully _______ his mother sang a lullaby.", new String[]{"although", "when", "since", "while"}, 3),
            new MultiChoiceQuestion("The athlete trained hard to improve his _______ and endurance.", new String[]{"strength", "power", "force", "energy"}, 0),
            new MultiChoiceQuestion("The art gallery features a diverse collection of contemporary _______.", new String[]{"paintings", "sculptures", "artworks", "drawings"}, 2),
            new MultiChoiceQuestion("She is skilled in multiple languages; _______, she can speak French, Spanish, and German.", new String[]{"moreover", "however", "nevertheless", "furthermore"}, 0),
            new MultiChoiceQuestion("The mountain trail is steep, so hikers need to be in good _______ for the climb.", new String[]{"condition", "shape", "form", "health"}, 0),
            new MultiChoiceQuestion("The company's success is attributed _______ its dedicated employees.", new String[]{"at", "by", "on", "to"}, 1),
            new MultiChoiceQuestion("The child was scared of the dark and always slept with a nightlight _______.", new String[]{"on", "in", "at", "by"}, 0),
            new MultiChoiceQuestion("The students were asked to submit their essays _______ the end of the semester.", new String[]{"until", "by", "to", "during"}, 1),
            new MultiChoiceQuestion("She was surprised to find a beautiful bouquet of flowers _______ her doorstep.", new String[]{"on", "at", "in", "by"}, 3),
            new MultiChoiceQuestion("The scientist made a groundbreaking _______ in the field of medicine.", new String[]{"discover", "discovery", "discovered", "discoverer"}, 1),
            new MultiChoiceQuestion("The children played happily _______ the playground.", new String[]{"at", "on", "in", "with"}, 2),
            new MultiChoiceQuestion("I will meet you _______ the park after work.", new String[]{"in", "at", "on", "by"}, 1),
            new MultiChoiceQuestion("The museum has a vast collection of historical _______ from different eras.", new String[]{"pieces", "works", "items", "artifacts"}, 3),
            new MultiChoiceQuestion("The cat is sitting _______ the chair and the sofa.", new String[]{"among", "between", "beside", "around"}, 1),
            new MultiChoiceQuestion("He enjoys playing the guitar _______ the evenings.", new String[]{"at", "on", "in", "by"}, 2),
            new MultiChoiceQuestion("The book is _______ on the top shelf, out of reach.", new String[]{"placed", "put", "set", "located"}, 2)
    };

    private MultiChoiceQuestion[] quesList = new MultiChoiceQuestion[10];


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        curQuestion = 0;
        curScore = 0;
        randomQuestions();
        displayQuestion(curQuestion);
        resultPane.setVisible(false);
        answerPane.setVisible(false);
    }

    private void randomQuestions() {
        int random = (int) (Math.random() * 4 + 1);
        for (int i = 0; i < 10; i++) {
            quesList[i] = allQuestions[random];
            random += (int) (Math.random() * 4 + 1);
        }
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

        if (selectedAnswer.equals(quesList[curQuestion].getCorrectAnswer())) {
            curScore += 10;
        }
        scoreBoard.setText(curScore + "/100");
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
