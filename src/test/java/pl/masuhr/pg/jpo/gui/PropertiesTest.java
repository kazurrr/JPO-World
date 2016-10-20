package pl.masuhr.pg.jpo.gui;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;

/**
 * JPO-Zaliczenie
 * Created by karol on 16.10.2016.
 */
public class PropertiesTest {

    @Test
    public void worldIsGreaterThan3x3() {
        assertThat(Properties.WORLD_SIZE, is(greaterThanOrEqualTo(3)));
    }
}
