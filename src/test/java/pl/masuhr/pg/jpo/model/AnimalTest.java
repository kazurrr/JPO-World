package pl.masuhr.pg.jpo.model;

import org.mockito.Mockito;
import pl.masuhr.pg.jpo.controller.World;
import pl.masuhr.pg.jpo.model.animals.*;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import pl.masuhr.pg.jpo.test.util.ParameterMethod;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
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

    @ParameterMethod
    private Object returnPairWeakAndStrongAnimal() {
        return new Object[]{
                new Object[]{new Sheep(world, new Point(0, 0)), new Wolf(world, new Point(0, 0))},
                new Object[]{new Hedgehog(world, new Point(0, 0)), new Wolf(world, new Point(0, 0))},
                new Object[]{new Unicorn(world, new Point(0, 0)), new Wolf(world, new Point(0, 0))},
                new Object[]{new Viper(world, new Point(0, 0)), new Wolf(world, new Point(0, 0))}
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

    @ParameterMethod
    private Object returnAllAnimals() {
        return new Object[]{
                new Object[]{new Sheep(world, new Point(0, 0))},
                new Object[]{new Wolf(world, new Point(0, 0))},
                new Object[]{new Hedgehog(world, new Point(0, 0))},
                new Object[]{new Unicorn(world, new Point(0, 0))},
                new Object[]{new Viper(world, new Point(0, 0))},
        };
    }

    @Test
    @Parameters(method = "returnAllAnimals")
    public void paralyzedAnimalDoNotPerformAction(Animal sut) {
        //Prepare
        doReturn(false).when(world).isFieldOccupied(any(Point.class));
        sut.setMyWorld(world);
        sut.setParalysis();
        sut = spy(sut);

        //Call
        sut.action();

        //Verify
        verify(sut, never()).setPosition(Mockito.any());
        verify(world, never()).getOrganismFromField(Mockito.any());
    }

    @Test
    @Parameters(method = "returnAllAnimals")
    public void paralyzedAnimalPerformActionAfterOneRound(Animal sut) {
        //Prepare
        doReturn(false).when(world).isFieldOccupied(any(Point.class));
        sut.setMyWorld(world);
        sut.setParalysis();
        sut = spy(sut);

        //Call
        sut.action();
        sut.action();

        //Verify
        verify(sut, times(2)).action();
        verify(sut, times(1)).setPosition(Mockito.any());
        verify(world, never()).getOrganismFromField(Mockito.any());
    }

    @Test
    public void multiplicationWhenAnimalIsOnFieldWithSameAnimalType() {
        //Prepare
        Wolf first = spy(new Wolf(world, new Point(0, 0)));
        Wolf second = spy(new Wolf(world, new Point(0, 1)));

        //Call
        first.collisionWith(second);

        //Verify
        verify(first).multiplicationWith(second);
    }
}
