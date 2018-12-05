package algorithms;

import algorithms.methods.CheckingMethods;
import gameComponents.TreeElement;

import java.util.ArrayList;
import java.util.List;

public class BFS extends AAlgorithm {

    public BFS(String solutionInfo, String inputFilePath) {
        super(solutionInfo, inputFilePath);
        solutionName = "bfs";
    }

    @Override
    public void solve() {
        TreeElement root = new TreeElement(board.getBoard(), null, null,0);
        List<TreeElement> actualLevel = new ArrayList<>();
        actualLevel.add(root);
        if (CheckingMethods.isSolved(root.getBoardAfter())) {
            isSolved = true;
        }
        root.expand(solutionInfo);
        int level = 0;

        if (!isSolved) {
            do {
                level++;
                actualLevel.forEach(a -> a.expand(solutionInfo));
                List<TreeElement> pom = new ArrayList<>();
                actualLevel.forEach(a -> pom.addAll(a.getChildren()));
                actualLevel = pom;
                for (TreeElement treeElement : actualLevel) {
                    treeElement.makeMove();
                    if (CheckingMethods.isSolved(treeElement.getBoardAfter())) {
                        isSolved = true;
                        movesSoFar = treeElement.getMovesSoFar();
                        break;
                    }
                }
            } while (!isSolved);
        }
        maxDepth = level;
    }
}
