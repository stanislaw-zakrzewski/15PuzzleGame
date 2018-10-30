package main;

import algorithms.Algorithm;
import mapGeneration.BoardGenerator;

public class Main {
    public static void main(String[] args) {
        BoardGenerator boardGenerator = new BoardGenerator();
        Algorithm algorithm = new Algorithm(boardGenerator.GenerateNew(5));
        algorithm.start();
    }
}
