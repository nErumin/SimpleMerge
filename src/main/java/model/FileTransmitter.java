package model;

import utility.StringUtility;

import java.io.*;
import java.nio.channels.FileLock;

public class FileTransmitter implements Transmitter<String> {
    private final RandomAccessFile file;
    private final FileLock fileLock;

    public FileTransmitter(String path) throws IOException {
        file = new RandomAccessFile(path, "rw");
        fileLock = file.getChannel().lock();
    }

    public String load() {
        try {
            StringBuilder contentBuilder = new StringBuilder();
            String line;
            while ((line = file.readLine()) != null) {
                contentBuilder.append(line);
                contentBuilder.append(StringUtility.LINE_SEPARATOR);
            }

            file.seek(0);
            return contentBuilder.toString();
        } catch (IOException exception) {
            return StringUtility.EMPTY_STRING;
        }
    }

    public void save(String content) {
        try {
            file.writeBytes(content);
            file.seek(0);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public File saveAs(String path) {
        String fileContent = load();
        File newFile = new File(path);

        try (FileWriter fw = new FileWriter(newFile.getAbsoluteFile());
             BufferedWriter bw = new BufferedWriter(fw)) {
            if (!newFile.exists() && newFile.createNewFile()) {
                return null;
            }

            bw.write(fileContent);
            return newFile;
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }

        return null;
    }

    public void close() throws IOException {
        fileLock.release();
        file.close();
    }
}
