import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class RemoveWordController {

    @FXML
    private TextField englishWordTextField;

    private DictionaryManagement dictionaryManagement = new DictionaryManagement(new Dictionary());

    public void setDictionaryManagement(DictionaryManagement dictionaryManagement) {
        this.dictionaryManagement = dictionaryManagement;
    }

    @FXML
    private void removeWordAction() {
        String wordTarget = englishWordTextField.getText();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("REMOVE WORD");

        if (!wordTarget.isEmpty()) {
            Word wordToRemove = dictionaryManagement.getWordList()
                    .stream()
                    .filter(word -> word.getWordTarget().equals(wordTarget))
                    .findFirst()
                    .orElse(null);

            if (wordToRemove != null) {
                dictionaryManagement.getWordList().remove(wordToRemove);
                alert.setHeaderText("Success");
                alert.setContentText("Word removed successfully. ");
            } else {
                alert.setHeaderText("Failure");
                alert.setContentText("Word not found in the dictionary.");
            }
        } else {
            alert.setHeaderText("Failure");
            alert.setContentText("There must be no blank line\nPlease enter English word again");
        }
        alert.show();
        dictionaryManagement.showAllWords();
    }
}
