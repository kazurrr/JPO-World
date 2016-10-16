package pl.masuhr.pg.jpo.model;

import pl.masuhr.pg.jpo.controller.World;
import pl.masuhr.pg.jpo.model.animals.Sheep;
import pl.masuhr.pg.jpo.model.animals.Wolf;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

import java.awt.Point;

/**
 * JPO-Zaliczenie
 * Created by karol on 11.10.2016.
 */

@RunWith(JUnitParamsRunner.class)
public class AnimalTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    World world;

    @Test
    @Parameters(method = "returnPairWeakAndStrongAnimal")
    public void strongerAnimalWins(Animal weak, Animal strong) {
        //Prepare
        doNothing().when(world).removeOrganism(any(Organism.class));
        strong.setMyWorld(world);
        weak.setMyWorld(world);

        //Call
        weak.attackBy(strong);
        strong.attackBy(weak);

        //Verify
        verify(world, times(2)).removeOrganism(weak);
    }

    private Object returnPairWeakAndStrongAnimal() {
        return new Object[]{
                new Object[]{new Sheep(world, new Point(0, 0)), new Wolf(world, new Point(0, 0))}
        };
    }

    @Test
    @Parameters(method = "returnAllAnimals")
    public void afterActionAnimalIsOlder(Animal sut) {
        //Prepare
        doReturn(false).when(world).isFieldOccupied(any(Point.class));
        sut.setMyWorld(world);

        //Call
        sut.action();

        //Verify
        assertThat(sut.age, is(2));
    }

    private Object returnAllAnimals() {
        return new Object[]{
                new Object[]{new Sheep(world, new Point(0, 0))},
                new Object[]{new Wolf(world, new Point(0, 0))}
        };
    }
}
