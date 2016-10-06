package pl.masuhr.pg.jpo.gui;

import pl.masuhr.pg.jpo.Properties;
import pl.masuhr.pg.jpo.controller.Logger;
import pl.masuhr.pg.jpo.controller.World;

import javax.swing.*;

import java.awt.*;

import static pl.masuhr.pg.jpo.Properties.*;

/**
 * Created by karol on 05.10.2016.
 */
public class MainFrame extends JFrame {
    private static final int SIZE_OF_WORLD = 5;

    private JPanel worldPanel;
    private JPanel rootPanel;
    private JButton saveButton;
    private JButton nextRoundButton;
    private JButton loadButton;
    private JPanel programPanel;
    private JTextArea logArea;

    private Logger logger = Logger.getInstance();
    private WorldFrame worldFrame;
    private World greatWorld;

    public MainFrame() {
        super(WINDOW_TITLE);
        initComponents();
        setFrameSize();
        initWorld();
    }

    private void initComponents() {
        worldFrame = new WorldFrame(worldPanel, SIZE_OF_WORLD);
        add(rootPanel);
        logger.setUpLogger(logArea);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void setFrameSize() { //ToDo make it better
        int width = (SIZE_OF_WORLD * (SIZE_OF_FIELD + FIELD_MARGIN + 15)) + LOG_AREA_WIDTH;
        int height = (SIZE_OF_WORLD + 1) * (SIZE_OF_FIELD + FIELD_MARGIN);
        setSize(width, height);

        programPanel.setMaximumSize(new Dimension(LOG_AREA_WIDTH, -1));
    }

    private void initWorld() {
        greatWorld = new World(SIZE_OF_WORLD);
    }

}
