package Controller;

import DictionaryComandLine.DictionaryManagement;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TranslateController implements Initializable {

    @FXML
    TextArea rightText, leftText;

    @FXML
    Label rightLabel, leftLabel;

    private String langFrom, langTo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rightLabel.setText("VIETNAMESE");
        leftLabel.setText("ENGLISH");
        langFrom = "en";
        langTo = "vi";
    }

    public void onActionTran() throws IOException {
        String vnInput = leftText.getText();
        String res = DictionaryManagement.translate(langFrom, langTo, vnInput);
        rightText.setText(res);
    }

    public void onActionSwitch() {
        if (langFrom.equals("vi")) {
            langFrom = "en";
            langTo = "vi";
            leftLabel.setText("ENGLISH");
            rightLabel.setText("VIETNAMESE");
        } else {
            langFrom = "vi";
            langTo = "en";
            leftLabel.setText("VIETNAMESE");
            rightLabel.setText("ENGLISH");
        }
        leftText.clear();
        rightText.clear();
    }

}
