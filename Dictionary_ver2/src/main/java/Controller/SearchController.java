package Controller;

import DictionaryComandLine.DictionaryManagement;
import DictionaryComandLine.Word;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
import javafx.scene.image.ImageView;

import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchController implements Initializable {
    private final String eng_vieTable = "av";

    private final String viet_engTable = "va";

    private String curTable;

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

    @FXML
    private AnchorPane deleteConfirmation;

    @FXML
    private ImageView leftFlag;

    @FXML
    private ImageView rightFlag;

    private ObservableList<String> list = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DictionaryManagement.readData(eng_vieTable);
        curTable = eng_vieTable;
        list = DictionaryManagement.dbSearch("'a%'", curTable);
        listWord.setItems(list);
        updateWindow.setVisible(false);
        deleteConfirmation.setVisible(false);
    }

    public void onActionExchange() {
        if (curTable.equals(eng_vieTable)) {
            curTable = viet_engTable;
        } else curTable = eng_vieTable;

        //swap flag
        Image tempIm = leftFlag.getImage();
        leftFlag.setImage(rightFlag.getImage());
        rightFlag.setImage(tempIm);

        searchField.clear();
        definitionArea.getEngine().loadContent("");
        DictionaryManagement.readData(curTable);
        list = DictionaryManagement.dbSearch("'a%'", curTable);
        listWord.setItems(list);
    }

    public void onActionSearchBtn() {
        list = DictionaryManagement.dbSearch("'" + searchField.getText().toLowerCase().trim() + "%'",
                curTable);
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
            deleteConfirmation.setVisible(true);
        }
    }

    public void onActionDeleteConfirmation() {
        String selectedString = listWord.getSelectionModel().getSelectedItem();
        Word selectedWord = DictionaryManagement.getData().get(selectedString);

        if (selectedWord != null) {
            DictionaryManagement.dbDelete(selectedString, curTable);
        }
    }

    public void onActionDeleteCancelation() {
        deleteConfirmation.setVisible(false);
    }

    public void onActionUpdate() {
        String selectedString = listWord.getSelectionModel().getSelectedItem();

        if (selectedString != null) {
            updateWindow.setVisible(true);
        }
    }

    public void onActionUpdateCancelation() {
        updateWindow.setVisible(false);
    }

    public void onActionSubmit() {
        String new_def = newDefinition.getText().toLowerCase().trim();

        if (!new_def.isEmpty()) {
            String selectedString = listWord.getSelectionModel().getSelectedItem();
            DictionaryManagement.dbUpdate(selectedString, new_def, curTable);

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
