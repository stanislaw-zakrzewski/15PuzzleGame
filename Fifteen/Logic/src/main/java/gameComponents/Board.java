package gameComponents;

public class Board {
    private int[][] board;
    private int w;
    private int k;

    public Board(int[][] board, int w, int k) {
        this.board = board;
        this.w = w;
        this.k = k;
    }

    public int[][] getBoard() {
        return board;
    }

    public int getW() {
        return w;
    }

    public int getK() {
        return k;
    }
}
