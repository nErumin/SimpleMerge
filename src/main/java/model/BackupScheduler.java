package model;

import utility.StringUtility;

import java.io.*;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Supplier;

/**
 * 백업 파일을 스케쥴링하고, 내용을 불러오는 클래스입니다.
 */
public class BackupScheduler {
    private static final long BACKUP_PERIOD = 10000;

    private Supplier<String> supplier;
    private Timer jobScheduler;
    private String backupPath;

    public BackupScheduler(Supplier<String> supplier, String path) {
        this.supplier = supplier;
        this.backupPath = path;
        this.jobScheduler = new Timer(true);
    }

    public void start() {
        jobScheduler.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                backup();
            }
        }, 0, BACKUP_PERIOD);
    }

    public void finish() {
        System.out.println("cancel");
        jobScheduler.cancel();
    }

    private void backup() {
        File backupFile = new File(backupPath);
        try {
            if (!backupFile.exists() &&
                backupFile.createNewFile()) {
                return;
            }

            FileWriter fw = new FileWriter(backupFile.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(supplier.get());

            bw.close();
            fw.close();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public String loadBackup() {
        File backupFile = new File(backupPath);
        StringBuilder contentBuilder = new StringBuilder();

        try (Scanner scanner = new Scanner(backupFile)) {
            while (scanner.hasNextLine()) {
                contentBuilder.append(scanner.nextLine());
                contentBuilder.append(StringUtility.LINE_SEPARATOR);
            }

            return contentBuilder.toString();
        } catch (FileNotFoundException exception) {
            return StringUtility.EMPTY_STRING;
        }
    }
}
