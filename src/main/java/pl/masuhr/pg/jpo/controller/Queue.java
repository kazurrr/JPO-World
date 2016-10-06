package pl.masuhr.pg.jpo.controller;

import pl.masuhr.pg.jpo.model.Organism;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by karol on 06.10.2016.
 */
public class Queue {
    private List<Organism> allOrganisms = new ArrayList<Organism>();

    public void add(Organism organism) {
        allOrganisms.add(organism);
    }

    private void remove(Organism organism) {
        allOrganisms.remove(organism);
    }

    public boolean isFieldOccupied(Point point) {
        for (Organism current : allOrganisms) {
            if (current.getLocation().equals(point))
                return true;
        }
        return false;
    }
}
