package sample;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

public class Controller {
    @FXML
    private ChoiceBox algorithm, modificator;
    @FXML
    private TextArea informations;
    @FXML
    private Button browse, solve;

    public Controller() {
        algorithm = new ChoiceBox();
        algorithm.setItems(FXCollections.observableArrayList("bfs", "dfs", "astr"));
    }

    public void browseFile(ActionEvent event) {

    }

    public void solvePuzzle(ActionEvent event) {

    }
}
