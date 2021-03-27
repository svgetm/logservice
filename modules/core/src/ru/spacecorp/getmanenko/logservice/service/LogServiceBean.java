package ru.spacecorp.getmanenko.logservice.service;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.FileLoader;
import com.haulmont.cuba.core.global.FileStorageException;
import com.haulmont.cuba.core.global.Metadata;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import com.haulmont.cuba.core.entity.FileDescriptor;
import ru.spacecorp.getmanenko.logservice.util.ConsoleTextColor;

import javax.inject.Inject;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import java.io.*;
import java.text.SimpleDateFormat;


@Service(LogService.NAME)
public class LogServiceBean implements LogService {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
    private SimpleDateFormat simpleDateFormatMinutes = new SimpleDateFormat("dd-MM-yyyy HH-mm");
    private SimpleDateFormat simpleDateFormatOneDay = new SimpleDateFormat("dd-MM-yyyy");
    private SimpleDateFormat simpleDateFormatManual = new SimpleDateFormat("HH-mm");
    private SimpleDateFormat simpleDateFormatCustom = new SimpleDateFormat("yyyy-MM-dd");
    @Inject
    private Metadata metadata;
    @Inject
    private FileLoader fileLoader;
    @Inject
    private DataManager dataManager;

    private File createFileEveryMinutes(String dir, String name, long lastTimeUpdate) {

        File fileLog = new File(String.format("%s%s_%s.txt", dir, "LogFile", simpleDateFormatMinutes.format(System.currentTimeMillis())));

        return fileLog;

    }

    private File createFilePeriod(String dir, String name, long lastTimeUpdate) {

        File fileLog = new File(String.format("%s%s_%s_%s.txt", dir, "LogFile",
                simpleDateFormatMinutes.format(lastTimeUpdate), simpleDateFormatManual.format(System.currentTimeMillis())));

        return fileLog;
    }

    private File createFilePeriodDayOne(String dir, String name) {

        File fileLog = new File(String.format("%s%s_%s.txt", dir, "LogFile", simpleDateFormatOneDay.format(System.currentTimeMillis())));

        return fileLog;
    }

    private File createFile(String dir, String name) {

        File fileLog = new File(String.format("%s%s.txt", dir, name));

        return fileLog;
    }

    @Override
    public void colorTextToConsole(ConsoleTextColor color, String text) {
        System.out.println(color.value() + text + ConsoleTextColor.RESET.value());
    }

    @Override
    public void writerTextToFile(String dir, String name, String message) {

        try {
            FileWriter fileWriter = new FileWriter(createFile(dir, name), true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.append(message).append(" ").append("\n");
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public StringBuffer readTextFromFileAndDelete(String dir, String name) {

        StringBuffer stringBuffer = new StringBuffer();
        try {

            FileReader fr = new FileReader(createFile(dir, name));
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();

            while (line != null) {
                stringBuffer.append(line);
                line = reader.readLine();
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        deleteFile(dir,name);

        return stringBuffer;
    }


    @Override
    public void writerFilesAutoScan(String message, String dir, String name, long lastTimeUpdate) {

        try {
            FileWriter fileWriter = new FileWriter(createFileEveryMinutes(dir, name, lastTimeUpdate), true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);


            bufferedWriter.append(simpleDateFormat.format(System.currentTimeMillis()) + message + name + "\n");
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writerFilesManualScan(String message, String dir, String name, long lastTimeUpdate) {

        try {
            FileWriter fileWriter = new FileWriter(createFilePeriod(dir, name, lastTimeUpdate), true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);


            bufferedWriter.append(simpleDateFormat.format(System.currentTimeMillis()) + message + name + "\n");
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writerFilesLogOneDay(String message, String dir) {

        try {
            FileWriter fileWriter = new FileWriter(createFilePeriodDayOne(dir, "DayOne"), true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);


            bufferedWriter.append(simpleDateFormat.format(System.currentTimeMillis()) + message + "\n");
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writerFilesManual(String dir, String name, String message) {

        try {
            FileWriter fileWriter = new FileWriter(createFile(dir, name), true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);


            bufferedWriter.append(simpleDateFormatCustom.format(System.currentTimeMillis()) + " " + message + "\n");
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFile(String dir, String name) {

        createFile(dir, name).delete();

    }

    @Override
    public FileDescriptor prepareLogFileInDir(String path, String logName) throws IOException, FileStorageException {
        FileDescriptor fileDescriptor = metadata.create(FileDescriptor.class);
        fileDescriptor.setName(logName);
        fileDescriptor.setExtension("txt");
        fileDescriptor.setCreateDate(new Date());
        File file = new File(path + logName + ".txt");
        StringBuilder stringBuilder = new StringBuilder(FileUtils.readFileToString(file, StandardCharsets.UTF_8));
        fileLoader.saveStream(fileDescriptor, () -> new ByteArrayInputStream(stringBuilder.toString().getBytes()));
        fileDescriptor = dataManager.commit(fileDescriptor);
        return fileDescriptor;
    }

    @Override
    public void writerLogToConsole(String logMessage) {
        System.out.println(logMessage);
    }

}