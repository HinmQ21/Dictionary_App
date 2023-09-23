import java.util.Scanner;

public class DictionaryManagement {
    Scanner scanner = new Scanner(System.in);

    public void insertFromCommandLine(Dictionary dictionary) {
        System.out.print("Enter the number of words: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        for (int i = 0; i < n; i++) {
            System.out.print("Enter word in English: ");
            String wordTarget = scanner.nextLine();
            System.out.print("Enter word in Vietnamese: ");
            String wordExplain = scanner.nextLine();
            Word word = new Word(wordTarget, wordExplain);
            dictionary.insertWord(word);
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

    public void dictionaryExportToFile(Dictionary dictionary) {
        System.out.print("Enter the filename to export: ");
        String filename = scanner.nextLine();
        dictionary.exportToFile(filename);
    }
}


