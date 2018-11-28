package sample;

import algorithms.*;

import javax.swing.*;
import java.io.File;

public class DataGenerating {
    private static BFS bfs;
    //private static DFS dfs;
    //private static HAMM hamm;
    //private static MANH manh;
    //private static MyHeuristic myHeuristic;

    private static File file;

    public static void main(String[] args) {
        chooseFile();

        bfs = new BFS("info", file.getPath());
        //dfs = new DFS("info", file.getPath());
        //hamm = new HAMM(file.getPath());
        //manh = new MANH(file.getPath());
        //myHeuristic = new MyHeuristic(file.getPath());

        bfs.solve();
        //dfs.solve();
        //hamm.solve();
        //manh.solve();
        //myHeuristic.solve();

        save();
    }

    private static void chooseFile() {
        JFileChooser fileChooser = new JFileChooser(new File("Files\\").getPath());
        file = null;
        int returnVal = fileChooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
        }
        assert file != null;
        if(!file.getName().substring(file.getName().length() - 4).equals(".txt")) {
            System.out.println("Incorrect file!");
            chooseFile();
        }
    }

    private static void save() {
        bfs.saveOutputFile();
        bfs.saveStatsFile();
        //dfs.saveOutputFile();
        //dfs.saveStatsFile();
        //hamm.saveOutputFile();
        //hamm.saveStatsFile();
        //manh.saveOutputFile();
        //manh.saveStatsFile();
        //myHeuristic.saveOutputFile();
        //myHeuristic.saveStatsFile();
    }
}
