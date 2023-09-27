import java.util.List;
import java.util.Scanner;

public class DictionaryManagement {
    Scanner scanner = new Scanner(System.in);

    Dictionary dictionary ;
    public DictionaryManagement(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

//    public void insertFromCommandLine(Dictionary dictionary) {
//        System.out.print("Enter the number of words: ");
//        int n = scanner.nextInt();
//        scanner.nextLine(); // Consume the newline character
//        for (int i = 0; i < n; i++) {
//            System.out.print("Enter word in English: ");
//            String wordTarget = scanner.nextLine();
//            System.out.print("Enter word in Vietnamese: ");
//            String wordExplain = scanner.nextLine();
//            Word word = new Word(wordTarget, wordExplain);
//            dictionary.insertWord(word);
//        }
//    }

    public void addWord() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the English word: ");
        String wordTarget = scanner.nextLine();
        System.out.print("Enter the Vietnamese explanation: ");
        String wordExplain = scanner.nextLine();
        Word word = new Word(wordTarget, wordExplain);
        dictionary.insertWord(word);
    }

    public void removeWord() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the English word to remove: ");
        String wordTarget = scanner.nextLine();
        Word word = dictionary.lookupWord(wordTarget);
        if (word != null) {
            dictionary.removeWord(word);
            System.out.println("Word removed successfully.");
        } else {
            System.out.println("Word not found in the dictionary.");
        }
    }

    public void updateWord() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the English word to update: ");
        String wordTarget = scanner.nextLine();
        Word word = dictionary.lookupWord(wordTarget);
        if (word != null) {
            System.out.print("Enter the new Vietnamese explanation: ");
            String newExplain = scanner.nextLine();
            Word new_word = new Word(wordTarget, newExplain);
            dictionary.updateWord(word, new_word);
            System.out.println("Word updated successfully.");
        } else {
            System.out.println("Word not found in the dictionary.");
        }
    }

    public void searchWord() {
        System.out.print("Enter the prefix to search: ");
        String prefix = scanner.nextLine();
        List<String> result = dictionary.searchWords(prefix);
        System.out.println("Words found:");
        for (String wordResult : result) {
            System.out.println(wordResult);
        }
    }
    public void showAllWords() {
        System.out.println("No | English        | Vietnamese");
        List<Word> words = dictionary.getWords();
        for (int i = 0; i < words.size(); i++) {
            Word word = words.get(i);
            System.out.printf("%-3d| %-15s| %s%n", (i + 1), word.getWordTarget(), word.getWordExplain());
        }
    }

    public void dictionaryLookup(Dictionary dictionary) {
        System.out.print("Enter the word you want to look up: ");
        String wordTarget = scanner.nextLine();
        Word word = dictionary.lookupWord(wordTarget);
        if (word != null) {
            System.out.println("English: " + word.getWordTarget());
            System.out.println("Vietnamese: " + word.getWordExplain());
        } else {
            System.out.println("Word not found in the dictionary.");
        }
    }

    public void dictionaryImportFromFile() {
//        System.out.print("Enter the filename to import: ");
//        String importFile = scanner.nextLine();
        String importFilename = "src\\dictionaries.txt";
        dictionary.importFromFile(importFilename);
    }

    public void dictionaryExportToFile(Dictionary dictionary) {
        System.out.print("Enter the filename to export: ");
        String filename = scanner.nextLine();
        dictionary.exportToFile(filename);
    }
}


