import java.util.List;

public class DictionaryCommandLine {
    Dictionary dictionary = new Dictionary();
    DictionaryManagement dictionaryManagement = new DictionaryManagement();

    public void showAllWords() {
        System.out.println("No | English        | Vietnamese");
        List<Word> words = dictionary.getWords();
        for (int i = 0; i < words.size(); i++) {
            Word word = words.get(i);
            System.out.printf("%-3d| %-15s| %s%n", (i + 1), word.getWordTarget(), word.getWordExplain());
        }
    }

    public void dictionaryBasic() {
        dictionaryManagement.insertFromCommandLine(dictionary);
        showAllWords();
    }

    public void dictionaryAdvanced() {
        while (true) {
            System.out.println("Welcome to My Application!");
            System.out.println("[0] Exit");
            System.out.println("[1] Add");
            System.out.println("[2] Remove");
            System.out.println("[3] Update");
            System.out.println("[4] Display");
            System.out.println("[5] Lookup");
            System.out.println("[6] Search");
            System.out.println("[7] Game");
            System.out.println("[8] Import from file");
            System.out.println("[9] Export to file");
            System.out.print("Your action: ");

            int choice = dictionaryManagement.scanner.nextInt();
            dictionaryManagement.scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 0:
                    return;
                case 1:
                    System.out.print("Enter word in English: ");
                    String wordTarget = dictionaryManagement.scanner.nextLine();
                    System.out.print("Enter word in Vietnamese: ");
                    String wordExplain = dictionaryManagement.scanner.nextLine();
                    Word word = new Word(wordTarget, wordExplain);
                    dictionary.insertWord(word);
                    break;
                case 2:
                    System.out.print("Enter the word to remove: ");
                    wordTarget = dictionaryManagement.scanner.nextLine();
                    Word removeWord = dictionary.lookupWord(wordTarget);
                    if (removeWord != null) {
                        dictionary.removeWord(removeWord);
                    } else {
                        System.out.println("Word not found in the dictionary.");
                    }
                    break;
                case 3:
                    System.out.print("Enter the word to update: ");
                    String oldWordTarget = dictionaryManagement.scanner.nextLine();
                    Word oldWord = dictionary.lookupWord(oldWordTarget);
                    if (oldWord != null) {
                        System.out.print("Enter the new word in English: ");
                        String newWordTarget = dictionaryManagement.scanner.nextLine();
                        System.out.print("Enter the new word in Vietnamese: ");
                        String newWordExplain = dictionaryManagement.scanner.nextLine();
                        Word newWord = new Word(newWordTarget, newWordExplain);
                        dictionary.updateWord(oldWord, newWord);
                    } else {
                        System.out.println("Word not found in the dictionary.");
                    }
                    break;
                case 4:
                    showAllWords();
                    break;
                case 5:
                    dictionaryManagement.dictionaryLookup(dictionary);
                    break;
                case 6:
                    System.out.print("Enter the prefix to search: ");
                    String prefix = dictionaryManagement.scanner.nextLine();
                    List<String> result = dictionary.searchWords(prefix);
                    System.out.println("Words found:");
                    for (String wordResult : result) {
                        System.out.println(wordResult);
                    }
                    break;
                case 7:
                    System.out.println("Game feature is not supported yet.");
                    break;
                case 8:
                    //System.out.print("Enter the filename to import: ");
                    //String importFilename = dictionaryManagement.scanner.nextLine();
                    String importFilename = "src\\dictionaries.txt";
                    dictionary.importFromFile(importFilename);
                    break;
                case 9:
                    dictionaryManagement.dictionaryExportToFile(dictionary);
                    break;
                default:
                    System.out.println("Action not supported");
                    break;
            }
        }
    }
}
