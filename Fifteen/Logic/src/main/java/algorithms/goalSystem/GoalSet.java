package algorithms.goalSystem;

import java.util.ArrayList;
import java.util.List;

public class GoalSet {
    private final List<Goal> goals;
    private final List<int[]> fieldsToBlock;
    private final List<int[]> fieldsToUnlock;
    private int numberToGetTo;

    GoalSet() {
        goals = new ArrayList<>();
        fieldsToBlock = new ArrayList<>();
        fieldsToUnlock = new ArrayList<>();
    }

    GoalSet addGoal(int x, int y, int value, boolean blocksField) {
        Goal pom = new Goal(x, y, value, true);
        goals.add(pom);
        if (blocksField) {
            fieldsToBlock.add(pom.getCoordinates());
        } else {
            fieldsToUnlock.add(pom.getCoordinates());
        }
        return this;
    }

    GoalSet addGoalNotDesired(int x, int y, int value) {
        Goal pom = new Goal(x,y,value,false);
        goals.add(pom);
        return this;
    }

    GoalSet addGoalForBlank(int numberToGetTo) {
        this.numberToGetTo = numberToGetTo;
        return this;
    }

    public List<Goal> getGoals() {
        return goals;
    }

    public List<int[]> getFieldsToBlock() {
        return fieldsToBlock;
    }

    public List<int[]> getFieldsToUnlock() {
        return fieldsToUnlock;
    }

    public int getNumberToGetTo() { return numberToGetTo; }
}
