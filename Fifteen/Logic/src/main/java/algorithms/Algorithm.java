package algorithms;

import algorithms.goalSystem.Goal;
import algorithms.goalSystem.GoalSequence;
import algorithms.moveTracking.Move;
import algorithms.moveTracking.MoveSequence;
import algorithms.moveTracking.Moves;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

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
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                blocked[i][j] = false;
            }
        }
    }

    public void start() {
        printBoard(board);
        int lastMovesSize = 0;
        List<MoveSequence> moveSequences = new ArrayList<>();
        List<MoveSequence> newSequences;
        List<Moves> possibleMoves;
        Optional<MoveSequence> completesGoal;

        while (true) {
            moveSequences = new ArrayList<>();
            if (movesSoFar.size() == 0) {
                possibleMoves = getPossibleMoves(null, board);
            } else {
                possibleMoves = getPossibleMoves(movesSoFar.get(movesSoFar.size() - 1), board);
            }
            for (Moves m : possibleMoves) {
                moveSequences.add(new MoveSequence(m, board));
            }


            newSequences = new ArrayList<>();
            for (MoveSequence bs : moveSequences) {
                possibleMoves = getPossibleMoves(null, bs.getBoardAfter());
                for (Moves move : possibleMoves) {
                    newSequences.add(new MoveSequence(bs, move));
                }
            }
            while (newSequences.stream().noneMatch(ms -> completesGoals(ms.getBoardAfter()))) {
                final OptionalInt closestDistance;
                newSequences = new ArrayList<>();
                for (MoveSequence bs : moveSequences) {
                    possibleMoves = getPossibleMoves(bs.getMovesSoFar().get(bs.getMovesSoFar().size() - 1), bs.getBoardAfter());
                    for (Moves move : possibleMoves) {
                        newSequences.add(new MoveSequence(bs, move));
                    }
                }
                moveSequences = newSequences;
                closestDistance = moveSequences.stream().mapToInt(v -> distanceToGoals(v.getBoardAfter())).min();
                if(closestDistance.isPresent()) {
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
            if(movesSoFar.size() > 0) {
                board = movesSoFar.get(movesSoFar.size() - 1).getBoard();
            }
            changeBlockedFields();

            System.out.println("Moves: " + movesSoFar.size() + "    Difference: " + (movesSoFar.size() - lastMovesSize));
            printBoard(board);
            lastMovesSize = movesSoFar.size();
            System.out.println();

            if (goals.hasNext()) {
                goals.next();
            } else {
                break;
            }
        }
        printBoard(board);
    }

    private boolean completesGoals(int[][] board) {
        if(goals.getCurrentGoals().getNumberToGetTo() > 0) {
            if(distanceToGoals(board) > 1) {
                return false;
            }
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

    private int distanceToGoals(int [][] board) {
        int pom1 = 0;
        int pom2 = 0;
        if(goals.getCurrentGoals().getNumberToGetTo() > 0) {
            int[] pom3 = getCoordinates(board, size*size);
            pom1 = calculateDistance(board, goals.getCurrentGoals().getNumberToGetTo(), pom3);
        } else {
            for (Goal g : goals.getCurrentGoals().getGoals()) {
                if (g.isValueDesired()) {
                    pom1 += calculateDistance(board, g.getValue(), new int[]{g.getX(), g.getY()});
                } else {
                    if (board[g.getX()][g.getY()] == g.getValue()) {
                        pom2 = size * size * size;
                    }
                }
            }
        }
        return pom1+pom2;
    }

    private int calculateDistance(int[][] board, int value, int[] desiredPosition) {
        int[] pom1 = getCoordinates(board, value);
        int pom2 = 0;
        pom2 += Math.abs(desiredPosition[0]-pom1[0]);
        pom2 += Math.abs(desiredPosition[1]-pom1[1]);
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
        int[] pom = getCoordinates(board, size * size);
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
        } else if (blocked[pom[0]][pom[1] - 1]) {
            if (!impossibleMoves.contains(Moves.Left)) impossibleMoves.add(Moves.Left);
        }

        //Check if Right move is possible
        if (pom[1] == size - 1) {
            if (!impossibleMoves.contains(Moves.Right)) impossibleMoves.add(Moves.Right);
        } else if (blocked[pom[0]][pom[1] + 1]) {
            if (!impossibleMoves.contains(Moves.Right)) impossibleMoves.add(Moves.Right);
        }

        //Check if Up move is possible
        if (pom[0] == 0) {
            if (!impossibleMoves.contains(Moves.Up)) impossibleMoves.add(Moves.Up);
        } else if (blocked[pom[0] - 1][pom[1]]) {
            if (!impossibleMoves.contains(Moves.Up)) impossibleMoves.add(Moves.Up);
        }

        //Check if Down move is possible
        if (pom[0] == size - 1) {
            if (!impossibleMoves.contains(Moves.Down)) impossibleMoves.add(Moves.Down);
        } else if (blocked[pom[0] + 1][pom[1]]) {
            if (!impossibleMoves.contains(Moves.Down)) impossibleMoves.add(Moves.Down);
        }

        List<Moves> allMoves = new ArrayList<>();
        allMoves.add(Moves.Up);
        allMoves.add(Moves.Down);
        allMoves.add(Moves.Left);
        allMoves.add(Moves.Right);
        allMoves.removeAll(impossibleMoves);
        return allMoves;
    }

    private void printBoard(int[][] board) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int pom = board[j][i];
                if (pom == size * size) {
                    System.out.print("   ");
                } else if (pom > 9) {
                    System.out.print(pom + " ");
                } else {
                    System.out.print(" " + pom + " ");
                }
            }
            System.out.println();
        }
    }
}
