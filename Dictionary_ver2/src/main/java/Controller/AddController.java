package Controller;

import DictionaryComandLine.DictionaryManagement;
import javafx.fxml.FXML;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddController {
    @FXML
    private TextField wordField;

    @FXML
    private TextField pronounceField;

    @FXML
    private TextArea defArea;

    public void onActionSave() {
        String word = wordField.getText().toLowerCase();
        String pronounce = pronounceField.getText();
        String def = defArea.getText();

        boolean hasWord = DictionaryManagement.dbHasWord(word, "av");

        if (word.trim().equals("") || def.trim().equals("")) {

        } else if (hasWord) {

        } else {
            DictionaryManagement.dbAdd(word, pronounce, def, "av");
            System.out.println(word + " " + def + " " + pronounce);
            System.out.println("Add successfully");
        }
        wordField.clear();
        pronounceField.clear();
        defArea.clear();
    }
}
