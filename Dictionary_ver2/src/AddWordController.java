import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class AddWordController {

    @FXML
    private TextField englishWordTextField;

    @FXML
    private TextField vietnameseExplanationTextField;

    private DictionaryManagement dictionaryManagement = new DictionaryManagement(new Dictionary());

    public void setDictionaryManagement(DictionaryManagement dictionaryManagement) {
        this.dictionaryManagement = dictionaryManagement;
    }

    @FXML
    private void addWordAction() {
        String wordTarget = englishWordTextField.getText();
        String wordExplain = vietnameseExplanationTextField.getText();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ADD WORD");

        if (!wordTarget.isEmpty() && !wordExplain.isEmpty()) {
            Word word = new Word(wordTarget, wordExplain);
            if(!dictionaryManagement.dictionary.contain(word)) {
                dictionaryManagement.dictionary.insertWord(word);

                alert.setHeaderText("Success");
                alert.setContentText("Word added successfully.");
            } else {

                alert.setHeaderText("Failure");
                alert.setContentText("The word is already in the dictionary.");
            }

        } else {

            alert.setHeaderText("Failure");
            alert.setContentText("Please enter both English word and Vietnamese explanation.");
        }
        alert.show();
        dictionaryManagement.showAllWords();
    }
}
