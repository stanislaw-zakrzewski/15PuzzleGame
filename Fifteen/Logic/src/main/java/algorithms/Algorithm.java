package algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Algorithm {
    private int[][] board;
    private int size;
    private List<Move> movesSoFar;
    private GoalSequence goals;

    private static final int[][] solvedBoard = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
    private static final int[][] shaded = new int[][]{{0, 1, 0, 1}, {1, 0, 1, 0}, {0, 1, 0, 1}, {1, 0, 1, 0}};
    private static final boolean[][] blocked = new boolean[][]{{false, false, false, false}, {false, false, false, false}, {false, false, false, false}, {false, false, false, false}};

    public Algorithm(int[][] board) {
        goals = new GoalSequence(4);
        this.board = board;
        movesSoFar = new ArrayList<>();
        size = board.length;
    }

    public boolean IsSolvable() {
        int E = 0;
        E += shaded[getCoordinates(board, 16)[0]][getCoordinates(board, 16)[1]];
        for (int i = 1; i <= size * size; i++) {
            E += lessI(i);
        }
        return E % 2 == 0;
    }

    private int lessI(int i) {
        /*int pom1[] = getCoordinates(board, i);
        int pom2 = 0;
        int startingPosition = pom1[0] * size + pom1[1];
        for (int j = startingPosition; j < size * size; j++) {
            if (board.getBoardOneRow()[j] < i) {
                pom2++;
            }
        }
        return pom2;*/
        return 1;
    }

    public void start() {
        printBoard(board);
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

        while (!isCorrect()) {
            List<MoveSequence> newSequences = new ArrayList<>();
            for (MoveSequence bs : moveSequences) {
                possibleMoves = getPossibleMoves(bs.getMovesSoFar().get(bs.getMovesSoFar().size() - 1), bs.getBoardAfter());
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
            System.out.println("------------------");
            changeBlockedFields();
            if (goals.hasNext()) {
                goals.next();
            }
            moveSequences = new ArrayList<>();
            possibleMoves = getPossibleMoves(movesSoFar.get(movesSoFar.size() - 1), board);
            for (Moves m : possibleMoves) {
                moveSequences.add(new MoveSequence(m, board));
            }
            printBoard(board);
        }
        printBoard(board);
    }

    private boolean isCorrect() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (solvedBoard[i][j] != board[i][j]) {
                    return false;
                }
            }
        }
        return true;
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
        int[] pom = getCoordinates(board, 16);
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

        if (pom[0] == 0) {
            if (!impossibleMoves.contains(Moves.Left)) impossibleMoves.add(Moves.Left);
        } else if (blocked[pom[0] - 1][pom[1]]) {
            impossibleMoves.add(Moves.Left);
        } else if (pom[0] == size - 1) {
            if (!impossibleMoves.contains(Moves.Right)) impossibleMoves.add(Moves.Right);
        } else if (blocked[pom[0] + 1][pom[1]]) {
            impossibleMoves.add(Moves.Right);
        }

        if (pom[1] == 0) {
            if (!impossibleMoves.contains(Moves.Up)) impossibleMoves.add(Moves.Up);
        } else if (blocked[pom[0]][pom[1] - 1]) {
            impossibleMoves.add(Moves.Up);
        } else if (pom[1] == size - 1) {
            if (!impossibleMoves.contains(Moves.Down)) impossibleMoves.add(Moves.Down);
        } else if (blocked[pom[0]][pom[1] + 1]) {
            impossibleMoves.add(Moves.Down);
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
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int pom = board[j][i];
                if (pom == 16) {
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
