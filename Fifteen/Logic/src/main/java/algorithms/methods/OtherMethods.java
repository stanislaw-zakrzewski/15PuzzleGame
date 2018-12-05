package algorithms.methods;

import algorithms.moveTracking.Moves;

import java.util.ArrayList;
import java.util.List;

public class OtherMethods {
    public static List<Moves> arrange(String order) {
        List<Moves> moves = new ArrayList<>();
        char[] o = order.toCharArray();
        for(char c : o) {
            switch(c) {
                case 'u':
                    moves.add(Moves.U);
                    break;
                case 'd':
                    moves.add(Moves.D);
                    break;
                case 'l':
                    moves.add(Moves.L);
                    break;
                case 'r':
                    moves.add(Moves.R);
                    break;
            }
        }
        return moves;
    }

    public static void makeMove(int[][] board, Moves move) {
        int[] blankCoordinates = FindingMethods.getCoordinates(board,0);
        int[] toSwitchCoordinates = FindingMethods.getCoordinates(board, 0);

        switch (move) {
            case U:
                toSwitchCoordinates[0] -= 1;
                break;
            case D:
                toSwitchCoordinates[0] += 1;
                break;
            case L:
                toSwitchCoordinates[1] -= 1;
                break;
            case R:
                toSwitchCoordinates[1] += 1;
                break;
        }

        int valueToSwitch = board[toSwitchCoordinates[0]][toSwitchCoordinates[1]];
        board[toSwitchCoordinates[0]][toSwitchCoordinates[1]] = 0;
        board[blankCoordinates[0]][blankCoordinates[1]] = valueToSwitch;
    }
}
