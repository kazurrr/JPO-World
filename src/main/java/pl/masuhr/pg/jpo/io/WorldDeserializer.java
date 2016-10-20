package pl.masuhr.pg.jpo.io;

import pl.masuhr.pg.jpo.controller.World;
import pl.masuhr.pg.jpo.io.model.WorldParsingException;
import pl.masuhr.pg.jpo.model.Organism;
import pl.masuhr.pg.jpo.util.ClassFinder;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * JPO-Zaliczenie
 * Created by karol on 01.10.2016.
 */
public class WorldDeserializer {
    private static final String FILE_PATH = "world-serialized.txt";
    private List<String> allOrganisms;
    private World newWorld;
    private int sizeOfWorld;

    public World load() throws WorldParsingException {
        readContentFromFile();
        prepareWorld();
        createOrganisms();

        return newWorld;
    }

    private void readContentFromFile() throws WorldParsingException {
        try {
            String fileContent = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
            allOrganisms = new ArrayList<>(Arrays.asList(fileContent.split("\n")));

        } catch (IOException e) {
            throw new WorldParsingException("Cannot read file content with world state");
        }
    }

    private void prepareWorld() {
        newWorld = new World();
        sizeOfWorld = getSizeOfWorld();
    }

    private int getSizeOfWorld() {
        String firstLine = allOrganisms.get(0);
        allOrganisms.remove(0);
        return Integer.parseInt(firstLine.split(":")[1]);       //First line looks like this: '[World]|size:6'
    }

    private void createOrganisms() throws WorldParsingException {
        for (String organism : allOrganisms) {
            Organism newOrganismInstance = getInstance(organism);
            newWorld.addNewOrganism(newOrganismInstance);
        }
        newWorld.mergeAddList();
    }

    private Organism getInstance(String organismAttributes) throws WorldParsingException {
        try {
            OrganismParser organismParser = new OrganismParser(organismAttributes);

            Class newOrganismClass = ClassFinder.find(organismParser.getString("type"));
            Organism newOrganism = (Organism) newOrganismClass.newInstance();

            newOrganism.setPosition(new Point(organismParser.getInt("x"), organismParser.getInt("y")));
            newOrganism.setMyWorld(newWorld);

            return newOrganism;
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            throw new WorldParsingException("Cannot create new instance of organism");
        }
    }
}
