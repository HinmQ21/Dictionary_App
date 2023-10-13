package Controller;

import DictionaryComandLine.DictionaryManagement;
import DictionaryComandLine.Word;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchController implements Initializable {
    private final String eng_vieTable = "av";

    @FXML
    private TextField searchField, pronounceField;
    @FXML
    private TextArea definitionArea;
    @FXML
    private ListView<Word> listWord;

    private ObservableList<Word> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        list = DictionaryManagement.dbSearch("'a%'", eng_vieTable);
        listWord.setItems(list);
    }

    public void onActionSearchBtn() {
        list = DictionaryManagement.dbSearch("'" + searchField.getText().toLowerCase().trim() + "%'",
                eng_vieTable);
        listWord.setItems(list);
    }

    public void onMouseClickListView() {
        Word selectedWord = listWord.getSelectionModel().getSelectedItem();
        if (selectedWord != null) {
            definitionArea.setText(selectedWord.getWord_explain());
            pronounceField.setText(selectedWord.getWord_pronouce());
            searchField.setText(selectedWord.getWord_target());
        }
    }
}
