package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.HashMap;
import java.util.Map;

public class Controller {
    @FXML
    private Button b00;
    @FXML
    private Button b10;
    @FXML
    private Button b20;
    @FXML
    private Button b30;
    @FXML
    private Button b01;
    @FXML
    private Button b11;
    @FXML
    private Button b21;
    @FXML
    private Button b31;
    @FXML
    private Button b02;
    @FXML
    private Button b12;
    @FXML
    private Button b22;
    @FXML
    private Button b32;
    @FXML
    private Button b03;
    @FXML
    private Button b13;
    @FXML
    private Button b23;
    @FXML
    private Button b33;

    @FXML
    private Button start;

    private Map<Button, int[]> buttonToPosition;
    private Board board;
    private MoveHandler moveHandler;

    private void updateValues() {
        buttonToPosition.keySet().forEach(k -> k.setText(Integer.toString(board.getElement(buttonToPosition.get(k)[0], buttonToPosition.get(k)[1]))));
    }

    public void Start(ActionEvent event) {
        board = new Board(4);
        moveHandler = new MoveHandler(board);
        buttonToPosition = new HashMap<>();
        buttonToPosition.put(b00, new int[]{0,0});
        buttonToPosition.put(b10, new int[]{1,0});
        buttonToPosition.put(b20, new int[]{2,0});
        buttonToPosition.put(b30, new int[]{3,0});
        buttonToPosition.put(b01, new int[]{0,1});
        buttonToPosition.put(b11, new int[]{1,1});
        buttonToPosition.put(b21, new int[]{2,1});
        buttonToPosition.put(b31, new int[]{3,1});
        buttonToPosition.put(b02, new int[]{0,2});
        buttonToPosition.put(b12, new int[]{1,2});
        buttonToPosition.put(b22, new int[]{2,2});
        buttonToPosition.put(b32, new int[]{3,2});
        buttonToPosition.put(b03, new int[]{0,3});
        buttonToPosition.put(b13, new int[]{1,3});
        buttonToPosition.put(b23, new int[]{2,3});
        buttonToPosition.put(b33, new int[]{3,3});
        updateValues();
    }

    public void ButtonClick(ActionEvent event) {
        moveHandler.move(buttonToPosition.get(event.getSource())[0], buttonToPosition.get(event.getSource())[1]);
        updateValues();
    }
}
