package algorithms.methods;

public class CostCalculation {
    public static int calculateHamm(int[][] board) {
        int cost = 0;
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(board[i][j] != 0) {
                    if(board[i][j] != i*board[0].length + j) {
                        cost++;
                    }
                }
            }
        }
        return cost;
    }

    public static int calculateMann(int[][] board) {
        int manhattanDistance = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                manhattanDistance += getManhattanDistanceForField(i, j, board);
            }
        }
        return manhattanDistance;
    }

    private static int getManhattanDistanceForField(int x, int y, int[][] board) {
        if (board[x][y] == 0) {
            return Math.abs(board[0].length - y - 1) + Math.abs(board.length - x - 1);
        }
        int correctX = (board[x][y] - 1) / board[0].length;
        int correctY = (board[x][y] - 1) % board[0].length;
        if (correctY == -1) {
            correctY = board[0].length - 1;
        }
        return Math.abs(correctX - x) + Math.abs(correctY - y);
    }
}
