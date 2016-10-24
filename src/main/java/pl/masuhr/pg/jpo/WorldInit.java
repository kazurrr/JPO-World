package pl.masuhr.pg.jpo;

import pl.masuhr.pg.jpo.gui.MainFrame;

import javax.swing.*;

/**
 * JPO-Zaliczenie
 * Created by karol on 05.10.2016.
 */
public class WorldInit {

    public static void main(String[] args) {
        //ToDo chose best GUI template http://stackoverflow.com/questions/3954616/java-look-and-feel-lf
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        new MainFrame();
    }
}
