package pl.masuhr.pg.jpo.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by karol on 02.10.2016.
 */
public class WorldFrame {
    private static final int SIZE_OF_FIELD = 30;
    private static final int FIELD_MARGIN = 3;
    private static final Dimension FIELD_SIZE = new Dimension(SIZE_OF_FIELD, SIZE_OF_FIELD);

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
}