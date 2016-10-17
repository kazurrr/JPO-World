package pl.masuhr.pg.jpo.model;

import pl.masuhr.pg.jpo.controller.Logger;
import pl.masuhr.pg.jpo.controller.World;
import pl.masuhr.pg.jpo.util.Position;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * JPO-Zaliczenie
 * Created by karol on 01.10.2016.
 */
public class Plant extends Organism {
    private static final int MULTIPLICATION_PROBABILITY = 3;
    private Logger logger = Logger.getInstance();

    public Plant(World myWorld, Point position) {
        super(myWorld, position);
    }

    @Override
    public void action() {
        if (isItTimeForMultiplication()) {
            multiplication();
        }
    }

    @Override
    public void collisionWith(Organism organism) {
        organism.setPosition(this.getPosition());
        myWorld.removeOrganism(this);
        logger.info(organism.getPosition(), organism.draw() + " ate " + this.draw());
    }

    private void multiplication() {
        Point newPosition = new Position().getNextFree(myWorld, this.getPosition());
        if (newPosition != null) {
            myWorld.addNewOrganism(makeNewPlant(newPosition));
        }
    }

    private Plant makeNewPlant(Point newPosition) {
        try {
            Plant newPlant = this.getClass().newInstance();
            newPlant.setMyWorld(myWorld);
            newPlant.setPosition(newPosition);

            return newPlant;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }


    private boolean isItTimeForMultiplication() {
        int random = ThreadLocalRandom.current().nextInt(0, MULTIPLICATION_PROBABILITY + 1);
        return random == 0;
    }

}
