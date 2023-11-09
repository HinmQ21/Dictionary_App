    package Controller;

import DictionaryComandLine.DictionaryManagement;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

    public class AddController {
    @FXML
    private TextField wordField;

    @FXML
    private TextField pronounceField;

    @FXML
    private TextArea defArea;

    @FXML
    private Label addNotification;

    private void disappearLable(Label lable) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
            lable.setText("");
        }));
        timeline.play();
    }

    public void onActionSave() {
        String word = wordField.getText().toLowerCase();
        String pronounce = pronounceField.getText();
        String def = defArea.getText();

        boolean hasWord = DictionaryManagement.dbHasWord(word, "av");

        if (word.trim().equals("") || def.trim().equals("")) {
            addNotification.setText("Không được để trống từ và nghĩa!");
            disappearLable(addNotification);
        } else if (hasWord) {
            addNotification.setText("Từ này đã tồn tại!");
            disappearLable(addNotification);
        } else {
            DictionaryManagement.dbAdd(word, pronounce, def, "av");
            addNotification.setText("Thêm từ thành công!" + "\nCảm ơn sự đóng góp của bạn.");
            disappearLable(addNotification);
        }
        wordField.clear();
        pronounceField.clear();
        defArea.clear();
    }
}
