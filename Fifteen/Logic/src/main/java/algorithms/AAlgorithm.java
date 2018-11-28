package algorithms;

import fileOperations.ReadFile;
import fileOperations.SaveToFile;
import gameComponents.Board;

import java.util.ArrayList;
import java.util.List;

public abstract class AAlgorithm {
    protected Board board;
    List<Character> movesSoFar;
    String solutionName;
    String solutionInfo;
    protected String path;
    protected String fileName;
    protected long statesVisited;//sa i byly w kolejce
    protected long statesProcessed;//byly w kolejce
    int maxDepth;
    protected float time;
    boolean isSolved;

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
            fileContents.append(movesSoFar.size()).append(System.getProperty("line.separator"));
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
            fileContents.append(movesSoFar.size()).append(System.getProperty("line.separator"));
        }
        fileContents.append(statesVisited).append(System.getProperty("line.separator"));
        fileContents.append(statesProcessed).append(System.getProperty("line.separator"));
        fileContents.append(maxDepth).append(System.getProperty("line.separator"));
        fileContents.append(time).append(System.getProperty("line.separator"));
        SaveToFile.Save(path, statsFileName, fileContents.toString());
    }
}
