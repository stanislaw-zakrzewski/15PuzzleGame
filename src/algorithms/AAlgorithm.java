package algorithms;

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
    private String path;
    long statesVisited;
    long statesProcessed;
    int maxDepth;
    private float time;
    boolean isSolved;
    private String solutionFileName;
    private String statsFileName;

    private StringBuilder fileContents;

    AAlgorithm(String solutionInfo, String inputFilePath, String solutionFileName, String statsFileName) {
        board = ReadFile.getBoardFromFile(inputFilePath);
        if(board == null) {
            System.out.println("Incorrect input file!");
        }
        this.solutionInfo = solutionInfo;
        this.solutionFileName = solutionFileName;
        this.statsFileName = statsFileName;
        movesSoFar = new ArrayList<>();
        path = "";
    }

    public abstract void solve();

    public void saveOutputFile() {
        fileContents = new StringBuilder();
        if(isSolved) {
            fileContents.append(movesSoFar.size()).append(System.getProperty("line.separator"));
            movesSoFar.forEach(move -> fileContents.append(moveToCharacter(move)));
        } else {
            fileContents.append("-1");
        }
        SaveToFile.Save(path, solutionFileName, fileContents.toString());
    }

    public void saveStatsFile() {
        fileContents = new StringBuilder();
        if(isSolved) {
            fileContents.append(movesSoFar.size()).append(System.getProperty("line.separator"));
        }
        fileContents.append(statesVisited).append(System.getProperty("line.separator"));
        fileContents.append(statesProcessed).append(System.getProperty("line.separator"));
        fileContents.append(maxDepth).append(System.getProperty("line.separator"));
        float t2 = time/1000000;
        if((t2 - (int)t2) == 0) {
            fileContents.append(t2).append(System.getProperty("line.separator"));
        } else {
            String[] t = Float.toString(time/1000000).split("\\.");
            if(t[1].length() > 3) {
                t[1] = t[1].substring(0,3);
            }
            fileContents.append(t[0]).append(".").append(t[1]).append(System.getProperty("line.separator"));
        }
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
