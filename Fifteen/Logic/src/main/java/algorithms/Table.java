package algorithms;

public class Table {
    int[][] table;

    public Table() {
        table = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
    }

    public int get(int x, int y) {
        return table[y][x];
    }
}
