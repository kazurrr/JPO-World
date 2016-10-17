package pl.masuhr.pg.jpo.controller.queue;

import pl.masuhr.pg.jpo.model.Organism;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * JPO-Zaliczenie
 * Created by karol on 06.10.2016.
 */
public class ListQueue implements IQueue {
    private List<Organism> allOrganisms = new ArrayList<>();
    private List<Organism> organismsToAdd = new ArrayList<>();
    private int currentIndex = -1;

    @Override
    public void add(Organism organism) {
        organismsToAdd.add(organism);
    }

    @Override
    public void mergeAddList() {
        allOrganisms.addAll(organismsToAdd);
        organismsToAdd = new ArrayList<>();
        Collections.sort(allOrganisms);
    }

    @Override
    public void remove(Organism organism) {
        allOrganisms.remove(organism);
        Collections.sort(allOrganisms);
    }

    @Override
    public void toStart() {
        mergeAddList();
        currentIndex = -1;
    }

    @Override
    public boolean hasNext() {
        return allOrganisms.size() - (currentIndex + 1) > 0;
    }

    @Override
    public Organism next() {
        currentIndex++;
        return allOrganisms.get(currentIndex);
    }

    @Override
    public boolean isFieldOccupied(Point point) {
        for (Organism current : allOrganisms) {
            if (current.getPosition().equals(point))
                return true;
        }
        return false;
    }

    @Override
    public Organism getOrganismFromField(Point point) {
        for (Organism current : allOrganisms) {
            if (current.getPosition().equals(point))
                return current;
        }
        return null;
    }

    @Override
    public Iterable<Organism> iterator() {
        return allOrganisms;
    }
}
