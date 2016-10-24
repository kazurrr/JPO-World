package pl.masuhr.pg.jpo.model.plants;

import pl.masuhr.pg.jpo.controller.World;
import pl.masuhr.pg.jpo.model.Plant;
import pl.masuhr.pg.jpo.model.annotation.OrganismImpl;

import java.awt.*;

/**
 * JPO-Zaliczenie
 * Created by karol on 16.10.2016.
 */
@OrganismImpl
public class Grass extends Plant {

    public Grass() {
        super(null, null);
    }

    public Grass(World myWorld, Point position) {
        super(myWorld, position);
    }
}
