package pl.masuhr.pg.jpo.util;

/**
 * JPO-Zaliczenie
 * Created by karol on 18.10.2016.
 */
public class ClassFinder {
    private static final String[] SEARCH_PACKAGES = {
            "pl.masuhr.pg.jpo.model.animals",
            "pl.masuhr.pg.jpo.model.plants"
    };

    public static Class<?> find(String name) {

        for (String searchPackage : SEARCH_PACKAGES) {
            try {
                return Class.forName(searchPackage + "." + name);
            } catch (ClassNotFoundException e) {
                //not in this package, try another
            }
        }
        return null;
    }
}
