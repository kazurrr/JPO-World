package pl.masuhr.pg.jpo;

import pl.masuhr.pg.jpo.gui.HandleFrame;
import pl.masuhr.pg.jpo.gui.WorldFrame;
import static pl.masuhr.pg.jpo.Properties.*;

import javax.swing.*;

import static java.lang.Thread.sleep;

/**
 * Created by karol on 01.10.2016.
 */
public class GUI extends JFrame {

    private WorldFrame worldFrame;
    private JPanel programFrame;

    public GUI() {
        initComponents();
        configureComponents();
    }

    private void initComponents() {
//        add(worldFrame = new WorldFrame(10));
//        add(programFrame = new HandleFrame(worldFrame.getHeight()).getHandlePanel());

    }

    private void configureComponents() {
//        setSize(worldFrame.getWidth() + LOG_AREA_WIDTH + 6, worldFrame.getHeight() + 22);
    }

}
