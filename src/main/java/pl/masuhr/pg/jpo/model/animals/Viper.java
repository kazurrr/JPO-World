package pl.masuhr.pg.jpo.model.animals;

import pl.masuhr.pg.jpo.controller.Logger;
import pl.masuhr.pg.jpo.controller.World;
import pl.masuhr.pg.jpo.model.Animal;
import pl.masuhr.pg.jpo.model.annotation.OrganismImpl;

import java.awt.*;

/**
 * JPO-Zaliczenie
 * Created by karol on 16.10.2016.
 */
@OrganismImpl
public class Viper extends Animal {
    private Logger logger = Logger.getInstance();

    public Viper() {
        super(null, null);
        prepareAnimal();
    }

    public Viper(World myWorld, Point point) {
        super(myWorld, point);
        prepareAnimal();
    }

    private void prepareAnimal() {
        setStrength(2);
        setInitiative(3);
    }

    @Override
    protected void attackBy(Animal opponent) {
        String logMessage = " is attacked by " + opponent.draw() + ".\n";
        Point pointToLog = new Point();

        int fightResult = ((Integer) this.getStrength()).compareTo(opponent.getStrength());

        switch (fightResult) {
            case -1:
            case 0:
                myWorld.removeOrganism(this);
                myWorld.removeOrganism(opponent);

                logMessage += opponent.draw() + " won, but was poisoned and also died.";
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

    @Override
    protected void afterAttack(Animal opponent, boolean amIDead) {
        if (amIDead) {
            myWorld.removeOrganism(opponent);
            logger.info(opponent.getPosition(), opponent.draw() + " was poisoned and also died.");
        }
    }
}
