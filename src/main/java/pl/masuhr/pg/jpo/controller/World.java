package pl.masuhr.pg.jpo.controller;

import pl.masuhr.pg.jpo.model.Organism;
import pl.masuhr.pg.jpo.model.animals.Sheep;
import pl.masuhr.pg.jpo.model.animals.Wolf;

import java.awt.*;
import java.util.Collections;
import java.util.Iterator;

/**
 * Created by karol on 01.10.2016.
 */
public class World implements Iterable<Organism> {
    private int sizeOfWorld;
    private Queue queue = new Queue();
    private Logger logger = Logger.getInstance();

    @Override
    public Iterator<Organism> iterator() {
        return queue.iterator();
    }

    public World(int sizeOfWorld) {
        this.sizeOfWorld = sizeOfWorld;
        addRandomOrganisms();
    }

    private void addRandomOrganisms() {
        addNewOrganism(new Wolf(this, new Point(1, 1)));
        addNewOrganism(new Wolf(this, new Point(1, 1)));
        addNewOrganism(new Sheep(this, new Point(0, 1)));
        addNewOrganism(new Sheep(this, new Point(4, 4)));
        addNewOrganism(new Sheep(this, new Point(6, 1)));
    }

    public void addNewOrganism(Organism organism) {
        Point point = organism.getLocation();

        if (queue.isFieldOccupied(point)) {
            logger.info(point, "is occupied, cannot create new organism.");
        } else if (!isPointInWorld(point)) {
            logger.info(point, "do not belong to world.");
        } else {
            queue.add(organism);
            logger.info(point, "Added new organism: " + organism.draw());
        }
    }

    public boolean isPointInWorld(Point point) {
        return point.x < sizeOfWorld && point.y < sizeOfWorld;
    }


    public void performRound() {
        queue.sort();
        for (Organism currentOrganism : queue) {
            currentOrganism.action();
            System.out.println(currentOrganism.draw() + "   Ini:" + currentOrganism.initiative);
        }
    }
}
