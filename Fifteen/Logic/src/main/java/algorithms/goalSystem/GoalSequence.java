package algorithms.goalSystem;

import java.util.ArrayList;
import java.util.List;

public class GoalSequence {
    private final int w;
    private final int k;
    private final List<GoalSet> goalsSequence;
    private GoalFactory goalFactory;
    private GoalSet currentGoals;
    private int goalsPosition;

    public GoalSequence(int w, int k) {
        this.w = w;
        this.k = k;
        goalsSequence = new ArrayList<>();
        goalFactory = new GoalFactory(goalsSequence);
        createSequence();
    }

    private void createSequence() {
        int pom = 1;
        goalFactory.nextSequence().addGoalForBlank(pom);
        for (int i = 0; i < w - 2; i++) {
            for (int j = 0; j < k - 2; j++) {
                goalFactory.nextSequence()
                        .addGoalForBlank(pom);
                goalFactory.nextSequence()
                        .addGoal(j, i, pom++, true);
            }
            goalFactory.nextSequence()
                    .addGoalForBlank(pom);
            goalFactory.nextSequence()
                    .addGoal(w - 1, i, pom, true)
                    .addGoalNotDesired(w - 2, i, pom+1)
                    .addGoalNotDesired(w - 2, i+1, pom+1);
            goalFactory.nextSequence()
                    .addGoalForBlank(pom + 1);
            goalFactory.nextSequence()
                    .addGoal(w - 1, i+1, pom+1, false);
            goalFactory.nextSequence()
                    .addGoal(w - 1, i, pom, false);
            goalFactory.nextSequence()
                    .addGoal(w - 2, i, pom, true)
                    .addGoal(w - 1, i, pom + 1, true);
            pom += 2;
        }

        for (int i = 0; i < k - 2; i++) {
            goalFactory.nextSequence()
                    .addGoal(i, k - 2, pom + k, true)
                    .addGoalNotDesired(i, k - 1, pom)
                    .addGoalNotDesired(i + 1, k - 1, pom);
            goalFactory.nextSequence()
                    .addGoal(i + 1, k - 2, pom, false);
            goalFactory.nextSequence()
                    .addGoal(i, k - 2, pom + k, false);
            goalFactory.nextSequence()
                    .addGoal(i, k - 2, pom, true)
                    .addGoal(i, k - 1, pom + k, true);
            pom++;
        }

        goalFactory.nextSequence()
                .addGoal(w - 2, k - 2, pom, true);
        goalFactory.nextSequence()
                .addGoal(w-1, k-2,pom+1,true);
        goalFactory.nextSequence()
                .addGoal(w-2, k-1,w*k-1,true);
        goalFactory.nextSequence()
                .addGoal(w-2, k-2,w * k,true);
        currentGoals = goalsSequence.get(goalsPosition);
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
