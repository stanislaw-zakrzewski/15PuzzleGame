package algorithms;

import algorithms.methods.CheckingMethods;
import gameComponents.TreeElement;

import java.util.ArrayList;
import java.util.List;

public class ASTR extends AAlgorithm {

    public ASTR(String solutionInfo, String inputFilePath, String solutionFileName, String statsFileName) {
        super(solutionInfo, inputFilePath, solutionFileName, statsFileName);
        solutionName = "astr";
    }

    @Override
    public void solve() {
        List<TreeElement> closedSet = new ArrayList<>();
        List<TreeElement> openSet = new ArrayList<>();
        openSet.add(new TreeElement(board.getBoard(), null, null, 0));
        TreeElement current;
        boolean wasItHere = false;

        while (!openSet.isEmpty()) {
            current = openSet.get(0);
            for (TreeElement treeElement : openSet) {
                if (solutionInfo.equals("hamm")) {
                    if (current.costHamm() > treeElement.costHamm()) {
                        current = treeElement;
                    }
                } else {
                    if (current.costMann() > treeElement.costMann()) {
                        current = treeElement;
                    }
                }
            }
            if (CheckingMethods.isSolved(current.getBoardAfter())) {
                isSolved = true;
                movesSoFar = current.getMovesSoFar();
                statesProcessed = closedSet.size();
                statesVisited = statesProcessed + openSet.size();
                return;
            }
            openSet.remove(current);
            closedSet.add(current);

            current.expand("uldr");
            if(maxDepth < current.getDepth()+1) {
                maxDepth = current.getDepth()+1;
            }
            for (TreeElement child : current.getChildren()) {
                child.makeMove();
                for (TreeElement closed : closedSet) {
                    if (closed.getBoardAfter() == child.getBoardAfter()) {
                        wasItHere = true;
                    }
                }
                if (wasItHere) {
                    continue;
                }
                for (TreeElement opened : openSet) {
                    if (opened.getBoardAfter() == child.getBoardAfter()) {
                        wasItHere = true;
                    }
                }
                if (!wasItHere) {
                    openSet.add(child);
                }
            }
        }
    }
}
