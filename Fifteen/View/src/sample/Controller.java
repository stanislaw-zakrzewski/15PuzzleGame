package sample;

import algorithms.methods.CheckingMethods;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

import javax.swing.*;
import java.io.File;

public class Controller {
    @FXML
    private ChoiceBox<String> algorithm, modificator;
    @FXML
    private TextArea informations;
    @FXML
    private Button solve;

    private ObservableList<String> algorithms = FXCollections.observableArrayList();
    private ObservableList<String> modificators = FXCollections.observableArrayList();
    private String algorithmSelected;
    private String modifierSelected;
    private File file;

    public Controller() {

    }


    public void browseFile() {
        solve.setDisable(true);
        JFileChooser fileChooser = new JFileChooser(new File("").getAbsolutePath());
        int i = fileChooser.showOpenDialog(null);
        if(i == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
            if(CheckingMethods.isFileCorrect(file)) {
                informations.setText(file.getPath());
                setAlgorithm();
            } else {
                informations.setText("File incorrect!");
            }
        }
    }

    private void setAlgorithm() {
        solve.setDisable(true);
        algorithms.clear();
        algorithms.add("bfs");
        algorithms.add("dfs");
        algorithms.add("astr");
        algorithm.setItems(algorithms);
        algorithm.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> setModificator(newValue.intValue()));
    }

    private void setModificator(int value) {
        solve.setDisable(true);
        updateSelectedAlgorithm(value);
        modificators.clear();
        if (value == 2) {
            modificators.add("hamm");
            modificators.add("manh");
        } else {
            modificators.add("prawo-dół-góra-lewo");
            modificators.add("prawo-dół-lewo-góra");
            modificators.add("dół-prawo-góra-lewo");
            modificators.add("dół-prawo-lewo-góra");
            modificators.add("lewo-góra-dół-prawo");
            modificators.add("lewo-góra-prawo-dół");
            modificators.add("góra-lewo-dół-prawo");
            modificators.add("góra-lewo-prawo-dół");
        }
        modificator.setItems(modificators);
        modificator.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> updateSelectedModificator(newValue.intValue()));
    }

    private void updateSelectedAlgorithm(int value) {
        solve.setDisable(true);
        switch (value) {
            case 0:
                algorithmSelected = "bfs";
                break;
            case 1:
                algorithmSelected = "dfs";
                break;
            case 2:
                algorithmSelected = "astr";
                break;
        }
    }

    private void updateSelectedModificator(int value) {
        switch (value) {
            case 0:
                if (algorithmSelected.equals("astr")) {
                    modifierSelected = "hamm";
                } else {
                    modifierSelected = "rdul";
                }
                break;
            case 1:
                if (algorithmSelected.equals("astr")) {
                    modifierSelected = "manh";
                } else {
                    modifierSelected = "rdlu";
                }
                break;
            case 2:
                modifierSelected = "drul";
                break;
            case 3:
                modifierSelected = "drlu";
                break;
            case 4:
                modifierSelected = "ludr";
                break;
            case 5:
                modifierSelected = "lurd";
                break;
            case 6:
                modifierSelected = "uldr";
                break;
            case 7:
                modifierSelected = "ulrd";
                break;
        }
        solve.setDisable(false);
    }

    public void solvePuzzle() {
        System.out.println(algorithmSelected + " " + modifierSelected + " " + file.getPath());
    }
}
