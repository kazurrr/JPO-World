package pl.masuhr.pg.jpo.model;

import pl.masuhr.pg.jpo.controller.World;

import java.awt.*;
import java.io.Serializable;

/**
 * JPO-Zaliczenie
 * Created by karol on 02.10.2016.
 */
public abstract class Organism implements Comparable<Organism>, Serializable{
    private static final long serialVersionUID = 1L;

    protected int strength;
    protected int initiative;
    protected Point position;
    protected int age;

    public void setMyWorld(World myWorld) {
        this.myWorld = myWorld;
    }

    protected World myWorld;
    protected boolean removed = false;

    public abstract void action();

    public abstract void collisionWith(Organism organism);

    private Organism() {

    }

    public Organism(World myWorld, Point position) {
        this.myWorld = myWorld;
        this.position = position;

        this.age = 1;
    }

    public void remove() {
        this.removed = true;
    }

    public boolean isMarkToRemove() {
        return this.removed;
    }

    public int getStrength() {
        return this.strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    protected void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public Point getPosition() {
        return position;
    }

    public String draw() {
        return this.getClass().getSimpleName();
    }

    public int compareTo(Organism opponent) {
        if (this.initiative == opponent.initiative)
            return ((Integer) opponent.age).compareTo(this.age);
        else
            return ((Integer) opponent.initiative).compareTo(this.initiative);
    }

    @Override
    public String toString() {
        String separator = "|";

        return "type:" + draw() + separator +
                "strength:" + strength + separator +
                "initiative:" + initiative + separator +
                "x:" + position.x + separator +
                "y:" + position.y + separator +
                "age:" + age;
    }
}
