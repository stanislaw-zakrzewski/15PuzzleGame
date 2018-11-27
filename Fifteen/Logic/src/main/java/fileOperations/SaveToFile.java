package fileOperations;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class SaveToFile {
    public static void Save(String path, String fileName, String fileContent) {
        try {
            PrintWriter out = new PrintWriter(path + "" + fileName);
            out.write(fileContent);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
