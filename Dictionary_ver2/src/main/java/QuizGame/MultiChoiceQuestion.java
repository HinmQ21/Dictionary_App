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

    public int getCorrectOpt() {
        return correctOpt;
    }
}
