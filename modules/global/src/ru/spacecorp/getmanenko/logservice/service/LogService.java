package ru.spacecorp.getmanenko.logservice.service;


import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.global.FileStorageException;
import ru.spacecorp.getmanenko.logservice.util.ConsoleTextColor;

import java.io.IOException;

public interface LogService {
    String NAME = "logservice_LogService";

    void writerFilesAutoScan(String message, String dir, String name, long time);

    void writerFilesManualScan(String message, String dir, String name, long time);

    void writerFilesLogOneDay(String message, String dir);

    void writerFilesManual(String dir, String name, String message);

    void deleteFile(String dir, String name);

    FileDescriptor prepareLogFileInDir(String path, String logName) throws IOException, FileStorageException;

    void writerLogToConsole(String logMessage);

    void writerTextToFile(String dir, String name, String message) throws IOException;

    StringBuffer readTextFromFileAndDelete(String dir, String name);

    void colorTextToConsole(ConsoleTextColor color, String text);


}