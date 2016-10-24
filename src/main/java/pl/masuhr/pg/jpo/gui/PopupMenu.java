package pl.masuhr.pg.jpo.gui;

import pl.masuhr.pg.jpo.controller.World;
import pl.masuhr.pg.jpo.model.Organism;
import pl.masuhr.pg.jpo.service.IconService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static pl.masuhr.pg.jpo.util.ClassFinder.getAllAnimals;
import static pl.masuhr.pg.jpo.util.ClassFinder.getAllPlants;

/**
 * JPO-Zaliczenie
 * Created by karol on 24.10.2016.
 */
public class PopupMenu extends JPopupMenu {
    private IconService icons = IconService.getInstance();
    private World world;
    private Point position;
    private JButton button;

    PopupMenu(World world) {
        this.world = world;

        addAnimals();
        addSeparator();
        addPlants();
    }

    public void setPosition(Point point) {
        this.position = point;
    }

    public void setButtonReference(JButton button) {
        this.button = button;
    }

    public void addAnimals() {
        for (Class animal : getAllAnimals()) {
            add(getAbstractActionForClass(animal));
        }
    }

    public void addPlants() {
        for (Class plant : getAllPlants()) {
            add(getAbstractActionForClass(plant));
        }
    }

    private AbstractAction getAbstractActionForClass(Class organism) {
        return new AbstractAction(organism.getSimpleName()) {
            public void actionPerformed(ActionEvent event) {
                try {
                    Organism newOrganism = (Organism) organism.newInstance();
                    newOrganism.setMyWorld(world);
                    newOrganism.setPosition(position);
                    world.addNewOrganism(newOrganism);
                    button.setIcon(icons.getImage(organism.getSimpleName()));
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        };
    }
}

