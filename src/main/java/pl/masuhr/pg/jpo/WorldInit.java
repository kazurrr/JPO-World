package pl.masuhr.pg.jpo;

import javax.swing.*;

/**
 * Created by karol on 01.10.2016.
 */
public class WorldInit {
    private static final String WINDOW_TITLE = "Karol Masuhr 152660";


    public static void main(String[] args) {
        //ToDo init world, create gui
        GUI mainFrame = new GUI();

        mainFrame.setTitle(WINDOW_TITLE);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);
    }
}
