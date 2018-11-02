package mapGeneration;

import java.util.Collections;
import java.util.Stack;

public class BoardGenerator {
    private int size;
    private Stack<Integer> numbersToFillBoard;
    private int[][] board;
    private int[] flatBoard;
    private int[][] shaded;

    public int[][] GenerateNew(int size) {
        this.size = size;
        board = new int[size][size];
        flatBoard = new int[size*size];
        shaded = new int[size][size];
        if (size < 3) {
            throw new IllegalArgumentException("Board must be at least 3x3!");
        }
        int last = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                shaded[i][j] = last;
                last = last == 1 ? 0 : 1;
            }
        }
        do {
            numbersToFillBoard = new Stack<>();
            for (int i = 1; i <= size * size; i++) {
                numbersToFillBoard.push(i);
            }
            Collections.shuffle(numbersToFillBoard);
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    int pom = numbersToFillBoard.pop();
                    board[i][j] = pom;
                    flatBoard[j*size+i] = pom;
                }
            }
        } while (!isSolvable(flatBoard));
        return board;
    }

    private boolean isSolvable(int[] puzzle)
    {
        int parity = 0;
        int gridWidth = (int) Math.sqrt(puzzle.length);
        int row = 0; // the current row we are on
        int blankRow = 0; // the row with the blank tile

        for (int i = 0; i < puzzle.length; i++)
        {
            if (i % gridWidth == 0) { // advance to next row
                row++;
            }
            if (puzzle[i] == size*size) { // the blank tile
                blankRow = row; // save the row on which encountered
                continue;
            }
            for (int j = i + 1; j < puzzle.length; j++)
            {
                if (puzzle[i] > puzzle[j] && puzzle[j] != size*size)
                {
                    parity++;
                }
            }
        }

        if (gridWidth % 2 == 0) { // even grid
            if (blankRow % 2 == 0) { // blank on odd row; counting from bottom
                return parity % 2 == 0;
            } else { // blank on even row; counting from bottom
                return parity % 2 != 0;
            }
        } else { // odd grid
            return parity % 2 == 0;
        }
    }
}
