package algorithms;

import algorithms.methods.CheckingMethods;
import algorithms.methods.FindingMethods;
import algorithms.methods.OtherMethods;
import algorithms.moveTracking.Move;
import algorithms.moveTracking.MoveSequence;
import algorithms.moveTracking.Moves;
import gameComponents.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BFS extends AAlgorithm {

    public BFS(String solutionInfo, String inputFilePath) {
        super(solutionInfo, inputFilePath);
        solutionName = "bfs";
    }

    @Override
    public void solve() {



        /*
        MoveSequence solution = null;
        maxDepth = 23;
        int depthCounter = 0;
        List<Moves> movesOrder = OtherMethods.arrange("udlr");

        List<MoveSequence> moveSequences = new ArrayList<>();
        List<MoveSequence> newSequences = new ArrayList<>();

        if(CheckingMethods.isSolved(board.getBoard())) {
            isSolved = true;
        }

        if(!isSolved) {
            List<Moves> firstMoves = FindingMethods.getPossibleMoves(movesOrder, null, board.getBoard(), null);
            for (Moves m : firstMoves) {
                System.out.println(m);
                MoveSequence temp = new MoveSequence(m, board.getBoard());
                if (CheckingMethods.isSolved(temp.getBoardAfter())) {
                    solution = temp;
                    isSolved = true;
                    break;
                }
                newSequences.add(temp);
            }
            moveSequences = newSequences;
            newSequences = new ArrayList<>();
        }

        if(!isSolved)
        while (depthCounter < maxDepth) {
            for (MoveSequence ms : moveSequences) {
                System.out.println("c");
                List<Moves> possibleMoves = FindingMethods.getPossibleMoves(movesOrder, ms.getMovesSoFar().get(ms.getMovesSoFar().size() - 1), ms.getBoardAfter(), null);
                for (Moves m : possibleMoves) {
                    MoveSequence temp = new MoveSequence(ms, m);
                    if (CheckingMethods.isSolved(temp.getBoardAfter())) {
                        solution = temp;
                        isSolved = true;
                        break;
                    }
                    newSequences.add(temp);
                }
                if(isSolved) break;
            }
            if(isSolved) break;
            moveSequences = newSequences;
            newSequences = new ArrayList<>();
            depthCounter++;
            System.out.println(depthCounter);
        }

        if (solution != null) {
            for (Move m : solution.getMovesSoFar()) {
                movesSoFar.add(m.getMoveSignature());
            }
        }*/
    }
}
