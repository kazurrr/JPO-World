package pl.masuhr.pg.jpo.gui;

import pl.masuhr.pg.jpo.controller.*;
import pl.masuhr.pg.jpo.model.Organism;
import pl.masuhr.pg.jpo.service.IconService;

import javax.swing.*;
import java.awt.*;

import static pl.masuhr.pg.jpo.gui.Properties.FIELD_MARGIN;
import static pl.masuhr.pg.jpo.gui.Properties.FIELD_SIZE;
import static pl.masuhr.pg.jpo.gui.Properties.SIZE_OF_FIELD;

/**
 * JPO-Zaliczenie
 * Created by karol on 02.10.2016.
 */
public class WorldFrame {
    private IconService icons = IconService.getInstance();
    private PopupMenu popupMenu;

    private JPanel panel;
    private int sizeOfWorld = Properties.WORLD_SIZE;
    private JPanel buttonPanel;
    private JButton buttons[][];

    public WorldFrame(JPanel panel, World world) {
        this.panel = panel;
        popupMenu = new PopupMenu(world);

        prepareButtonPanel();
        createButtons();
    }

    protected void prepareButtonPanel() {
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
                addListener(buttons[j][i], j, i);
            }
        }

        panel.add(buttonPanel, BorderLayout.CENTER);
    }

    private void addListener(JButton button, int x, int y) {
        button.addActionListener(e -> {
            popupMenu.show(buttonPanel, x * SIZE_OF_FIELD, y * SIZE_OF_FIELD);
            popupMenu.setPosition(new Point(x, y));
            popupMenu.setButtonReference(button);
            popupMenu.setVisible(true);
        });
    }

    public void render(World world) {
        clean();
        for (Organism organism : world.organismIterable()) {
            if (!organism.isMarkToRemove())
                setIcon(organism);
        }
    }

    private void clean() {
        for (int i = 0; i < sizeOfWorld; i++) {
            for (int j = 0; j < sizeOfWorld; j++) {
                buttons[i][j].setIcon(icons.getImage("Blank"));
            }
        }
    }

    private void setIcon(Organism organism) {
        JButton buttonToSetIcon = buttons[organism.getPosition().x][organism.getPosition().y];
        String imageName = organism.draw();

        buttonToSetIcon.setIcon(icons.getImage(imageName));
    }
}