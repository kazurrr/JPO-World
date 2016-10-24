package pl.masuhr.pg.jpo.service;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * JPO-Zaliczenie
 * Created by karol on 24.10.2016.
 */
public class ImageService {
    private static ImageService ourInstance = new ImageService();
    private Map<String, ImageIcon> map = new HashMap<>(); //Object is containing String


    public static ImageService getInstance() {
        return ourInstance;
    }

    private ImageService() {

    }

    private void loadImagesForOrganisms() {

    }

    private Image getImage(String name) {
        try {
            URL pathToImage = getClass().getClassLoader().getResource(name);
            return ImageIO.read(pathToImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
