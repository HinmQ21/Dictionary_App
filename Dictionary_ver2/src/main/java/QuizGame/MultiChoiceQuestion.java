package QuizGame;

public class MultiChoiceQuestion extends Question{
    private String [] opts;

    private int correctOpt;

    public MultiChoiceQuestion(String questiontext, String[] opts, int correctOpt) {
        this.correctOpt = correctOpt;
        this.opts = opts;
        this.questionText = questiontext;
    }

    @Override
    public String getQuestionText() {
        return this.questionText;
    }

    public String[] getOpts() {
        return opts;
    }

    public String getCorrectOpt() {
        if (correctOpt == 0) {
            return "A";
        } else if (correctOpt == 1) {
            return "B";
        } else if (correctOpt == 2) {
            return "C";
        } return "D";
    }

    public String getCorrectAnswer() {
        return opts[correctOpt];
    }
}
