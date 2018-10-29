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
        int[] blankCoordinates = getCoordinates(16);
        int[] toSwitchCoordinates = getCoordinates(16);
        if (move.equals(Moves.Up)) toSwitchCoordinates[1] -= 1;
        if (move.equals(Moves.Down)) toSwitchCoordinates[1] += 1;
        if (move.equals(Moves.Left)) toSwitchCoordinates[0] -= 1;
        if (move.equals(Moves.Right)) toSwitchCoordinates[0] += 1;
        if(toSwitchCoordinates[1] > 3 || toSwitchCoordinates[0] > 3) {

        } else {
            int valueToSwitch = board[toSwitchCoordinates[0]][toSwitchCoordinates[1]];
            board[toSwitchCoordinates[0]][toSwitchCoordinates[1]] = 16;
            board[blankCoordinates[0]][blankCoordinates[1]] = valueToSwitch;
        }
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
        for (int i = 0; i < newBoard.length; i++) {
            for (int j = 0; j < newBoard.length; j++) {
                board[i][j] = newBoard[i][j];
            }
        }
    }
}
