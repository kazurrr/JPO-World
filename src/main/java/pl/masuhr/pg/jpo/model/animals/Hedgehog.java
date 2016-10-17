package pl.masuhr.pg.jpo.model.animals;

import pl.masuhr.pg.jpo.controller.World;
import pl.masuhr.pg.jpo.model.Animal;

import java.awt.*;

/**
 * JPO-Zaliczenie
 * Created by karol on 16.10.2016.
 */
public class Hedgehog extends Animal {

    public Hedgehog() {
        super(null, null);
        prepareAnimal();
    }

    public Hedgehog(World myWorld, Point point) {
        super(myWorld, point);
        prepareAnimal();
    }

    private void prepareAnimal() {
        setStrength(2);
        setInitiative(3);
    }
}
