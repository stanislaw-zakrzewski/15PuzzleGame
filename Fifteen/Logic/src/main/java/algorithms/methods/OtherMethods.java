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
}
