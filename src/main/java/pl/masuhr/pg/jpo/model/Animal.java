package pl.masuhr.pg.jpo.model;

import pl.masuhr.pg.jpo.controller.World;

import java.awt.*;

/**
 * Created by karol on 01.10.2016.
 */
public class Animal extends Organism {
    private int paralysis;

    public Animal(World myWorld, Point point) {
        super(myWorld, point);
        paralysis = 0;
    }

    public void action() {

    }

    public void collision() {

    }
}
