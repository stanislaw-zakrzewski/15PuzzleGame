package algorithms;

import mapGeneration.BoardGenerator;

public class Main {
    public static void main(String[] args) {
        BoardGenerator boardGenerator = new BoardGenerator();
        Algorithm algorithm = new Algorithm(boardGenerator.GenerateNew(4));
        algorithm.start();
    }
}
