import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.stage.FileChooser;

public class FileUtility {
    
    private String fileName;
    private String[] text;
    private int i = 0, j;
    
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    public void fileRead() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        while (true) {
            String line = br.readLine();
            if (line == null) break;
            text[i] = line;
            i++;
        }
        br.close();
    }
    
    public void fileWrite() throws IOException {
        BufferedWriter fw = new BufferedWriter(new FileWriter(fileName));
        for (j = 0; j < i; j++) {
            fw.write(text[j]);
            fw.newLine();
        }
        fw.close();
    }
    
    public String[] load() throws IOException {
        File file;
        FileChooser fileChooser = new FileChooser();
        file = fileChooser.showOpenDialog(null);
        setFileName(file.getName());
        fileRead();
        return text;
    }
    
    public File saveAs(String path) throws IOException {
        File file = new File(path);
        setFileName(file.getName());
        fileWrite();
        return file;
    }
    
}
