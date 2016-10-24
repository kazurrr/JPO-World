package pl.masuhr.pg.jpo.gui;

import pl.masuhr.pg.jpo.controller.Logger;
import pl.masuhr.pg.jpo.controller.World;
import pl.masuhr.pg.jpo.io.WorldDeserializer;
import pl.masuhr.pg.jpo.io.WorldSerializer;
import pl.masuhr.pg.jpo.io.model.WorldParsingException;

import javax.swing.*;
import java.awt.*;

import static pl.masuhr.pg.jpo.gui.Properties.*;

/**
 * JPO-Zaliczenie
 * Created by karol on 05.10.2016.
 */
public class MainFrame extends JFrame {
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
        initWorld();
        initComponents();
        setFrameSize();
        renderWorld();
        bindActions();
    }

    private void initWorld() {
        greatWorld = new World();
        greatWorld.addRandomOrganisms();
    }

    private void initComponents() {
        worldFrame = new WorldFrame(worldPanel, greatWorld);
        add(rootPanel);
        logger.setUpLogger(logArea);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }

    private void setFrameSize() {
        setSize(getGridSize() + LOG_AREA_WIDTH, getGridSize());
        programPanel.setMaximumSize(new Dimension(LOG_AREA_WIDTH, -1));
        repaint();
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
                reload();
            } catch (WorldParsingException e1) {
                e1.printStackTrace();
            }
        });
    }

    private void reload() {
        worldPanel.removeAll();
        worldFrame = new WorldFrame(worldPanel, greatWorld);
        renderWorld();
        setFrameSize();
    }
}
