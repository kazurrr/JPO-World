package pl.masuhr.pg.jpo.controller;

import javax.swing.*;
import java.awt.*;

/**
 * Created by karol on 06.10.2016.
 */
public class Logger {
    private static Logger ourInstance = new Logger();
    private JTextArea logOutput;

    public static Logger getInstance() {
        return ourInstance;
    }

    private Logger() {
    }

    public void setUpLogger(JTextArea logOutput) {
        this.logOutput = logOutput;
    }

    public void info (Point point, String message) {
        if (isSetUp()) {
            StringBuilder logMessage = new StringBuilder();
            logMessage.append("Field [").append(point.x).append(":").append(point.y).append("] ");
            logMessage.append(message).append("\n");
            logOutput.append(logMessage.toString());
        }
    }

    public void info(String message) {
        if (isSetUp()) {
            logOutput.append(message + "\n");
        }
    }

    public void clear() {
        if(isSetUp()) {
            logOutput.setText("");
        }
    }

    private boolean isSetUp() {
        return logOutput != null;
    }
}
