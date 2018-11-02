package algorithms.goalSystem;

import java.util.List;

public class GoalFactory {
    private final List<GoalSet> goalSequence;

    public GoalFactory(List<GoalSet> goalSequence) {
        this.goalSequence = goalSequence;
    }

    public GoalSet nextSequence() {
        GoalSet gs = new GoalSet();
        goalSequence.add(gs);
        return gs;
    }
}
