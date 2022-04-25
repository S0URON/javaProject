package metier;

import java.util.Arrays;
import java.util.List;

public class Sol{
    public String type;
    public static final List<String> types = Arrays.asList(
            "Argileux",
            "Sec",
            "Caillouteux",
            "Sableux",
            "Calcaire",
            "Pauvre",
            "Humifère",
            "Limoneux"
    );


    public Sol(String type) throws TypeException {
        if (types.contains(type))
            this.type = type;
        else
            throw new TypeException("ce type de sol n'existe pas");
    }
}
