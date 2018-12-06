package algorithms;

import algorithms.methods.CheckingMethods;
import gameComponents.TreeElement;

public class DFS extends AAlgorithm {
    private static final int maximumDepth = 20;
    private static final int maximumStates = 1000000;

    public DFS(String solutionInfo, String inputFilePath, String solutionFileName, String statsFileName) {
        super(solutionInfo, inputFilePath, solutionFileName, statsFileName);
        solutionName = "dfs";
    }

    @Override
    public void solve() {
        maxDepth = 23;
        TreeElement root = new TreeElement(board.getBoard(), null, null, 0);
        visitTreeElement(root);
    }

    private void visitTreeElement(TreeElement treeElement) {
        if (statesProcessed >= maximumStates) {
            return;
        }
        if (!treeElement.isChecked()) {
            treeElement.setChecked(true);
        }
        if (CheckingMethods.isSolved(treeElement.getBoardAfter())) {
            isSolved = true;
            movesSoFar = treeElement.getMovesSoFar();
        } else if (!isSolved) {
            treeElement.expand(solutionInfo);
            if (maxDepth < treeElement.getDepth() + 1) {
                maxDepth = treeElement.getDepth() + 1;
            }
            statesProcessed++;
            if (treeElement.getDepth() < maximumDepth) {
                for (TreeElement child : treeElement.getChildren()) {
                    statesVisited++;
                    if (!child.isChecked()) {
                        child.makeMove();
                        visitTreeElement(child);
                    }
                }
            }
        }
    }
}
