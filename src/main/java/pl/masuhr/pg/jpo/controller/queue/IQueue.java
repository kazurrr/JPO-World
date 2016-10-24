package pl.masuhr.pg.jpo.controller.queue;

import pl.masuhr.pg.jpo.model.Organism;

import java.awt.*;

/**
 * JPO-Zaliczenie
 * Created by karol on 08.10.2016.
 */

public interface IQueue {
    void add(Organism organism);

    void mergeAddList();

    void remove(Organism organism);

    void toStart();

    boolean hasNext();

    Organism next();

    boolean isFieldOccupied(Point point);

    Organism getOrganismFromField(Point point);

    Iterable<Organism> iterator();
}
