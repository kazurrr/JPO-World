package pl.masuhr.pg.jpo.controller;

import javax.swing.*;
import java.awt.*;

/**
 * JPO-Zaliczenie
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
        info("Round: 0");
    }

    public void info(Point point, String message) {
        if (isSetUp()) {
            String logMessage = "Field [" + point.x + ":" + point.y + "] " + message;
            System.out.println(logMessage);
            logOutput.append(logMessage + "\n");
        }
    }

    public void info(String message) {
        if (isSetUp()) {
            System.out.println(message);
            logOutput.append(message + "\n");
        }
    }

    public void clear() {
        if (isSetUp()) {
            logOutput.setText("");
        }
    }

    public void newRound(int counter) {
        clear();
        info("Round: " + Integer.toString(counter));
    }

    private boolean isSetUp() {
        return logOutput != null;
    }
}
