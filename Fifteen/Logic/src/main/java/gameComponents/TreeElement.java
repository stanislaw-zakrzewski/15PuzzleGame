package gameComponents;

import algorithms.methods.FindingMethods;
import algorithms.methods.OtherMethods;
import algorithms.moveTracking.Moves;

import java.util.ArrayList;
import java.util.List;

public class TreeElement {
    private TreeElement parent;
    private List<TreeElement> children;
    private int[][] boardBefore;
    private int[][] boardAfter;
    private Moves move;
    private boolean wasMoveMade;
    private boolean isChecked;
    private int depth;

    public TreeElement(int[][] board, Moves move, TreeElement parent, int depth) {
        this.parent = parent;
        this.depth = depth;
        boardBefore = new int[board.length][];
        for (int i = 0; i < board.length; i++)
            boardBefore[i] = board[i].clone();
        boardAfter = new int[board.length][];
        for (int i = 0; i < board.length; i++)
            boardAfter[i] = board[i].clone();
        this.move = move;
        wasMoveMade = false;
    }

    public void expand(String moveOrder) {
        children = new ArrayList<>();
        List<Moves> possibleMoves = FindingMethods.getPossibleMoves2(moveOrder, move, boardAfter);
        for (Moves possibleMove : possibleMoves) {
            children.add(new TreeElement(boardAfter, possibleMove, this, depth + 1));
        }
    }

    public List<Moves> getMovesSoFar() {
        if (move == null) {
            return new ArrayList<>();
        } else {
            List<Moves> pom = parent.getMovesSoFar();
            pom.add(move);
            return pom;
        }
    }

    public int[][] getBoardBefore() {
        return boardBefore;
    }

    public int[][] getBoardAfter() {
        return boardAfter;
    }

    public void makeMove() {
        if (!wasMoveMade) {
            OtherMethods.makeMove(boardAfter, move);
            wasMoveMade = true;
        }
    }

    public Moves getMove() {
        return move;
    }

    public TreeElement getParent() {
        return parent;
    }

    public List<TreeElement> getChildren() {
        return children;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public int getDepth() {
        return depth;
    }
}
