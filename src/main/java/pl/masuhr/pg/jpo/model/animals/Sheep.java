package pl.masuhr.pg.jpo.model.animals;

import pl.masuhr.pg.jpo.controller.World;
import pl.masuhr.pg.jpo.model.Animal;

import java.awt.*;

/**
 * Created by karol on 06.10.2016.
 */
public class Sheep extends Animal {
    public Sheep(World myWorld, Point point) {
        super(myWorld, point);
        setInitiative(5);
    }
}
