package algorithms;

public class Main {
    public static void main(String[] args) {
        int[][] plansza = new int[][]{{11, 6, 16, 8}, {15, 4, 12, 7}, {5, 9, 3, 2}, {1, 14, 10, 13}};
        //int[][] plansza = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 16, 15}};
        Algorithm algorithm = new Algorithm(plansza);
        System.out.println(algorithm.IsSolvable());
        algorithm.start();
        //Table table = new Table();
        //System.out.println(table.get(0,1));
    }
}
