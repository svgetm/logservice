package ru.spacecorp.getmanenko.logservice;

import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.gui.AppConfig;
import com.haulmont.cuba.gui.components.Frame;
import org.springframework.stereotype.Component;


@Component
public final class LoggingHelper {
    public final static void fileDownload(FileDescriptor fileDescriptor, Frame currentFrame) {
        AppConfig.createExportDisplay(currentFrame).show(fileDescriptor);
    }
}
