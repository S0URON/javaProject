package metier;

import java.util.Arrays;
import java.util.List;

public class Exposition{
    public String name;
    public static final List<String> types = Arrays.asList(
            "Solei",
            "Ombre",
            "Mi-ombre"
    );


    public Exposition(String type) throws TypeException {
        if (types.contains(type))
            this.name = type;
        else
            throw new TypeException("ce type d'exposition n'existe pas");
    }
}
