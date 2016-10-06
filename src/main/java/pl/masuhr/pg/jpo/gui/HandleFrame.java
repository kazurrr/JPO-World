package pl.masuhr.pg.jpo.gui;

import static pl.masuhr.pg.jpo.Properties.*;

import javax.swing.*;

/**
 * Created by karol on 05.10.2016.
 */
public class HandleFrame {
    private JPanel newPanel = new JPanel();
    private int frameHeight;

    public HandleFrame(int frameHeight) {
        this.frameHeight = frameHeight;
        addLogArea();
    }

    private void addLogArea() {
        JTextPane logPanel = new JTextPane();
        logPanel.setEnabled(false);
        logPanel.setSize(LOG_AREA_WIDTH, frameHeight);
    }

    public JPanel getHandlePanel() {
        return newPanel;
    }

}
