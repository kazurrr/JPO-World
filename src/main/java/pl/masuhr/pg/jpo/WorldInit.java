package pl.masuhr.pg.jpo;

import com.alee.laf.WebLookAndFeel;
import pl.masuhr.pg.jpo.gui.MainFrame;

import javax.swing.*;

/**
 * JPO-Zaliczenie
 * Created by karol on 01.10.2016.
 */
public class WorldInit {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(WebLookAndFeel::install);

        new MainFrame();
    }
}
