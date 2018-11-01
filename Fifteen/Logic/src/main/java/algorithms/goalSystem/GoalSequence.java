package algorithms.goalSystem;

import java.util.ArrayList;
import java.util.List;

public class GoalSequence {
    private final int size;
    private final List<GoalSet> goalsSequence;
    private GoalSet currentGoals;
    private int goalsPosition;

    public GoalSequence(int boardSize) {
        size = boardSize;
        goalsSequence = new ArrayList<>();
        createSequence();
    }

    private void createSequence() {
        int pom = 1;
        for (int i = 0; i < size - 2; i++) {
            for (int j = 0; j < size - 2; j++) {
                goalsSequence.add(new GoalSet());
                goalsSequence.get(goalsSequence.size() - 1).addGoal(j, i, pom++, true);
            }
            goalsSequence.add(new GoalSet());
            goalsSequence.get(goalsSequence.size() - 1).addGoal(size - 2, i, pom + 1, true);
            goalsSequence.get(goalsSequence.size() - 1).addGoalNotDesired(size - 1, i, pom);
            goalsSequence.get(goalsSequence.size() - 1).addGoalNotDesired(size - 1, i+1, pom);
            goalsSequence.add(new GoalSet());
            goalsSequence.get(goalsSequence.size() - 1).addGoal(size - 2, i + 1, pom, false);
            goalsSequence.add(new GoalSet());
            goalsSequence.get(goalsSequence.size() - 1).addGoal(size - 2, i, pom + 1, false);
            goalsSequence.add(new GoalSet());
            goalsSequence.get(goalsSequence.size() - 1).addGoal(size - 2, i, pom, true);
            goalsSequence.get(goalsSequence.size() - 1).addGoal(size - 1, i, pom + 1, true);
            pom += 2;
        }

        for(int i = 0; i < size-2; i++) {
            goalsSequence.add(new GoalSet());
            goalsSequence.get(goalsSequence.size()-1).addGoal(i,size-2,pom+size,true);
            goalsSequence.get(goalsSequence.size() - 1).addGoalNotDesired(i, size - 1, pom);
            goalsSequence.get(goalsSequence.size() - 1).addGoalNotDesired(i+1, size - 1, pom);
            goalsSequence.add(new GoalSet());
            goalsSequence.get(goalsSequence.size()-1).addGoal(i+1,size-2,pom,false);
            goalsSequence.add(new GoalSet());
            goalsSequence.get(goalsSequence.size()-1).addGoal(i,size-2,pom+size,false);
            goalsSequence.add(new GoalSet());
            goalsSequence.get(goalsSequence.size()-1).addGoal(i,size-2,pom,true);
            goalsSequence.get(goalsSequence.size()-1).addGoal(i,size-1,pom + size,true);
            pom++;
        }

        goalsSequence.add(new GoalSet());
        goalsSequence.get(goalsSequence.size()-1).addGoal(size-2, size-2,pom,true);
        //goalsSequence.get(goalsSequence.size()-1).addGoal(size-2, size-1,pom+size,true);
        //goalsSequence.add(new GoalSet());
        //goalsSequence.get(goalsSequence.size()-1).addGoal(size-1, size-2,pom+1,true);
        //goalsSequence.add(new GoalSet());
        //goalsSequence.get(goalsSequence.size()-1).addGoal(size-1, size-1,size*size,true);
        goalsSequence.add(new GoalSet());
        currentGoals = goalsSequence.get(goalsPosition);
    }

    public boolean hasNext() {
        return !goalsSequence.get(goalsSequence.size() - 1).equals(currentGoals);
    }

    public void next() {
        goalsPosition ++;
        currentGoals = goalsSequence.get(goalsPosition);
    }

    public GoalSet getCurrentGoals() {
        return currentGoals;
    }
}
