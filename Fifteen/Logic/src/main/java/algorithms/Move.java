package algorithms;

public class Move {
    private final Moves move;
    private final int size;
    private int[][] board;

    public Move(Moves move, int[][] newBoard) {
        this.move = move;
        size = newBoard.length;
        board = new int[size][size];
        setBoard(newBoard);
    }

    public void makeMove() {
        int[] blankCoordinates = getCoordinates(size * size);
        int[] toSwitchCoordinates = getCoordinates(size * size);

        switch (move) {
            case Up:
                toSwitchCoordinates[0] -= 1;
                break;
            case Down:
                toSwitchCoordinates[0] += 1;
                break;
            case Left:
                toSwitchCoordinates[1] -= 1;
                break;
            case Right:
                toSwitchCoordinates[1] += 1;
                break;
        }

        int valueToSwitch = board[toSwitchCoordinates[0]][toSwitchCoordinates[1]];
        board[toSwitchCoordinates[0]][toSwitchCoordinates[1]] = size * size;
        board[blankCoordinates[0]][blankCoordinates[1]] = valueToSwitch;
    }

    private int[] getCoordinates(int number) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
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
        for (int i = 0; i < size; i++) {
            System.arraycopy(newBoard[i], 0, board[i], 0, size);
        }
    }
}
