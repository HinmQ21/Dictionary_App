package Controller;

import DictionaryComandLine.DictionaryManagement;
import DictionaryComandLine.Word;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;

import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchController implements Initializable {
    private final String eng_vieTable = "av";

    @FXML
    private TextField searchField;
    @FXML
    private WebView definitionArea;
    @FXML
    private ListView<String> listWord;

    @FXML
    private AnchorPane updateWindow;

    @FXML
    private TextArea newDefinition;

    private ObservableList<String> list = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DictionaryManagement.readData();
        list = DictionaryManagement.dbSearch("'a%'", eng_vieTable);
        listWord.setItems(list);
        updateWindow.setVisible(false);
    }

    public void onActionSearchBtn() {
        list = DictionaryManagement.dbSearch("'" + searchField.getText().toLowerCase().trim() + "%'",
                eng_vieTable);
        listWord.setItems(list);
    }

    public void onMouseClickListView() {
        String selectedString = listWord.getSelectionModel().getSelectedItem();
        Word selectedWord = DictionaryManagement.getData().get(selectedString);
        if (selectedWord != null) {
            definitionArea.getEngine().loadContent(selectedWord.getWord_explain());
            searchField.setText(selectedWord.getWord_target());
        }
    }

    public void onActionDelete() {
        String selectedString = listWord.getSelectionModel().getSelectedItem();
        Word selectedWord = DictionaryManagement.getData().get(selectedString);

        if (selectedWord != null) {
            DictionaryManagement.dbDelete(selectedString, eng_vieTable);
        }
    }

    public void onActionUpdate() {
        String selectedString = listWord.getSelectionModel().getSelectedItem();

        if (selectedString != null) {
            updateWindow.setVisible(true);
        }
    }

    public void onActionSubmit() {
        String new_def = newDefinition.getText().toLowerCase().trim();

        if (!new_def.isEmpty()) {
            String selectedString = listWord.getSelectionModel().getSelectedItem();
            DictionaryManagement.dbUpdate(selectedString, new_def, eng_vieTable);

            updateWindow.setVisible(false);
            definitionArea.getEngine().loadContent(DictionaryManagement.getData().get(selectedString).getWord_explain());
            newDefinition.clear();
        }
    }

    public void onActionSpeak() {
        String selectedString = listWord.getSelectionModel().getSelectedItem();
        DictionaryManagement.TextToSpeech(selectedString, "hl=en-us");
    }
}
