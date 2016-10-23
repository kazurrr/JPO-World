package pl.masuhr.pg.jpo.gui;

import pl.masuhr.pg.jpo.controller.Logger;
import pl.masuhr.pg.jpo.controller.World;
import pl.masuhr.pg.jpo.io.WorldDeserializer;
import pl.masuhr.pg.jpo.io.model.WorldParsingException;
import pl.masuhr.pg.jpo.io.WorldSerializer;

import javax.swing.*;

import java.awt.*;

import static pl.masuhr.pg.jpo.gui.Properties.*;

/**
 * JPO-Zaliczenie
 * Created by karol on 05.10.2016.
 */
//ToDo refactor this class to remove Intellij GUI builder
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
        renderWorld();
        bindActions();
    }

    private void initComponents() {
        worldFrame = new WorldFrame(worldPanel);
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
        greatWorld = new World();
        greatWorld.addRandomOrganisms();
    }

    private void renderWorld() {
        worldFrame.render(greatWorld);
    }
    private void bindActions() {
        nextRoundButton.addActionListener(e -> {
            logger.newRound(greatWorld.getRound());
            greatWorld.performRound();
            renderWorld();
        });

        saveButton.addActionListener(e -> {
            WorldSerializer worldSerializer = new WorldSerializer();
            worldSerializer.save(greatWorld);
        });

        loadButton.addActionListener(e -> {
            try {
                WorldDeserializer worldDeserializer = new WorldDeserializer();
                greatWorld = worldDeserializer.load();
                Properties.WORLD_SIZE = worldDeserializer.worldSize();
                renderWorld();
            } catch (WorldParsingException e1) {
                e1.printStackTrace();
            }
        });
    }
}
