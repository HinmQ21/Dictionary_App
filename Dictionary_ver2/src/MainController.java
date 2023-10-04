import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    private DictionaryManagement dictionaryManagement = new DictionaryManagement(new Dictionary());

    @FXML
    private void openAddWordWindow(ActionEvent event) {
        openWindow("res\\fxml\\addWord.fxml", "Add Word");
    }

    @FXML
    private void openRemoveWordWindow(ActionEvent event) {
        openWindow("res\\fxml\\removeWord.fxml", "Remove Word");
    }

    @FXML
    private void openUpdateWordWindow(ActionEvent event) {
        openWindow("res\\fxml\\updateWord.fxml", "Update Word");
    }

    private void openWindow(String fxmlFile, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle(title);
            stage.setScene(new Scene(root));

            if (fxmlFile.equals("res\\fxml\\addWord.fxml")) {
                AddWordController addWordController = loader.getController();
                addWordController.setDictionaryManagement(dictionaryManagement);
            } else if (fxmlFile.equals("res\\fxml\\removeWord.fxml")) {
                RemoveWordController removeWordController = loader.getController();
                removeWordController.setDictionaryManagement(dictionaryManagement);
            } else if (fxmlFile.equals("res\\fxml\\updateWord.fxml")) {
                UpdateWordController updateWordController = loader.getController();
                updateWordController.setDictionaryManagement(dictionaryManagement);
            }

            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void exitApplication(ActionEvent event) {
        Platform.exit();
    }
}
