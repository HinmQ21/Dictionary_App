import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Dictionary {

    private final List<Word> words = new ArrayList<>();

    public List<Word> getWords() {
        return words;
    }

    public void insertWord(Word word) {
        words.add(word);
    }

    public void removeWord(Word word) {
        words.remove(word);
    }

    public void updateWord(Word oldWord, Word newWord) {
        int index = words.indexOf(oldWord);
        if (index != -1) {
            words.set(index, newWord);
        }
    }

    public Word lookupWord(String wordTarget) {
        for (Word word : words) {
            if (word.getWordTarget().equals(wordTarget)) {
                return word;
            }
        }
        return null;
    }

    public List<String> searchWords(String prefix) {
        List<String> result = new ArrayList<>();
        for (Word word : words) {
            if (word.getWordTarget().startsWith(prefix)) {
                result.add(word.getWordTarget());
            }
        }
        return result;
    }

    public void exportToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(filename)) {
            for (Word word : words) {
                writer.println(word.getWordTarget() + "\t" + word.getWordExplain());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void importFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length == 2) {
                    insertWord(new Word(parts[0], parts[1]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}