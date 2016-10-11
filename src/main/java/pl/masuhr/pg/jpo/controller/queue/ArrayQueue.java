package pl.masuhr.pg.jpo.controller.queue;

import pl.masuhr.pg.jpo.model.Organism;

import java.awt.*;

/**
 * Created by karol on 08.10.2016.
 */
public class ArrayQueue implements IQueue {
    private Organism[][] allOrganisms;
    private int current;

//    ArrayQueue(int size) {
//        allOrganisms = new Organism[size][size];
//    }

    @Override
    public void add(Organism organism) {
        Point position = organism.getPosition();
        allOrganisms[position.x][position.y] = organism;
    }

    @Override
    public void remove(Organism organism) {
        Point position = organism.getPosition();
        allOrganisms[position.x][position.y] = null;
    }

    @Override
    public void toStart() {

    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Organism next() {
        return null;
    }

    @Override
    public boolean isFieldOccupied(Point point) {
        return  !(allOrganisms[point.x][point.y] == null);
    }

    @Override
    public Organism getOrganismFromField(Point point) {
        return allOrganisms[point.x][point.y];
    }

    @Override
    public Iterable<Organism> iterator() {
        return null;
    }
}
