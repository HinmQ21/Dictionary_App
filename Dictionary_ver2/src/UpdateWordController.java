import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class UpdateWordController {

    @FXML
    private TextField englishWordTextField;

    @FXML
    private TextField newVietnameseExplanationTextField;

    private DictionaryManagement dictionaryManagement;

    public void setDictionaryManagement(DictionaryManagement dictionaryManagement) {
        this.dictionaryManagement = dictionaryManagement;
    }

    @FXML
    private void updateWordAction() {
        String wordTarget = englishWordTextField.getText();
        String newExplain = newVietnameseExplanationTextField.getText();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("UPDATE WORD");

        if (!wordTarget.isEmpty() && !newExplain.isEmpty()) {
            Word wordToUpdate = dictionaryManagement.getWordList()
                    .stream()
                    .filter(word -> word.getWordTarget().equals(wordTarget))
                    .findFirst()
                    .orElse(null);

            if (wordToUpdate != null) {
                Word updatedWord = new Word(wordTarget, newExplain);
                dictionaryManagement.dictionary.updateWord(wordToUpdate, updatedWord);
                alert.setHeaderText("Success");
                alert.setContentText("Word updated successfully.");
            } else {
                alert.setHeaderText("Failure");
                alert.setContentText("Word not found in the dictionary.");
            }
        } else {
            alert.setHeaderText("Failure");
            alert.setContentText("Please enter both English word and Vietnamese explanation.");        }
    }
}
