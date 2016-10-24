package pl.masuhr.pg.jpo.service;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static pl.masuhr.pg.jpo.util.ClassFinder.getAllClasses;

/**
 * JPO-Zaliczenie
 * Created by karol on 24.10.2016.
 */
public class IconService {
    private static IconService ourInstance = new IconService();
    private Map<String, ImageIcon> cachedIcons = new HashMap<>();

    public static IconService getInstance() {
        return ourInstance;
    }

    public ImageIcon getImage(String name) {
        return cachedIcons.get(name);
    }

    private IconService() {
        loadImagesForOrganisms();
    }

    private void loadImagesForOrganisms() {
        for (Class animal : getAllClasses()) {
            ImageIcon icon = readImage(animal.getSimpleName());
            cachedIcons.put(animal.getSimpleName(), icon);
        }
        cachedIcons.put("Blank", readImage("Blank"));
    }

    private ImageIcon readImage(String name) {
        try {
            name = name + ".jpg";
            URL pathToImage = getClass().getClassLoader().getResource(name);
            return new ImageIcon(ImageIO.read(pathToImage));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
