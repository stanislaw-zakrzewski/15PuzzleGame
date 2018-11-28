package fileOperations;

import gameComponents.Board;

import java.io.File;
import java.util.Scanner;

public class ReadFile {
    public static Board getBoardFromFile(String path) {
        int w, k;
        int[][] b;
        Board board = null;
        try {
            Scanner scanner = new Scanner(new File(path));

            if (scanner.hasNext()) {
                w = scanner.nextInt();
            } else {
                return null;
            }

            if (scanner.hasNext()) {
                k = scanner.nextInt();
            } else {
                return null;
            }

            b = new int[w][k];
            for (int i = 0; i < w; i++) {
                for (int j = 0; j < k; j++) {
                    if (scanner.hasNext()) {
                        b[i][j] = scanner.nextInt();
                    } else {
                        return null;
                    }
                }
            }

            board = new Board(b, w, k);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return board;
    }
}
