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
public class Belladonna extends Plant {
    private Logger logger = Logger.getInstance();

    public Belladonna() {
        super(null, null);
    }

    public Belladonna(World myWorld, Point position) {
        super(myWorld, position);
    }

    @Override
    public void collisionWith(Organism organism) {
        myWorld.removeOrganism(this);
        myWorld.removeOrganism(organism);

        logger.info(this.getPosition(), organism.draw() + " ate " + this.draw() + ".\n"
                + organism.draw() + " also died.");
    }
}
