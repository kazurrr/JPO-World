package pl.masuhr.pg.jpo.controller;

import pl.masuhr.pg.jpo.model.Organism;
import pl.masuhr.pg.jpo.model.animals.Sheep;
import pl.masuhr.pg.jpo.model.animals.Wolf;

import java.awt.*;

/**
 * Created by karol on 01.10.2016.
 */
public class World {
    private int sizeOfWorld;
    private Queue queue = new Queue();
    private Logger logger = Logger.getInstance();

    public World(int sizeOfWorld) {
        this.sizeOfWorld = sizeOfWorld;
        addRandomOrganisms();
    }

    private void addRandomOrganisms() {
        addNewOrganism(new Wolf(this, new Point(1, 1)));
        addNewOrganism(new Wolf(this, new Point(1, 1)));
        addNewOrganism(new Sheep(this, new Point(0, 1)));
    }

    public void addNewOrganism(Organism organism) {
        Point point = organism.getLocation();

        if (queue.isFieldOccupied(point)) {
            logger.info(point, "is occupied, cannot create new organism.");
        }
        else {
            queue.add(organism);
            logger.info(point, "Added new organism: " + organism.draw());
        }
    }
}
