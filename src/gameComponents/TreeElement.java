package gameComponents;

import algorithms.Moves;
import algorithms.methods.CostCalculation;
import algorithms.methods.FindingMethods;
import algorithms.methods.OtherMethods;

import java.util.ArrayList;
import java.util.List;

public class TreeElement {
    private TreeElement parent;
    private List<TreeElement> children;
    private int[][] boardAfter;
    private Moves move;
    private int depth;

    public TreeElement(int[][] board, Moves move, TreeElement parent, int depth) {
        this.parent = parent;
        this.depth = depth;
        boardAfter = new int[board.length][];
        for (int i = 0; i < board.length; i++)
            boardAfter[i] = board[i].clone();
        this.move = move;
        if (move != null) {
            OtherMethods.makeMove(boardAfter, move);
        }
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

    public int costHamm() {
        return CostCalculation.calculateHamm(boardAfter) + gCost();
    }

    public int costMann() {
        return CostCalculation.calculateMann(boardAfter) + gCost();
    }

    private int gCost() {
        return depth;
    }

    public int[][] getBoardAfter() {
        return boardAfter;
    }

    public List<TreeElement> getChildren() {
        return children;
    }

    public int getDepth() {
        return depth;
    }
}
