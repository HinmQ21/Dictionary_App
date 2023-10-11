package Controller;

import DictionaryComandLine.DictionaryManagement;
import javafx.fxml.FXML;

import javafx.scene.control.TextArea;

import java.io.IOException;

public class TranslateController {
    @FXML
    TextArea vnText, enText;

    public void onActionTran() throws IOException {
        String vnInput = vnText.getText();
        String res = DictionaryManagement.translate("en", "vi", vnInput);
        enText.setText(res);
    }
}
