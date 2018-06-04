package model;

import utility.StringUtility;

import java.io.*;
import java.nio.channels.FileLock;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Supplier;

/**
 * 백업 파일을 스케쥴링하고, 내용을 불러오는 클래스입니다.
 */
public class BackupScheduler {
    private static final long BACKUP_PERIOD = 1000;

    private Supplier<String> supplier;
    private Timer jobScheduler;
    private FileTransmitter transmitter;

    public BackupScheduler(Supplier<String> supplier, String path)
            throws IOException {
        this.supplier = supplier;
        this.jobScheduler = new Timer(true);
        this.transmitter = new FileTransmitter(path);
    }

    public void start() {
        jobScheduler.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    backup();
                } catch (IOException exception) {
                    System.out.println(exception.getMessage());
                }
            }
        }, 0, BACKUP_PERIOD);
    }

    public void finish() {
        System.out.println("cancel");
        jobScheduler.cancel();
    }

    private void backup() throws IOException {
        transmitter.save(supplier.get());
    }

    public String loadBackup() throws IOException {
        return transmitter.load();
    }
}
