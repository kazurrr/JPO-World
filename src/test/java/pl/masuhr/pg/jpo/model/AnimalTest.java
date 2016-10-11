package pl.masuhr.pg.jpo.model;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import pl.masuhr.pg.jpo.controller.World;
import pl.masuhr.pg.jpo.model.animals.Sheep;
import pl.masuhr.pg.jpo.model.animals.Wolf;

import java.awt.*;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;


/**
 * JPO-Zaliczenie
 * Created by karol on 11.10.2016.
 */

@RunWith(JUnitParamsRunner.class)
public class AnimalTest {

    @Mock
    World world;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    @Parameters(method = "parametersForVerifyThatStrongerAnimalWinsDuringCollision")
    public void verifyThatStrongerAnimalWinsDuringCollision(Animal weak, Animal strong) {

        //Prepare
        doNothing().when(world).removeOrganism(any(Organism.class));
        weak.setMyWorld(world);
        strong.setMyWorld(world);

        //Call
        weak.attackBy(strong);

        //Verify
        verify(world).removeOrganism(weak);
    }

    private Object parametersForVerifyThatStrongerAnimalWinsDuringCollision() {
        return new Object[]{
                new Object[]{new Sheep(world, new Point(0, 0)), new Wolf(world, new Point(0, 0))}
        };
    }
}
