package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import mapGeneration.BoardGenerator;

import java.util.HashMap;
import java.util.Map;

public class Controller {
    @FXML
    private GridPane gp;


    @FXML
    private Button start;
    @FXML
    private Button solve;

    private Map<Button, int[]> buttonToPosition;
    private int[][] board;
    private BoardGenerator boardGenerator;

    private void updateValues() {
        buttonToPosition.keySet().forEach(k -> k.setText(Integer.toString(board[buttonToPosition.get(k)[0]][buttonToPosition.get(k)[1]])));
    }

    public void Start(ActionEvent event) {
        buttonToPosition = new HashMap<>();
        Button button = new Button("oko");
        gp = new GridPane();
        gp.add(button,0,0);
        boardGenerator = new BoardGenerator();
        board = boardGenerator.GenerateNew(4);
        updateValues();
    }

    public void ButtonClick(ActionEvent event) {
        updateValues();
    }
}
