package pl.masuhr.pg.jpo.util;

import org.reflections.Reflections;
import pl.masuhr.pg.jpo.model.annotation.OrganismImpl;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * JPO-Zaliczenie
 * Created by karol on 18.10.2016.
 */
public class ClassFinder {
    private static final String[] SEARCH_PACKAGES = {
            "pl.masuhr.pg.jpo.model.animals",
            "pl.masuhr.pg.jpo.model.plants"
    };

    public static Class find(String name) {

        for (String searchPackage : SEARCH_PACKAGES) {
            try {
                return Class.forName(searchPackage + "." + name);
            } catch (ClassNotFoundException e) {
                //not in this package, try another
            }
        }
        return null;
    }

    public static List<Class<?>> getAllClasses() {
        Reflections reflections;
        List<Class<?>> allClasses = new ArrayList<>();

        for (String pkg : SEARCH_PACKAGES) {
            reflections = new Reflections(pkg);
            Set<Class<?>> classesInPackage = reflections.getTypesAnnotatedWith(OrganismImpl.class);
            allClasses.addAll(classesInPackage);
        }
        return allClasses;
    }
}
