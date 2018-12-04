package algorithms.methods;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CheckingMethods {
    public static boolean isSolved(int[][] board) {
        System.out.println();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (i == board.length - 1 && j == board[0].length - 1) {
                    if (board[i][j] != 0) {
                        System.out.println("oko" + i + " " + j + " " + (i * board[0].length + j) + " " + board[i][j]);
                        return false;
                    }
                } else if (board[i][j] != (i * board[0].length + j + 1)) {
                    System.out.println(i + " " + j + " " + (i * board[0].length + j + 1) + " " + board[i][j]);
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isFileCorrect(File file) {
        if(!file.getName().endsWith(".txt")) return false;
        int w;
        int k;
        try {
            Scanner scanner = new Scanner(file);
            if(scanner.hasNextInt()) {
                w = scanner.nextInt();
            } else {
                return false;
            }
            if(scanner.hasNextInt()) {
                k = scanner.nextInt();
            } else {
                return false;
            }
            for(int i = 0; i < w*k; i++) {
                if(scanner.hasNextInt()) {
                    scanner.nextInt();
                } else {
                    return false;
                }
            }
            if(scanner.hasNext()) {
                return false;
            }
        } catch (FileNotFoundException e) {
            return false;
        }
        return true;
    }
}
