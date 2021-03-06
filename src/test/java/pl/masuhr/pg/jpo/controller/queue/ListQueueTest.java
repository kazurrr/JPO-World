package pl.masuhr.pg.jpo.controller.queue;

import junitparams.JUnitParamsRunner;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import pl.masuhr.pg.jpo.controller.World;
import pl.masuhr.pg.jpo.model.animals.Sheep;
import pl.masuhr.pg.jpo.model.animals.Wolf;

import java.awt.*;

/**
 * JPO-Zaliczenie
 * Created by karol on 16.10.2016.
 */
@RunWith(JUnitParamsRunner.class)
public class ListQueueTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private World world;

    private IQueue sut = new ListQueue();

    @Test
    public void duringQueueIterationEachOrganismIsCalledOnce() {
        //Prepare
        doReturn(false).when(world).isFieldOccupied(any(Point.class));
        Wolf first = spy(new Wolf(world, new Point(0, 0)));
        Wolf second = spy(new Wolf(world, new Point(0, 0)));
        Sheep third = spy(new Sheep(world, new Point(0, 0)));

        sut.add(first);
        sut.add(second);
        sut.add(third);
        sut.toStart();

        //Call
        sut.next().action();
        //Verify
        verify(first, times(1)).action();
        verify(second, times(0)).action();
        verify(third, times(0)).action();

        //Call
        sut.next().action();
        //Verify
        verify(first, times(1)).action();
        verify(second, times(1)).action();
        verify(third, times(0)).action();

        //Call
        sut.next().action();
        //Verify
        verify(first, times(1)).action();
        verify(second, times(1)).action();
        verify(third, times(1)).action();
    }

    @Test
    public void afterNewOrganismIsAddedItWontPerformAction() {
        //Prepare
        doReturn(false).when(world).isFieldOccupied(any(Point.class));
        Wolf first = spy(new Wolf(world, new Point(0, 0)));
        Wolf second = spy(new Wolf(world, new Point(0, 1)));
        Wolf third = spy(new Wolf(world, new Point(0, 1)));

        sut.add(first);
        sut.add(second);
        sut.toStart();

        //Call
        sut.next().action();
        //Verify
        verify(first, times(1)).action();
        verify(second, times(0)).action();

        //Call
        sut.next().action();
        //Verify
        verify(first, times(1)).action();
        verify(second, times(1)).action();

        //Call
        sut.add(third);

        //Verify
        assertThat(sut.hasNext(), is(false));
        verify(first, times(1)).action();
        verify(second, times(1)).action();
        verify(third, times(0)).action();
    }
}
