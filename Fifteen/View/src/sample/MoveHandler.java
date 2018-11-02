package sample;

import algorithms.moveTracking.Moves;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class MoveHandler {
    private Button[][] buttons;
    private GridPane grid;
    private int size;

    public MoveHandler(Button[][] buttons, GridPane grid) {
        this.buttons = buttons;
        this.grid = grid;
        size = buttons.length;
    }

    public void makeMove(Moves move) {
        int[] pom1 = getCoordinates(size * size);
        int[] pom2 = new int[]{pom1[0], pom1[1]};
        switch (move) {
            case Up:
                pom2[0] -= 1;
                break;
            case Down:
                pom2[0] += 1;
                break;
            case Left:
                pom2[1] -= 1;
                break;
            case Right:
                pom2[1] += 1;
                break;
        }
        Button b1 = buttons[pom1[0]][pom1[1]];
        Button b2 = buttons[pom2[0]][pom2[1]];
        grid.getChildren().remove(b1);
        grid.getChildren().remove(b2);

        grid.add(b1, pom2[0], pom2[1]);
        grid.add(b2, pom1[0], pom1[1]);
    }

    private int[] getCoordinates(int number) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (buttons[i][j].getId().equals(Integer.toString(number))) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{0, 0};
    }
}
