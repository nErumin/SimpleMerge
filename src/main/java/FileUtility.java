import java.io.*;

public class FileUtility {

    private String filename;
    private String[] text;
    private int i = 0, j;

    public void setFileName(String filename) {
        this.filename = filename;
    }

    public void FileRead() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        while (true) {
            String line = br.readLine();
            if (line == null) break;
            text[i] = line;
            i++;
        }
        br.close();
    }

    public String[] getText() {
        return text;
    }

    public void FileLoad(String name) throws IOException {
        setFileName(name);
        FileRead();
        getText();
    }

    public void FileRefresh() throws IOException {
        FileRead();
        getText();
    }

    public void FileWrite() throws IOException {
        BufferedWriter fw = new BufferedWriter(new FileWriter(filename));
        for (j = 0; j < i; j++) {
            fw.write(text[j]);
            fw.newLine();
        }
        fw.close();
    }

    public void FileSave(String name) throws IOException {
        setFileName(name);
        FileWrite();
    }

}
