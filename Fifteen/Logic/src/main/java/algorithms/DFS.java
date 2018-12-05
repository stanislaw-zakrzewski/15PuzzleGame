package algorithms;

import algorithms.methods.CheckingMethods;
import algorithms.moveTracking.Moves;
import gameComponents.TreeElement;

import java.util.ArrayList;
import java.util.List;

public class DFS extends AAlgorithm {

    public DFS(String solutionInfo, String inputFilePath) {
        super(solutionInfo, inputFilePath);
        solutionName = "dfs";
    }

    @Override
    public void solve() {
        maxDepth = 23;
        TreeElement root = new TreeElement(board.getBoard(), null, null,0);
        /*List<TreeElement> actualLevel = new ArrayList<>();
        actualLevel.add(root);
        if (CheckingMethods.isSolved(root.getBoardAfter())) {
            isSolved = true;
        }
        root.expand(solutionInfo);
        for(TreeElement child : root.getChildren()) {
            visitTreeElement(child);
        }*/
        visitTreeElement(root);
    }

    private void visitTreeElement(TreeElement treeElement) {
        if (treeElement.isChecked()) {
            treeElement.setChecked(true);
        }
        if (CheckingMethods.isSolved(treeElement.getBoardAfter())) {
            isSolved = true;
            movesSoFar = treeElement.getMovesSoFar();
        } else if(!isSolved) {
            treeElement.expand(solutionInfo);
            if (treeElement.getDepth() < maxDepth) {
                for (TreeElement child : treeElement.getChildren()) {
                    if (!child.isChecked()) {
                        child.makeMove();
                        visitTreeElement(child);
                    }
                }
            }
        }
    }
}
