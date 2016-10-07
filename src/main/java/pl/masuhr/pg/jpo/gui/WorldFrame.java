package pl.masuhr.pg.jpo.gui;

import pl.masuhr.pg.jpo.controller.*;
import pl.masuhr.pg.jpo.model.Organism;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static pl.masuhr.pg.jpo.gui.Properties.FIELD_MARGIN;
import static pl.masuhr.pg.jpo.gui.Properties.FIELD_SIZE;
import static pl.masuhr.pg.jpo.gui.Properties.SIZE_OF_FIELD;

/**
 * Created by karol on 02.10.2016.
 */
public class WorldFrame {
    private JPanel panel;
    private int sizeOfWorld;
    private JPanel buttonPanel;
    private JButton buttons[][];

    public WorldFrame(JPanel panel, int sizeOfWorld) {
        this.panel = panel;
        this.sizeOfWorld = sizeOfWorld;

        prepareButtonPanel();
        createButtons();
    }

    private void prepareButtonPanel() {
        panel.setLayout(null);

        panel.setSize(sizeOfWorld * SIZE_OF_FIELD + ((sizeOfWorld + 1) * FIELD_MARGIN),
                sizeOfWorld * SIZE_OF_FIELD + ((sizeOfWorld + 1) * FIELD_MARGIN));

        buttonPanel = new JPanel();
        buttonPanel.setBounds(FIELD_MARGIN, FIELD_MARGIN, 0, 0);
        buttonPanel.setLayout(new GridLayout(sizeOfWorld, sizeOfWorld, FIELD_MARGIN, FIELD_MARGIN));
        buttonPanel.setSize(sizeOfWorld * SIZE_OF_FIELD + ((sizeOfWorld - 1) * FIELD_MARGIN),
                sizeOfWorld * SIZE_OF_FIELD + ((sizeOfWorld - 1) * FIELD_MARGIN));

        buttons = new JButton[sizeOfWorld][sizeOfWorld];
    }

    private void createButtons() {
        for (int i = 0; i < sizeOfWorld; i++) {
            for (int j = 0; j < sizeOfWorld; j++) {
                buttons[j][i] = new JButton();
                buttons[j][i].setPreferredSize(FIELD_SIZE);
                buttonPanel.add(buttons[j][i]);
            }
        }

        panel.add(buttonPanel, BorderLayout.CENTER);
    }

    public void render(World world) {
        clean();
        for (Organism organism : world) {
            setIcon(organism);
        }
    }

    private void clean() {
        for (int i = 0; i < sizeOfWorld; i++) {
            for (int j = 0; j < sizeOfWorld; j++) {
                buttons[i][j].setIcon(null);
            }
        }
    }

    private void setIcon(Organism organism) {
        try {
            Image img = ImageIO.read(getClass().getClassLoader().getResource(organism.draw() + ".jpg"));
            buttons[organism.getLocation().x][organism.getLocation().y].setIcon(new ImageIcon(img));
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }
}