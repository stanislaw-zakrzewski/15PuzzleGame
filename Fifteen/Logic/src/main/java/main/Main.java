package main;

import algorithms.Algorithm;
import mapGeneration.BoardGenerator;

public class Main {
    public static void main(String[] args) {
        BoardGenerator boardGenerator = new BoardGenerator();
        Algorithm algorithm = new Algorithm(boardGenerator.GenerateNew(7));

        long time = System.nanoTime();
        algorithm.start();
        time = System.nanoTime() - time;
        printTime(time);
    }

    private static void printTime(long time) {
        int minutes = (int) ((time / 1000000000) / 60);
        int seconds = (int) (time / 1000000000) - minutes * 60;
        int miliseconds = (int) (time / 1000000) - minutes * 60000 - seconds * 1000;
        System.out.print("Time: ");
        if (minutes > 0) {
            System.out.print(minutes + " minutes, ");
        }
        if (seconds > 0) {
            System.out.print(seconds + " seconds, ");
        }
        if (miliseconds > 0) {
            System.out.println(miliseconds + " miniseconds");
        }
    }
}
