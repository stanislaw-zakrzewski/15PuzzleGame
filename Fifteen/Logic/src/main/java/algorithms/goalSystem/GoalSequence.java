package algorithms.goalSystem;

import java.util.ArrayList;
import java.util.List;

public class GoalSequence {
    private final int size;
    private final List<GoalSet> goalsSequence;
    private GoalFactory goalFactory;
    private GoalSet currentGoals;
    private int goalsPosition;

    public GoalSequence(int boardSize) {
        size = boardSize;
        goalsSequence = new ArrayList<>();
        goalFactory = new GoalFactory(goalsSequence);
        createSequence();
    }

    private void createSequence() {
        int pom = 1;
        goalFactory.nextSequence().addGoalForBlank(pom);
        for (int i = 0; i < size - 2; i++) {
            for (int j = 0; j < size - 2; j++) {
                goalFactory.nextSequence()
                        .addGoalForBlank(pom);
                goalFactory.nextSequence()
                        .addGoal(j, i, pom++, true);
            }
            goalFactory.nextSequence()
                    .addGoalForBlank(pom);
            goalFactory.nextSequence()
                    .addGoal(size - 1, i, pom, true)
                    .addGoalNotDesired(size - 2, i, pom+1);
            goalFactory.nextSequence()
                    .addGoalForBlank(pom + 1);
            goalFactory.nextSequence()
                    .addGoal(size - 1, i+1, pom+1, false);
            goalFactory.nextSequence()
                    .addGoal(size - 1, i, pom, false);
            goalFactory.nextSequence()
                    .addGoal(size - 2, i, pom, true)
                    .addGoal(size - 1, i, pom + 1, true);
            pom += 2;
        }

        for (int i = 0; i < size - 2; i++) {
            goalFactory.nextSequence()
                    .addGoal(i, size - 2, pom + size, true)
                    .addGoalNotDesired(i, size - 1, pom)
                    .addGoalNotDesired(i + 1, size - 1, pom);
            goalFactory.nextSequence()
                    .addGoal(i + 1, size - 2, pom, false);
            goalFactory.nextSequence()
                    .addGoal(i, size - 2, pom + size, false);
            goalFactory.nextSequence()
                    .addGoal(i, size - 2, pom, true)
                    .addGoal(i, size - 1, pom + size, true);
            pom++;
        }

        goalFactory.nextSequence()
                .addGoal(size - 2, size - 2, pom, true);
        goalFactory.nextSequence()
                .addGoal(size-1, size-2,pom+1,true);
        goalFactory.nextSequence()
                .addGoal(size-2, size-1,pom+2,true);
        currentGoals = goalsSequence.get(goalsPosition);
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

    public boolean hasNext() {
        return !goalsSequence.get(goalsSequence.size() - 1).equals(currentGoals);
    }

    public void next() {
        goalsPosition++;
        currentGoals = goalsSequence.get(goalsPosition);
    }

    public GoalSet getCurrentGoals() {
        return currentGoals;
    }
}
