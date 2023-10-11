package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private AnchorPane container;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        //showComponent("/fxml/Search.fxml");
    }
    private void showComponent(String path) {
        try {
            AnchorPane component = FXMLLoader.load(getClass().getResource(path));
            container.getChildren().clear();
            container.getChildren().add(component);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onActionSearch () {
        showComponent("/fxml/Search.fxml");
    }

    public void onActionAdd() {
        showComponent("/fxml/Add.fxml");
    }

    public void onActionTranslate() {
        showComponent("/fxml/Translate.fxml");
    }

    public void onActionGrammar() {
        showComponent("/fxml/Grammar.fxml");
    }

    public void onActionGame() {
        showComponent("/fxml/Game.fxml");
    }
}
