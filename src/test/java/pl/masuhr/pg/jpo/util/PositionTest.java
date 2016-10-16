package pl.masuhr.pg.jpo.util;

import junitparams.JUnitParamsRunner;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import pl.masuhr.pg.jpo.controller.World;
import pl.masuhr.pg.jpo.test.util.Repeat;
import pl.masuhr.pg.jpo.test.util.RepeatRule;

import java.awt.Point;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static pl.masuhr.pg.jpo.gui.Properties.WORLD_SIZE;

/**
 * JPO-Zaliczenie
 * Created by karol on 15.10.2016.
 */

@RunWith(JUnitParamsRunner.class)
public class PositionTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Rule
    public RepeatRule repeatRule = new RepeatRule();

    @Mock
    World world;

    private Position sut = new Position();

    @Test
    @Repeat(1000)
    public void randomPointsAreInWorld() {
        //Call
        Point position = sut.getRandom();

        //Verify
        assertThat(position.x, is(both(greaterThanOrEqualTo(0)).and(lessThan(WORLD_SIZE))));
        assertThat(position.y, is(both(greaterThanOrEqualTo(0)).and(lessThan(WORLD_SIZE))));
    }

    @Test
    @Repeat(30)
    public void returnValidNextPointWhenCurrentIsInCorner() {
        //Prepare
        Point myPosition = new Point(0, 0);

        //Call
        Point newPosition = sut.getNext(myPosition);

        //Verify
        assertThat(newPosition, anyOf(equalTo(new Point(1, 0)), equalTo(new Point(1, 1)), equalTo(new Point(0, 1))));
    }

    @Test
    @Repeat(30)
    public void returnNullWhenThereIsNoFreeField() {
        //Prepare
        Point currentPoint = sut.getRandom();
        doReturn(true).when(world).isFieldOccupied(any(Point.class));

        //Call
        Point newPosition = sut.getNextFree(world, currentPoint);

        //Verify
        assertThat(newPosition, is(nullValue()));
    }

    @Test
    @Repeat(30)
    public void returnFreeNextPointWhenAnimalInCorner() {
        //Prepare
        Point myPosition = new Point(0, 0);
        doReturn(false).when(world).isFieldOccupied(any(Point.class));

        //Call
        Point newPosition = sut.getNextFree(world, myPosition);

        //Verify
        assertThat(newPosition, anyOf(equalTo(new Point(1, 0)), equalTo(new Point(1, 1)), equalTo(new Point(0, 1))));
        assertThat(newPosition, not(myPosition));
    }

}
