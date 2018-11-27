package sample;

import algorithms.Algorithm;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import mapGeneration.BoardGenerator;

import java.util.HashMap;
import java.util.Map;

public class Controller {
    @FXML
    private GridPane grid;


    @FXML
    private Button start;
    @FXML
    private Button solve;

    private Map<Button, int[]> buttonToPosition;
    private int[][] board;
    private Button[][] buttons;
    private int size;
    private BoardGenerator boardGenerator;
    private Algorithm algorithm;
    private MoveHandler moveHandler;

    private void updateValues() {
        buttonToPosition.keySet().forEach(k -> k.setText(Integer.toString(board[buttonToPosition.get(k)[0]][buttonToPosition.get(k)[1]])));
    }

    public void start(ActionEvent event) {
        buttonToPosition = new HashMap<>();
        boardGenerator = new BoardGenerator();
        board = boardGenerator.GenerateNew(4);
        size = board.length;
        buttons = new Button[size][size];
        algorithm = new Algorithm(board);
        moveHandler = new MoveHandler(buttons, grid);

        for (int i = 0; i < size - 3; i++) {
            grid.addColumn(1);
            grid.addRow(1);
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Button b = new Button(Integer.toString(board[i][j]));
                b.setId(Integer.toString(board[i][j]));
                if (board[i][j] == size * size) {
                    b.setText("");
                    b.setBackground(Background.EMPTY);
                }
                b.setPrefSize(600 / size, 600 / size);
                grid.add(b, i, j);
                buttons[i][j] = b;
            }
        }
    }

    public void solve(ActionEvent event) {
        algorithm.start();

        ValuesUpdater vu = new ValuesUpdater(buttons,algorithm.getMoves());
        vu.run();

        //Thread valuesUpdater = new Thread(new ValuesUpdater(buttons, algorithm.getMoves()));
        //valuesUpdater.start();
    }

    public void ButtonClick(ActionEvent event) {
        updateValues();
    }
}
