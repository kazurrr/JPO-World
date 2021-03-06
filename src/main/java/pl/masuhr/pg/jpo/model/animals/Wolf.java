package pl.masuhr.pg.jpo.model.animals;

import pl.masuhr.pg.jpo.controller.World;
import pl.masuhr.pg.jpo.model.Animal;
import pl.masuhr.pg.jpo.model.annotation.OrganismImpl;

import java.awt.*;

/**
 * JPO-Zaliczenie
 * Created by karol on 06.10.2016.
 */
@OrganismImpl
public class Wolf extends Animal {

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
        setInitiative(5);
    }
}
