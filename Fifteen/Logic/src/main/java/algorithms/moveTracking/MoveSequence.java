package algorithms.moveTracking;

import java.util.ArrayList;
import java.util.List;

public class MoveSequence {
    private List<Move> movesSoFar;
    private int[][] board;

    public MoveSequence(Moves move, int[][] board) {
        Move nextMove = new Move(move, board);
        nextMove.makeMove();
        movesSoFar = new ArrayList<>();
        movesSoFar.add(nextMove);
        this.board = nextMove.getBoard();
    }

    public MoveSequence(MoveSequence parent, Moves move) {
        board = parent.getBoardAfter();
        movesSoFar = new ArrayList<>();
        movesSoFar.addAll(parent.getMovesSoFar());
        Move nextMove = new Move(move, board);
        nextMove.makeMove();
        movesSoFar.add(nextMove);
        board = nextMove.getBoard();
    }

    public List<Move> getMovesSoFar() {
        return movesSoFar;
    }

    public int[][] getBoardAfter() {
        return movesSoFar.get(movesSoFar.size()-1).getBoard();
    }
}
