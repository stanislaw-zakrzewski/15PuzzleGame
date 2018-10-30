package algorithms;

import algorithms.goalSystem.Goal;
import algorithms.goalSystem.GoalSequence;
import algorithms.moveTracking.Move;
import algorithms.moveTracking.MoveSequence;
import algorithms.moveTracking.Moves;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Algorithm {
    private int[][] board;
    private final int size;
    private List<Move> movesSoFar;
    private GoalSequence goals;

    private boolean[][] blocked;

    public Algorithm(int[][] board) {
        size = board.length;
        goals = new GoalSequence(size);
        this.board = board;
        movesSoFar = new ArrayList<>();
        blocked = new boolean[size][size];
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                blocked[i][j] = false;
            }
        }
    }

    public void start() {
        printBoard(board);
        int lastMovesSize = 0;
        List<MoveSequence> moveSequences = new ArrayList<>();
        List<Moves> possibleMoves;
        Optional<MoveSequence> completesGoal;

        if (movesSoFar.size() == 0) {
            possibleMoves = getPossibleMoves(null, board);
        } else {
            possibleMoves = getPossibleMoves(movesSoFar.get(movesSoFar.size() - 1), board);
        }
        for (Moves m : possibleMoves) {
            moveSequences.add(new MoveSequence(m, board));
        }

        while (true) {
            List<MoveSequence> newSequences = new ArrayList<>();
            for (MoveSequence bs : moveSequences) {
                possibleMoves = getPossibleMoves(null, bs.getBoardAfter());
                for (Moves move : possibleMoves) {
                    newSequences.add(new MoveSequence(bs, move));
                }
            }
            while (newSequences.stream().noneMatch(ms -> completesGoals(ms.getBoardAfter()))) {
                newSequences = new ArrayList<>();
                for (MoveSequence bs : moveSequences) {
                    possibleMoves = getPossibleMoves(bs.getMovesSoFar().get(bs.getMovesSoFar().size() - 1), bs.getBoardAfter());
                    for (Moves move : possibleMoves) {
                        newSequences.add(new MoveSequence(bs, move));
                    }
                }
                moveSequences = newSequences;
            }
            completesGoal = moveSequences.stream().filter(ms -> completesGoals(ms.getBoardAfter())).findFirst();
            completesGoal.ifPresent(moveSequence -> movesSoFar.addAll(moveSequence.getMovesSoFar()));
            board = movesSoFar.get(movesSoFar.size() - 1).getBoard();
            changeBlockedFields();

            System.out.println("Moves: " + movesSoFar.size() + "    Difference: " + (movesSoFar.size()-lastMovesSize));
            lastMovesSize = movesSoFar.size();
            printBoard(board);

            if (goals.hasNext()) {
                goals.next();
            } else {
                break;
            }
            moveSequences = new ArrayList<>();
            possibleMoves = getPossibleMoves(movesSoFar.get(movesSoFar.size() - 1), board);
            for (Moves m : possibleMoves) {
                moveSequences.add(new MoveSequence(m, board));
            }
        }
    }

    private boolean completesGoals(int[][] board) {
        for (Goal g : goals.getCurrentGoals().getGoals()) {
            if (board[g.getX()][g.getY()] != g.getValue()) {
                return false;
            }
        }
        return true;
    }

    private void changeBlockedFields() {
        for (int[] position : goals.getCurrentGoals().getFieldsToBlock()) {
            blocked[position[0]][position[1]] = true;
        }
        for (int[] position : goals.getCurrentGoals().getFieldsToUnlock()) {
            blocked[position[0]][position[1]] = false;
        }
    }

    private int[] getCoordinates(int[][] board, int number) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == number) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{0, 0};
    }

    private List<Moves> getPossibleMoves(Move lastMove, int[][] board) {
        List<Moves> impossibleMoves = new ArrayList<>();
        int[] pom = getCoordinates(board, size*size);
        if (lastMove != null) {
            switch (lastMove.getMove()) {
                case Up:
                    impossibleMoves.add(Moves.Down);
                    break;
                case Down:
                    impossibleMoves.add(Moves.Up);
                    break;
                case Left:
                    impossibleMoves.add(Moves.Right);
                    break;
                case Right:
                    impossibleMoves.add(Moves.Left);
                    break;
            }
        }

        //Check if Left move is possible
        if (pom[1] == 0) {
            if (!impossibleMoves.contains(Moves.Left)) impossibleMoves.add(Moves.Left);
        } else if (blocked[pom[0]][pom[1]-1]) {
            if (!impossibleMoves.contains(Moves.Left)) impossibleMoves.add(Moves.Left);
        }

        //Check if Right move is possible
        if (pom[1] == size - 1) {
            if (!impossibleMoves.contains(Moves.Right)) impossibleMoves.add(Moves.Right);
        } else if (blocked[pom[0]][pom[1]+1]) {
            if (!impossibleMoves.contains(Moves.Right)) impossibleMoves.add(Moves.Right);
        }

        //Check if Up move is possible
        if (pom[0] == 0) {
            if (!impossibleMoves.contains(Moves.Up)) impossibleMoves.add(Moves.Up);
        } else if (blocked[pom[0]-1][pom[1]]) {
            if (!impossibleMoves.contains(Moves.Up)) impossibleMoves.add(Moves.Up);
        }

        //Check if Down move is possible
        if (pom[0] == size - 1) {
            if (!impossibleMoves.contains(Moves.Down)) impossibleMoves.add(Moves.Down);
        } else if (blocked[pom[0]+1][pom[1]]) {
            if (!impossibleMoves.contains(Moves.Down)) impossibleMoves.add(Moves.Down);
        }

        List<Moves> allMoves = new ArrayList<>();
        allMoves.add(Moves.Up);
        allMoves.add(Moves.Down);
        allMoves.add(Moves.Left);
        allMoves.add(Moves.Right);
        for (Moves m : impossibleMoves) {
            allMoves.remove(m);
        }
        return allMoves;
    }

    private void printBoard(int[][] board) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int pom = board[j][i];
                if (pom == size*size) {
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
    }

    private void printBlocked() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(blocked[j][i] ? "1" : "0");
            }
            System.out.println();
        }
    }
}
