package algorithms;

import algorithms.methods.CheckingMethods;
import gameComponents.TreeElement;

import java.util.ArrayList;
import java.util.List;

public class BFS extends AAlgorithm {

    public BFS(String solutionInfo, String inputFilePath, String solutionFileName, String statsFileName) {
        super(solutionInfo, inputFilePath, solutionFileName, statsFileName);
        solutionName = "bfs";
    }

    @Override
    public void solve() {
        TreeElement root = new TreeElement(board.getBoard(), null, null, 0);
        List<TreeElement> actualLevel = new ArrayList<>();
        actualLevel.add(root);
        if (CheckingMethods.isSolved(root.getBoardAfter())) {
            isSolved = true;
        }
        root.expand(solutionInfo);
        statesProcessed += 1;
        int level = 0;

        if (!isSolved) {
            do {
                level++;
                actualLevel.forEach(a -> a.expand(solutionInfo));
                List<TreeElement> pom = new ArrayList<>();
                actualLevel.forEach(a -> pom.addAll(a.getChildren()));
                actualLevel = pom;
                statesVisited += actualLevel.size();
                for (TreeElement treeElement : actualLevel) {
                    treeElement.makeMove();
                    if (CheckingMethods.isSolved(treeElement.getBoardAfter())) {
                        isSolved = true;
                        movesSoFar = treeElement.getMovesSoFar();
                        break;
                    } else {
                        statesProcessed ++;
                    }
                }
            } while (!isSolved);
        }
        maxDepth = level;
    }
}
