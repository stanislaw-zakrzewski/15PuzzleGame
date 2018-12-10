package algorithms;

import algorithms.methods.CheckingMethods;
import gameComponents.TreeElement;

import java.util.LinkedList;

public class BFS extends AAlgorithm {

    public BFS(String solutionInfo, String inputFilePath, String solutionFileName, String statsFileName) {
        super(solutionInfo, inputFilePath, solutionFileName, statsFileName);
        solutionName = "bfs";
    }

    @Override
    public void solve() {
        LinkedList<TreeElement> treeElements = new LinkedList<>();
        treeElements.push(new TreeElement(board.getBoard(), null, null, 0));
        TreeElement actualElement;
        while (!isSolved) {
            actualElement = treeElements.pop();
            statesVisited++;
            if (CheckingMethods.isSolved(actualElement.getBoardAfter())) {
                isSolved = true;
                movesSoFar = actualElement.getMovesSoFar();
                maxDepth = actualElement.getDepth();
                break;
            }
            actualElement.expand(solutionInfo);
            for (TreeElement child : actualElement.getChildren()) {
                statesProcessed++;
                treeElements.addLast(child);
            }
        }
    }
}
