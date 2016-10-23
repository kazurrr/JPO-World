package pl.masuhr.pg.jpo.util;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import pl.masuhr.pg.jpo.model.animals.*;
import pl.masuhr.pg.jpo.model.plants.Belladonna;
import pl.masuhr.pg.jpo.model.plants.Grass;
import pl.masuhr.pg.jpo.model.plants.Guarana;
import pl.masuhr.pg.jpo.test.util.ParameterMethod;

import java.awt.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * JPO-Zaliczenie
 * Created by karol on 20.10.2016.
 */
@RunWith(JUnitParamsRunner.class)
public class ClassFinderTest {
    @Test
    public void getWolfClass() {
        //Call
        Class result = ClassFinder.find("Wolf");

        //Verify
        assertThat(result, is(equalTo(Wolf.class)));
    }

    @Test
    @Parameters(method = "getAllOrganisms")
    public void findAllOrganismsClasses(Class currentAnimal) {
        //Prepare
        String className = currentAnimal.getSimpleName();

        //Call
        Class result = ClassFinder.find(className);

        //Verify
        assertThat(result, is(equalTo(currentAnimal)));
    }

    @ParameterMethod
    public Object getAllOrganisms() {
        return new Object[]{
                new Object[]{Wolf.class},
                new Object[]{Sheep.class},
                new Object[]{Unicorn.class},
                new Object[]{Viper.class},
                new Object[]{Hedgehog.class},

                new Object[]{Belladonna.class},
                new Object[]{Grass.class},
                new Object[]{Guarana.class},
        };
    }
}