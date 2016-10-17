package pl.masuhr.pg.jpo.controller;

import pl.masuhr.pg.jpo.controller.queue.IQueue;
import pl.masuhr.pg.jpo.controller.queue.ListQueue;
import pl.masuhr.pg.jpo.gui.*;
import pl.masuhr.pg.jpo.gui.Properties;
import pl.masuhr.pg.jpo.model.Organism;
import pl.masuhr.pg.jpo.model.animals.Sheep;
import pl.masuhr.pg.jpo.model.animals.Wolf;
import pl.masuhr.pg.jpo.model.plants.Grass;
import pl.masuhr.pg.jpo.util.Position;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * JPO-Zaliczenie
 * Created by karol on 01.10.2016.
 */
public class World {
    private int sizeOfWorld = Properties.WORLD_SIZE;
    private IQueue queue = new ListQueue();
    private Logger logger = Logger.getInstance();

    public World() {
        addRandomOrganisms();
    }

    public void removeOrganism(Organism organism) {
        queue.remove(organism);
//        organism.remove();
    }

    private void addRandomOrganisms() {
        addNewOrganism(new Sheep(this, new Position().getRandom()));

        addNewOrganism(new Grass(this, new Position().getRandom()));
        queue.mergeAddList();
    }

    public void addNewOrganism(Organism organism) {
        Point point = organism.getPosition();

        if (isFieldOccupied(point)) {
            logger.info(point, "is occupied, cannot create new organism.");
        } else if (!isPointInWorld(point)) {
            logger.info(point, "do not belong to world.");
        } else {
            queue.add(organism);
            logger.info(point, "Added new organism: " + organism.draw());
        }
    }

    public boolean isFieldOccupied(Point point) {
        return queue.isFieldOccupied(point);
    }

    public Organism getOrganismFromField(Point point) {
        return queue.getOrganismFromField(point);
    }

    public boolean isPointInWorld(Point point) {
        boolean plus = point.x < sizeOfWorld && point.y < sizeOfWorld;
        boolean minus = point.x >= 0 && point.y >= 0;
        return plus && minus;
    }

    public void performRound() {
        queue.toStart();
        while (queue.hasNext()) {
            queue.next().action();
        }
        queue.mergeAddList();
    }

    public Iterable<Organism> organismIterable() {
        return queue.iterator();
    }
}
