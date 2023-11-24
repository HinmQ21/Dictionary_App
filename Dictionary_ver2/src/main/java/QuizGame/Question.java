package QuizGame;

public abstract class Question {
    protected String questionText;

    protected int point;

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public int getPoint() {
        return point;
    }
}
