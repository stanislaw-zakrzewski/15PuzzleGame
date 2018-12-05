package main;

import algorithms.AAlgorithm;
import algorithms.ASTR;
import algorithms.BFS;
import algorithms.DFS;

public class Main {
    public static void main(String[] args) {
        AAlgorithm algorithm = null;
        args = new String[5];
        args[0] = "bfs";
        args[1] = "rdlu";
        args[2] = "Files\\sample.txt";
        args[3] = "solution.txt";
        args[4] = "stats.txt";
        switch(args[0]) {
            case "bfs":
                algorithm = new BFS(args[1], args[2], args[3], args[4]);
                break;
            case "dfs":
                algorithm = new DFS(args[1], args[2], args[3], args[4]);
                break;
            case "astr":
                algorithm = new ASTR(args[1], args[2], args[3], args[4]);
                break;
            default:
                return;
        }

        long time = System.nanoTime();
        algorithm.solve();
        algorithm.setTime(System.nanoTime() - time);
        algorithm.saveStatsFile();
        algorithm.saveOutputFile();
    }
}
