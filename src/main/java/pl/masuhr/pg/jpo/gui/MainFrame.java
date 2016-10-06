package pl.masuhr.pg.jpo.gui;

import pl.masuhr.pg.jpo.Properties;

import javax.swing.*;

import java.awt.*;

import static pl.masuhr.pg.jpo.Properties.*;

/**
 * Created by karol on 05.10.2016.
 */
public class MainFrame extends JFrame {
    private static final int SIZE_OF_WORLD = 15;

    private JPanel worldPanel;
    private JPanel rootPanel;
    private JButton saveButton;
    private JButton nextRoundButton;
    private JButton loadButton;
    private JTextPane testTextPane;
    private JPanel programPanel;

    private WorldFrame worldFrame;

    public MainFrame() {
        super("Karol Masuhr 152660");
        initComponents();
        setFrameSize();
    }

    private void initComponents() {
        worldFrame = new WorldFrame(worldPanel, SIZE_OF_WORLD);
        add(rootPanel);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void setFrameSize() { //ToDo make it better
        int width = (SIZE_OF_WORLD * (SIZE_OF_FIELD + FIELD_MARGIN + 15)) + LOG_AREA_WIDTH;
        int height = (SIZE_OF_WORLD + 1) * (SIZE_OF_FIELD + FIELD_MARGIN);

        programPanel.setMaximumSize(new Dimension(LOG_AREA_WIDTH, -1));
        setSize(width, height);
    }

    public static void main(String[] args) {
        new MainFrame();
    }

}
