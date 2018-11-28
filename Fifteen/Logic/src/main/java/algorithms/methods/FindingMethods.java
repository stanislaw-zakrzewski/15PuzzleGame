package algorithms.methods;

import algorithms.moveTracking.Move;
import algorithms.moveTracking.Moves;

import java.util.ArrayList;
import java.util.List;

public class FindingMethods {
    public static int[] getCoordinates(int[][] board, int number) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == number) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{0, 0};
    }

    public static List<Moves> getPossibleMoves(List<Moves> moves, Move lastMove, int[][] board, boolean[][] blocked) {
        List<Moves> impossibleMoves = new ArrayList<>();
        int[] pom = FindingMethods.getCoordinates(board, 0);
        if (false){//lastMove != null) {
            switch (lastMove.getMove()) {
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
            if (!impossibleMoves.contains(Moves.L)) impossibleMoves.add(Moves.L);
        } else if (blocked != null) if(blocked[pom[0]][pom[1] - 1]) {
            if (!impossibleMoves.contains(Moves.L)) impossibleMoves.add(Moves.L);
        }

        //Check if R move is possible
        if (pom[1] == board.length - 1) {
            if (!impossibleMoves.contains(Moves.R)) impossibleMoves.add(Moves.R);
        } else if  (blocked != null) if(blocked[pom[0]][pom[1] + 1]) {
            if (!impossibleMoves.contains(Moves.R)) impossibleMoves.add(Moves.R);
        }

        //Check if U move is possible
        if (pom[0] == 0) {
            if (!impossibleMoves.contains(Moves.U)) impossibleMoves.add(Moves.U);
        } else if  (blocked != null) if(blocked[pom[0] - 1][pom[1]]) {
            if (!impossibleMoves.contains(Moves.U)) impossibleMoves.add(Moves.U);
        }

        //Check if D move is possible
        if (pom[0] == board[0].length - 1) {
            if (!impossibleMoves.contains(Moves.D)) impossibleMoves.add(Moves.D);
        } else if  (blocked != null) if(blocked[pom[0] + 1][pom[1]]) {
            if (!impossibleMoves.contains(Moves.D)) impossibleMoves.add(Moves.D);
        }

        List<Moves> allMoves = new ArrayList<>();
        allMoves.add(moves.get(0));
        allMoves.add(moves.get(1));
        allMoves.add(moves.get(2));
        allMoves.add(moves.get(3));
        allMoves.removeAll(impossibleMoves);
        return allMoves;
    }
}
