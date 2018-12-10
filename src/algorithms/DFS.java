package algorithms;

import algorithms.methods.CheckingMethods;
import gameComponents.TreeElement;

import java.util.Stack;

public class DFS extends AAlgorithm {
    private static final int maximumDepth = 20;

    public DFS(String solutionInfo, String inputFilePath, String solutionFileName, String statsFileName) {
        super(solutionInfo, inputFilePath, solutionFileName, statsFileName);
        solutionName = "dfs";
    }

    @Override
    public void solve() {
        maxDepth = 0;
        Stack<TreeElement> toProcess = new Stack<>();
        toProcess.push(new TreeElement(board.getBoard(), null, null, 0));
        TreeElement actualElement;
        while (!toProcess.isEmpty()) {
            statesVisited++;
            actualElement = toProcess.pop();
            maxDepth = maxDepth < actualElement.getDepth() ? actualElement.getDepth() : maxDepth;
            if(CheckingMethods.isSolved(actualElement.getBoardAfter())) {
                isSolved = true;
                movesSoFar = actualElement.getMovesSoFar();
                break;
            }
            else if(actualElement.getDepth() <= maximumDepth) {
                actualElement.expand(solutionInfo);
                for(int i = actualElement.getChildren().size()-1; i >= 0; i--) {
                    toProcess.push(actualElement.getChildren().get(i));
                    statesProcessed++;
                }
            }
        }
    }
}
