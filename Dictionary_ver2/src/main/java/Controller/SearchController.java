package Controller;

import DictionaryComandLine.DictionaryManagement;
import DictionaryComandLine.Word;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
import javafx.scene.image.ImageView;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Duration;

import java.net.URL;
import java.time.LocalDate;
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
    private ListView<Word> listWord;

    @FXML
    private AnchorPane updateWindow;

    @FXML
    private AnchorPane suggestPane;

    @FXML
    private TextArea newDefinition;

    @FXML
    private AnchorPane deleteConfirmation;

    @FXML
    private ImageView leftFlag;

    @FXML
    private ImageView rightFlag;

    @FXML
    Label wordOfDay;

    private Word chosenWord = null;

    private ObservableList<Word> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        curTable = eng_vieTable;
        list = DictionaryManagement.dbSearch("'a%'", curTable);
        listWord.setItems(list);
        updateWindow.setVisible(false);
        deleteConfirmation.setVisible(false);
        suggestPane.setVisible(false);
        choseWordOfDay();
    }

    public void onActionSuggest() {
        suggestPane.setVisible(!suggestPane.isVisible());
    }

    private void choseWordOfDay() {
        LocalDate currentDate = LocalDate.now();

        int hashCode = currentDate.hashCode();
        chosenWord = DictionaryManagement.getWordByHash(hashCode, eng_vieTable);
        wordOfDay.setText(chosenWord.toString());
    }

    public void onActionGo() {
        searchField.setText(chosenWord.getWord_target());
        listWord.setItems(null);
        definitionArea.getEngine().loadContent(chosenWord.getWord_explain());
        suggestPane.setVisible(!suggestPane.isVisible());
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
        list = DictionaryManagement.dbSearch("'a%'", curTable);
        listWord.setItems(list);
    }

    public void onActionSearchBtn() {
        list = DictionaryManagement.dbSearch("'" + searchField.getText().toLowerCase().trim() + "%'",
                curTable);
        listWord.setItems(list);
    }

    public void onMouseClickListView() {
        Word selectedWord = listWord.getSelectionModel().getSelectedItem();
        if (selectedWord != null) {
            definitionArea.getEngine().loadContent(selectedWord.getWord_explain());
            searchField.setText(selectedWord.getWord_target());
        }
    }

    public void onActionDelete() {
        Word selectedWord = listWord.getSelectionModel().getSelectedItem();
        if (selectedWord != null || suggestPane.isVisible()) {
            deleteConfirmation.setVisible(true);
        }
    }

    public void onActionDeleteConfirmation() {
        Word selectedWord = listWord.getSelectionModel().getSelectedItem();
        String word = selectedWord.getWord_target();

        if (suggestPane.isVisible()) {
            word = chosenWord.getWord_target();
        }

        if (selectedWord != null) {
            DictionaryManagement.dbDelete(word, curTable);
            deleteConfirmation.setVisible(false);
        }
    }

    public void onActionDeleteCancelation() {
        deleteConfirmation.setVisible(false);
    }

    public void onActionUpdate() {
        Word selectedWord = listWord.getSelectionModel().getSelectedItem();

        if (selectedWord != null || suggestPane.isVisible()) {
            updateWindow.setVisible(true);
        }
    }

    public void onActionUpdateCancelation() {
        updateWindow.setVisible(false);
    }

    public void onActionSubmit() {
        String new_def = newDefinition.getText().toLowerCase().trim();

        if (!new_def.isEmpty()) {
            Word selectedWord = listWord.getSelectionModel().getSelectedItem();

            if (suggestPane.isVisible()) {
                selectedWord = chosenWord;
            }
            DictionaryManagement.dbUpdate(selectedWord.getWord_target(), new_def, curTable);

            updateWindow.setVisible(false);
            definitionArea.getEngine().loadContent(new_def);
            newDefinition.clear();
        }
    }

    public void onActionSpeak() {
        String langCode = "";
        try {
            Word selectedWord = listWord.getSelectionModel().getSelectedItem();

            if (suggestPane.isVisible()) {
                selectedWord = chosenWord;
            }

            if (curTable.equals("av")) langCode = "en-us";
            else langCode = "vi-vn";
            DictionaryManagement.TextToSpeech(selectedWord.getWord_target(), "hl=" + langCode);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
