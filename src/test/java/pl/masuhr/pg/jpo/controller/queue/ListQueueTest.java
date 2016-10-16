package pl.masuhr.pg.jpo.controller.queue;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import pl.masuhr.pg.jpo.controller.World;
import pl.masuhr.pg.jpo.model.Animal;
import pl.masuhr.pg.jpo.model.animals.Sheep;
import pl.masuhr.pg.jpo.model.animals.Wolf;
import pl.masuhr.pg.jpo.util.Position;

import java.awt.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

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
        Wolf second = spy(new Wolf(world, new Point(0, 1)));
        Sheep third = spy(new Sheep(world, new Point(1, 1)));

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
}
