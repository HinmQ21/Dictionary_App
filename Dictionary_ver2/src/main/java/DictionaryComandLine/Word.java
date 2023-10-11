package DictionaryComandLine;

public class Word {
    private String word_target;
    private String word_explain;
    private String word_pronouce;

    public Word (String t, String e, String p) {
        word_target = t;
        word_explain = e;
        word_pronouce = p;
    }

    public String getWord_target() {
        return word_target;
    }

    public void setWord_target(String word_target) {
        this.word_target = word_target;
    }

    public String getWord_explain() {
        return word_explain;
    }

    public void setWord_explain(String word_explain) {
        this.word_explain = word_explain;
    }

    public String getWord_pronouce() {
        return word_pronouce;
    }

    public void setWord_pronouce(String word_pronouce) {
        this.word_pronouce = word_pronouce;
    }

}
