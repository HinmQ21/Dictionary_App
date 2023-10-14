package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class GrammarController implements Initializable {
    @FXML
    private ListView<String> grammarName;

    @FXML
    private TextArea grammarDef;

    private Map<String, String> grammarData = new HashMap<String, String>();

    private ObservableList<String> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:./src/main/resources/data/dict_hh.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "SELECT * FROM grammar";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String name = rs.getString("name");
                String explanation = rs.getString("explanation");
                grammarData.put(name, explanation);
                list.add(name);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        grammarName.setItems(list);
    }

    public void onMouseClickView() {
        String selectGrammar = grammarName.getSelectionModel().getSelectedItem();

        grammarDef.setText(grammarData.get(selectGrammar));
    }
}
