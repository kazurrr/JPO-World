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
public class Animal extends Organism {
    private int paralysis;
    private Logger logger = Logger.getInstance();

    public Animal(World myWorld, Point point) {
        super(myWorld, point);
        paralysis = 0;
    }

    @Override
    public void action() {
        Point newPosition = new Position().getNext(getPosition());

        if (myWorld.isFieldOccupied(newPosition)) {
            myWorld.getOrganismFromField(newPosition).collisionWith(this);
        } else {
            this.setPosition(newPosition);
        }
        age++;
    }

    @Override
    public void collisionWith(Organism opponent) {
        if (opponent.getClass().isInstance(this))
            multiplicationWith(opponent);
        else
            attackBy((Animal) opponent);
    }

    private void multiplicationWith(Organism organism) {
        Point newAnimalPosition = new Position().getNextFree(myWorld, organism.getPosition());
        if (newAnimalPosition == null) {
            return;
        }

        try {
            Animal newAnimal = this.getClass().newInstance();
            newAnimal.setPosition(newAnimalPosition);
            newAnimal.setMyWorld(myWorld);

            myWorld.addNewOrganism(newAnimal);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    protected void attackBy(Animal opponent) {
        String logMessage = " is attacked by " + opponent.draw() + ". ";
        Point pointToLog = new Point();

        int fightResult = ((Integer) this.strength).compareTo(opponent.strength);

        switch (fightResult) {
            case -1:
            case 0:
                myWorld.removeOrganism(this);
                opponent.setPosition(this.getPosition());
                logMessage += opponent.draw() + " won.";
                pointToLog = opponent.getPosition();
                break;
            case 1:
                myWorld.removeOrganism(opponent);
                logMessage += this.draw() + " won.";
                pointToLog = this.getPosition();
                break;
        }
        logger.info(pointToLog, draw() + logMessage);
    }
}
