package pl.masuhr.pg.jpo.io;

import pl.masuhr.pg.jpo.controller.World;
import pl.masuhr.pg.jpo.controller.queue.IQueue;

import java.io.*;

import static pl.masuhr.pg.jpo.gui.Properties.WORLD_SIZE;

/**
 * JPO-Zaliczenie
 * Created by karol on 01.10.2016.
 */
public class WorldSerializer {
    private static final String FILE_PATH = "world-serialized.txt";
    private IQueue queue;

    public void save(World world) {
        queue = world.getQueue();

        String serializedWorld = "";
        serializedWorld += getWorldSerialized();
        serializedWorld += getOrganismsSerialized();

        writeToFile(serializedWorld);
    }

    private String getWorldSerialized() {
        return "[World]|size:" + WORLD_SIZE + "\n";
    }

    private String getOrganismsSerialized() {
        String serializedOrganisms = "";

        queue.toStart();
        while (queue.hasNext()) {
            serializedOrganisms += queue.next().toString() + "\n";
        }

        return serializedOrganisms;
    }

    private void writeToFile(String content) {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(FILE_PATH), "utf-8"))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
