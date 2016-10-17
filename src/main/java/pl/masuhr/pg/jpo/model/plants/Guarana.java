package pl.masuhr.pg.jpo.model.plants;

import pl.masuhr.pg.jpo.controller.Logger;
import pl.masuhr.pg.jpo.controller.World;
import pl.masuhr.pg.jpo.model.Organism;
import pl.masuhr.pg.jpo.model.Plant;

import java.awt.*;

/**
 * JPO-Zaliczenie
 * Created by karol on 16.10.2016.
 */
public class Guarana extends Plant {
    private static final int INCREASE_STRENGTH = 3;
    private Logger logger = Logger.getInstance();


    public Guarana() {
        super(null, null);
    }

    public Guarana(World myWorld, Point position) {
        super(myWorld, position);
    }

    @Override
    public void collisionWith(Organism organism) {
        int currentStrength = organism.getStrength();

        organism.setStrength(currentStrength + INCREASE_STRENGTH);
        organism.setPosition(this.getPosition());
        myWorld.removeOrganism(this);

        logger.info(organism.getPosition(), organism.draw() + " ate " + this.draw() + ".\n"
                + organism.draw() + " strength increased by 3. Now it is:" + organism.getStrength());
    }
}
