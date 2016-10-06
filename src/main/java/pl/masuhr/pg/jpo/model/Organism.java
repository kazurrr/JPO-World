package pl.masuhr.pg.jpo.model;

import pl.masuhr.pg.jpo.controller.World;

import java.awt.*;

/**
 * Created by karol on 02.10.2016.
 */
public abstract class Organism implements Comparable<Organism> {
    private int strength;
    private int initiative;
    private int x;
    private int y;
    private int symbol;
    private int age;
    private World myWorld;

    public abstract void action();

    public abstract void collision();

    public Organism(World myWorld, Point point) {
        this.myWorld = myWorld;
        this.x = point.x;
        this.y = point.y;

        this.age = 1;
    }

    protected void setSymbol(int symbol) {
        this.symbol = symbol;
    }

    public String draw() {
        return this.getClass().getSimpleName();
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public Point getLocation() {
        return new Point(x, y);
    }

    public int compareTo(Organism opponent) {
        if (this.initiative == opponent.initiative)
            return ((Integer) this.age).compareTo(opponent.age);
        else
            return ((Integer) this.initiative).compareTo(opponent.initiative);
    }
}
