package algorithms;

import fileOperations.ReadFile;
import fileOperations.SaveToFile;

import java.util.ArrayList;
import java.util.List;

public abstract class AAlgorithm {
    protected Board board;
    protected List<Character> movesSoFar;
    String solutionName;
    String solutionInfo;
    protected String path;
    protected String fileName;
    protected long statesVisited;//sa i byly w kolejce
    protected long statesProcessed;//byly w kolejce
    protected int maxDepth;
    protected float time;
    protected boolean isSolved;

    private StringBuilder fileContents;

    AAlgorithm(String solutionInfo, String inputFilePath) {
        board = ReadFile.getBoardFromFile(inputFilePath);
        if(board == null) {
            System.out.println("Incorrect input file!");
        }
        this.solutionInfo = solutionInfo;
        movesSoFar = new ArrayList<>();
        path = "Files\\";
    }

    public abstract void solve();

    public void saveOutputFile() {
        String outputFileName = fileName + "_" + solutionName + "_" + solutionInfo + "_sol.txt";
        fileContents = new StringBuilder();
        if(isSolved) {
            fileContents.append(movesSoFar.size()).append("\n");
            movesSoFar.forEach(move -> fileContents.append(move));
        } else {
            fileContents.append("-1");
        }
        SaveToFile.Save(path, outputFileName, fileContents.toString());
    }

    public void saveStatsFile() {
        String statsFileName = fileName + "_" + solutionName + "_" + solutionInfo + "_stats.txt";
        fileContents = new StringBuilder();
        if(isSolved) {
            fileContents.append(movesSoFar.size()).append("\n");
        }
        fileContents.append(statesVisited).append("\n");
        fileContents.append(statesProcessed).append("\n");
        fileContents.append(maxDepth).append("\n");
        fileContents.append(time).append("\n");
        SaveToFile.Save(path, statsFileName, fileContents.toString());
    }
}
