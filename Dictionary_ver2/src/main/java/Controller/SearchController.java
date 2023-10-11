package Controller;

import DictionaryComandLine.DictionaryManagement;
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
    private TextField searchField;
    @FXML
    private TextArea definitionArea;
    @FXML
    private ListView<String> listWord;

    private ObservableList<String> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        list = DictionaryManagement.dbSearch(" ", eng_vieTable);
        listWord.setItems(list);
    }

    public void onActionSearchBtn() {
        list = DictionaryManagement.dbSearch("'" + searchField.getText().toLowerCase().trim() + "%'",
                eng_vieTable);
        listWord.setItems(list);
    }

//    public void onMouseClickListView() {
//        Word selectedWord = (Word) listWord.getSelectionModel().getSelectedItem();
//        if (selectedWord != null) {
//            definitionArea.setText(selectedWord.getWord_explain());
//        }
//    }
}
