package algorithms;


import algorithms.methods.FindingMethods;
import algorithms.methods.OtherMethods;
import algorithms.moveTracking.Moves;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public class Algorithm {/*
    private int[][] board;
    private final int w;
    private final int k;
    private List<Move> movesSoFar;
    private GoalSequence goals;

    private boolean[][] blocked;

    public Algorithm(int[][] board) {
        w = board.length;
        k = board[0].length;
        goals = new GoalSequence(w, k);
        this.board = board;
        movesSoFar = new ArrayList<>();
        blocked = new boolean[w][k];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < k; j++) {
                blocked[i][j] = false;
            }
        }
    }

    public void start() {
        printBoard(board);
        //int lastMovesSize = 0;
        List<MoveSequence> moveSequences;
        List<MoveSequence> newSequences;
        List<Moves> possibleMoves;
        List<Moves> movesOrder = OtherMethods.arrange("udlr");
        Optional<MoveSequence> completesGoal;

        while (true) {
            //Checks if any goal has been reached if so changes current goal set to next goal set and changes blocked fields
            while (completesGoals(board)) {
                if (goals.hasNext()) {
                    //Changes blocked fields
                    changeBlockedFields();


                    //printBoard(board);
                    //lastMovesSize = movesSoFar.size();

                    //Sets current goal to next goal
                    goals.next();
                } else {
                    //After every goal has been reached stops algorithm
                    break;
                }
            }
            if (!goals.hasNext()) {
                break;
            }

            moveSequences = new ArrayList<>();
            if (movesSoFar.size() == 0) {
                possibleMoves = FindingMethods.getPossibleMoves(movesOrder, null, board, blocked);
            } else {
                possibleMoves = FindingMethods.getPossibleMoves(movesOrder, movesSoFar.get(movesSoFar.size() - 1), board, blocked);
            }
            for (Moves m : possibleMoves) {
                moveSequences.add(new MoveSequence(m, board));
            }

            while (moveSequences.stream().noneMatch(ms -> completesGoals(ms.getBoardAfter()))) {
                final OptionalInt closestDistance;
                newSequences = new ArrayList<>();
                for (MoveSequence bs : moveSequences) {
                    possibleMoves = FindingMethods.getPossibleMoves(movesOrder, bs.getMovesSoFar().get(bs.getMovesSoFar().size() - 1), bs.getBoardAfter(), blocked);
                    for (Moves move : possibleMoves) {
                        newSequences.add(new MoveSequence(bs, move));
                    }
                }
                moveSequences = newSequences;
                closestDistance = moveSequences.stream().mapToInt(v -> distanceToGoals(v.getBoardAfter())).min();
                if (closestDistance.isPresent()) {
                    newSequences = new ArrayList<>();
                    for (MoveSequence ms : moveSequences) {
                        if (distanceToGoals(ms.getBoardAfter()) <= closestDistance.getAsInt() + 1) {
                            newSequences.add(ms);
                        }
                    }
                    moveSequences = newSequences;
                }
            }

            completesGoal = moveSequences.stream().filter(ms -> completesGoals(ms.getBoardAfter())).findFirst();
            completesGoal.ifPresent(moveSequence -> movesSoFar.addAll(moveSequence.getMovesSoFar()));
            if (movesSoFar.size() > 0) {
                board = movesSoFar.get(movesSoFar.size() - 1).getBoard();
            }
            printBoard(board);
        }
        printBoard(board);
    }

    private boolean completesGoals(int[][] board) {
        if (goals.getCurrentGoals().getNumberToGetTo() > 0) {
            return distanceToGoals(board) <= 1;
        } else {
            for (Goal g : goals.getCurrentGoals().getGoals()) {
                if (g.isValueDesired()) {
                    if (board[g.getX()][g.getY()] != g.getValue()) {
                        return false;
                    }
                } else if (board[g.getX()][g.getY()] == g.getValue()) {
                    return false;
                }
            }
        }
        return true;
    }

    private int distanceToGoals(int[][] board) {
        int pom1 = 0;
        int pom2 = 0;
        if (goals.getCurrentGoals().getNumberToGetTo() > 0) {
            int[] pom3 = FindingMethods.getCoordinates(board, w * k);
            pom1 = calculateDistance(board, goals.getCurrentGoals().getNumberToGetTo(), pom3);
        } else {
            for (Goal g : goals.getCurrentGoals().getGoals()) {
                if (g.isValueDesired()) {
                    pom1 += calculateDistance(board, g.getValue(), new int[]{g.getX(), g.getY()});
                } else {
                    if (board[g.getX()][g.getY()] == g.getValue()) {
                        pom2 = w * w * w;
                    }
                }
            }
        }
        return pom1 + pom2;
    }

    private int calculateDistance(int[][] board, int value, int[] desiredPosition) {
        int[] pom1 = FindingMethods.getCoordinates(board, value);
        int pom2 = 0;
        pom2 += Math.abs(desiredPosition[0] - pom1[0]);
        pom2 += Math.abs(desiredPosition[1] - pom1[1]);
        return pom2;
    }

    private void changeBlockedFields() {
        for (int[] position : goals.getCurrentGoals().getFieldsToBlock()) {
            blocked[position[0]][position[1]] = true;
        }
        for (int[] position : goals.getCurrentGoals().getFieldsToUnlock()) {
            blocked[position[0]][position[1]] = false;
        }
    }

    public int[][] getBoard() {
        return board;
    }

    public List<Moves> getMoves() {
        List<Moves> pom = new ArrayList<>();
        for (Move m : movesSoFar) {
            pom.add(m.getMove());
        }
        return pom;
    }

    private void printBoard(int[][] board) {
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < k; j++) {
                int pom = board[j][i];
                if (pom == w * k) {
                    System.out.print("   ");
                } else if (pom > 9) {
                    System.out.print(pom + " ");
                } else {
                    System.out.print(" " + pom + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }*/
}
