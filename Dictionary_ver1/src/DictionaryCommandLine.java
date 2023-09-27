import java.util.List;

public class DictionaryCommandLine {
    private Dictionary dictionary;
    private DictionaryManagement dictionaryManagement;

    public DictionaryCommandLine() {
        dictionary = new Dictionary();
        dictionaryManagement = new DictionaryManagement(dictionary);
    }
    public void showMenu() {
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
    }

    public void dictionaryAdvanced() {
        while (true) {
            showMenu();

            int choice = dictionaryManagement.scanner.nextInt();
            dictionaryManagement.scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 0:
                    return;
                case 1:
                    dictionaryManagement.addWord();
                    break;
                case 2:
                    dictionaryManagement.removeWord();
                    break;
                case 3:
                    dictionaryManagement.updateWord();
                    break;
                case 4:
                    dictionaryManagement.showAllWords();
                    break;
                case 5:
                    dictionaryManagement.dictionaryLookup(dictionary);
                    break;
                case 6:
                    dictionaryManagement.searchWord();
                    break;
                case 7:
                    dictionaryManagement.init_dictionaryGame();
                    dictionaryManagement.dictionaryGame();
                    break;
                case 8:
                    dictionaryManagement.dictionaryImportFromFile();
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
