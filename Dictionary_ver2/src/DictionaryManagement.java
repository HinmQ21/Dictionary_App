import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DictionaryManagement {
    Scanner scanner = new Scanner(System.in);

    Dictionary dictionary ;

    public DictionaryManagement(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public List<Word> getWordList() {
        return dictionary.getWords();
    }

    public void showAllWords() {
        System.out.println("No | English        | Vietnamese");
        List<Word> words = dictionary.getWords();
        for (int i = 0; i < words.size(); i++) {
            Word word = words.get(i);
            System.out.printf("%-3d| %-15s| %s%n", (i + 1), word.getWordTarget(), word.getWordExplain());
        }
    }
}


