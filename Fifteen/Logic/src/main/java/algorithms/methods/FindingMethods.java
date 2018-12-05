package algorithms.methods;

import algorithms.Moves;

import java.util.ArrayList;
import java.util.List;

public class FindingMethods {
    static int[] getCoordinates(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 0) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{0, 0};
    }

    public static List<Moves> getPossibleMoves2(String movesOrder, Moves lastMove, int[][] board) {
        List<Moves> impossibleMoves = new ArrayList<>();
        int[] pom = FindingMethods.getCoordinates(board);
        if (lastMove != null) {
            switch (lastMove) {
                case U:
                    impossibleMoves.add(Moves.D);
                    break;
                case D:
                    impossibleMoves.add(Moves.U);
                    break;
                case L:
                    impossibleMoves.add(Moves.R);
                    break;
                case R:
                    impossibleMoves.add(Moves.L);
                    break;
            }
        }

        //Check if L move is possible
        if (pom[1] == 0) {
            impossibleMoves.add(Moves.L);
        }

        //Check if R move is possible
        if (pom[1] == board.length - 1) {
            impossibleMoves.add(Moves.R);
        }

        //Check if U move is possible
        if (pom[0] == 0) {
            impossibleMoves.add(Moves.U);
        }

        //Check if D move is possible
        if (pom[0] == board[0].length - 1) {
            impossibleMoves.add(Moves.D);
        }

        List<Moves> allMoves = OtherMethods.arrange(movesOrder);
        allMoves.removeAll(impossibleMoves);
        return allMoves;
    }
}
