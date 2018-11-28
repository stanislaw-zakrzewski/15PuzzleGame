package algorithms.moveTracking;

public class Move {
    private final Moves move;
    private final int w;
    private final int k;
    private int[][] board;

    Move(Moves move, int[][] newBoard) {
        this.move = move;
        w = newBoard.length;
        k = newBoard[0].length;
        board = new int[w][k];
        setBoard(newBoard);
    }

    void makeMove() {
        int[] blankCoordinates = getCoordinates(0);
        int[] toSwitchCoordinates = getCoordinates(0);

        switch (move) {
            case U:
                toSwitchCoordinates[0] -= 1;
                break;
            case D:
                toSwitchCoordinates[0] += 1;
                break;
            case L:
                toSwitchCoordinates[1] -= 1;
                break;
            case R:
                toSwitchCoordinates[1] += 1;
                break;
        }

        int valueToSwitch = board[toSwitchCoordinates[0]][toSwitchCoordinates[1]];
        board[toSwitchCoordinates[0]][toSwitchCoordinates[1]] = 0;
        board[blankCoordinates[0]][blankCoordinates[1]] = valueToSwitch;
    }

    private int[] getCoordinates(int number) {
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < k; j++) {
                if (board[i][j] == number) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{0, 0};
    }

    public Moves getMove() {
        return move;
    }

    public int[][] getBoard() {
        return board;
    }

    private void setBoard(int[][] newBoard) {
        for (int i = 0; i < w; i++) {
            System.arraycopy(newBoard[i], 0, board[i], 0, k);
        }
    }

    public char getMoveSignature() {
        return move.toString().toCharArray()[0];
    }
}
