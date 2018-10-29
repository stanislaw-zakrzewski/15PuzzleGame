package algorithms;

import java.util.ArrayList;
import java.util.List;

public class GoalSet {
    private final List<Goal> goals;
    private final List<int[]> fieldsToBlock;
    private final List<int[]> fieldsToUnlock;

    public GoalSet() {
        goals = new ArrayList<>();
        fieldsToBlock = new ArrayList<>();
        fieldsToUnlock = new ArrayList<>();
    }

    public void addGoal(int x, int y, int value, boolean blocksField) {
        Goal pom = new Goal(x,y,value);
        goals.add(pom);
        if(blocksField) {
            fieldsToBlock.add(pom.getCoordinates());
        } else {
            fieldsToUnlock.add(pom.getCoordinates());
        }
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
}
