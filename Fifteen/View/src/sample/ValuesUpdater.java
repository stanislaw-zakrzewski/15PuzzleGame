package sample;

import algorithms.Moves;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;

import java.util.List;

public class ValuesUpdater implements Runnable {
    private Button[][] buttons;
    private List<Moves> moves;
    private int size;

    public ValuesUpdater(Button[][] buttons, List<Moves> moves) {
        this.buttons = buttons;
        this.moves = moves;
        size = buttons.length;

    }

    @Override
    public void run() {
        for (Moves m : moves) {
            int[] pom1 = getCoordinates(size * size);
            int[] pom2 = new int[]{pom1[0], pom1[1]};
            switch (m) {
                case U:
                    pom2[0] -= 1;
                    break;
                case D:
                    pom2[0] += 1;
                    break;
                case L:
                    pom2[1] -= 1;
                    break;
                case R:
                    pom2[1] += 1;
                    break;
            }
            String valueToSwitch = buttons[pom2[0]][pom2[1]].getText();

            buttons[pom1[0]][pom1[1]].setText(valueToSwitch);
            buttons[pom1[0]][pom1[1]].setId(valueToSwitch);
            buttons[pom1[0]][pom1[1]].setBackground(buttons[pom2[0]][pom2[1]].getBackground());
            buttons[pom2[0]][pom2[1]].setText("");
            buttons[pom2[0]][pom2[1]].setId(Integer.toString(size * size));
            buttons[pom2[0]][pom2[1]].setBackground(Background.EMPTY);
        }
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
