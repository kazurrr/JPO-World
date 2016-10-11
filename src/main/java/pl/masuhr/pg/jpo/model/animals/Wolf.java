package pl.masuhr.pg.jpo.model.animals;

import pl.masuhr.pg.jpo.controller.World;
import pl.masuhr.pg.jpo.model.Animal;

import java.awt.*;

/**
 * Created by karol on 06.10.2016.
 */
public class Wolf extends Animal {
    private int strength = 9;

    public Wolf() {
        super(null, null);
        prepareAnimal();
    }
    public Wolf(World myWorld, Point point) {
        super(myWorld, point);
        prepareAnimal();
    }

    private void prepareAnimal() {
        setStrength(9);
    }
}
