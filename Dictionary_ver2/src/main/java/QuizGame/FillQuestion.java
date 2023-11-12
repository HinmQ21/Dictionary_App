package QuizGame;

public class FillQuestion extends Question{
    private String answer;

    public FillQuestion(String questionText, String answer) {
        this.answer = answer;
        this.questionText = questionText;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public String getQuestionText() {
        return this.questionText;
    }
}
