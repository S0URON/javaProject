package metier;

import java.util.Arrays;
import java.util.List;

public class Feuillage{
    public String name;
    private static final List<String> types = Arrays.asList(
            "Caduc",
            "Semi-persistant",
            "Persistant",
            "Marcescent"
    );


    public Feuillage(String type) throws TypeException {
        if (types.contains(type))
            this.name = type;
        else
            throw new TypeException("ce type de feuillage n'existe pas");
    }
}
