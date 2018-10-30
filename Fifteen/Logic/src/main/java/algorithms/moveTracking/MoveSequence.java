package algorithms.moveTracking;

import java.util.ArrayList;
import java.util.List;

public class MoveSequence {
    private List<Move> movesSoFar;

    public MoveSequence(Moves move, int[][] board) {
        Move nextMove = new Move(move, board);
        nextMove.makeMove();
        movesSoFar = new ArrayList<>();
        movesSoFar.add(nextMove);
    }

    public MoveSequence(MoveSequence parent, Moves move) {
        movesSoFar = new ArrayList<>();
        movesSoFar.addAll(parent.getMovesSoFar());
        Move nextMove = new Move(move, parent.getBoardAfter());
        nextMove.makeMove();
        movesSoFar.add(nextMove);
    }

    public List<Move> getMovesSoFar() {
        return movesSoFar;
    }

    public int[][] getBoardAfter() {
        return movesSoFar.get(movesSoFar.size()-1).getBoard();
    }
}
