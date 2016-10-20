package pl.masuhr.pg.jpo.io;

import pl.masuhr.pg.jpo.io.model.WorldParsingException;

import java.util.Hashtable;
import java.util.Map;

/**
 * JPO-Zaliczenie
 * Created by karol on 18.10.2016.
 *
 * Object that allows to read key-value attributes from given String
 * type:Wolf|strength:9|initiative:5|x:4|y:0|age:1
 */
public class OrganismParser {
    private Map<String, String> organismAttr = new Hashtable<>();

    public OrganismParser(String args) {
        String[] allAttributes= args.split("\\|");

        for(String attribute : allAttributes) {
            String[] temp = attribute.split(":");
            organismAttr.put(temp[0], temp[1]);
        }
    }

    public String getString(String key) throws WorldParsingException {
        if(organismAttr.containsKey(key)) {
            return organismAttr.get(key);
        }
        else {
            throw new WorldParsingException("Cannot read organism attribute");
        }
    }

    public int getInt(String key) throws WorldParsingException {
        if(organismAttr.containsKey(key)) {
            return Integer.parseInt(organismAttr.get(key));
        }
        else {
            throw new WorldParsingException("Cannot read organism attribute");
        }
    }

}
