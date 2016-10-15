package pl.masuhr.pg.jpo.gui;

import pl.masuhr.pg.jpo.controller.*;
import pl.masuhr.pg.jpo.model.Organism;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

import static pl.masuhr.pg.jpo.gui.Properties.FIELD_MARGIN;
import static pl.masuhr.pg.jpo.gui.Properties.FIELD_SIZE;
import static pl.masuhr.pg.jpo.gui.Properties.SIZE_OF_FIELD;

/**
 * JPO-Zaliczenie
 * Created by karol on 02.10.2016.
 */
public class WorldFrame {
    private JPanel panel;
    private int sizeOfWorld = Properties.WORLD_SIZE;
    private JPanel buttonPanel;
    private JButton buttons[][];

    public WorldFrame(JPanel panel) {
        this.panel = panel;

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
        Iterable<Organism> allOrganisms = world.organismIterable();
        clean();
        for (Organism organism : allOrganisms) {
            if (!organism.isMarkToRemove())
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
            URL pathToImage = getClass().getClassLoader().getResource(organism.draw() + ".jpg");
            Image img = ImageIO.read(pathToImage);
            buttons[organism.getPosition().x][organism.getPosition().y].setIcon(new ImageIcon(img));
        } catch (IOException | NullPointerException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}