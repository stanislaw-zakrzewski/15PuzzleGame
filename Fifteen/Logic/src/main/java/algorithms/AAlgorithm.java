package algorithms;

import algorithms.moveTracking.Moves;
import fileOperations.ReadFile;
import fileOperations.SaveToFile;
import gameComponents.Board;

import java.util.ArrayList;
import java.util.List;

public abstract class AAlgorithm {
    protected Board board;
    List<Moves> movesSoFar;
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
            movesSoFar.forEach(move -> fileContents.append(moveToCharacter(move)));
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
        String[] t = Float.toString(time/1000000).split("\\.");
        fileContents.append(t[0]).append(".").append(t[1], 0, 3).append(System.getProperty("line.separator"));
        SaveToFile.Save(path, statsFileName, fileContents.toString());
    }

    private char moveToCharacter(Moves move) {
        switch (move) {
            case D:
                return 'd';
            case L:
                return 'l';
            case R:
                return 'r';
            case U:
                return 'u';
        }
        return '?';
    }

    public void setTime(float time) {
        this.time = time;
    }
}
