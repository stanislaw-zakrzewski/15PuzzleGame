package main;

import algorithms.AAlgorithm;
import algorithms.BFS;
import algorithms.DFS;

public class Main {
    public static void main(String[] args) {
        AAlgorithm algorithm = new DFS("rdul", "Files\\sample.txt");
        long time = System.nanoTime();
        algorithm.solve();
        algorithm.setTime(System.nanoTime() - time);
        algorithm.saveStatsFile();
        algorithm.saveOutputFile();
    }
}
