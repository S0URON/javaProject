package metier;

import java.util.Arrays;
import java.util.List;

public class Category{
    public String name;
    public static final List<String> types = Arrays.asList(
            "Arbres Forestiers",
            "Arbres Fruitiers",
            "Arbres Résineux",
            "Arbres Truffiers",
            "Grimpantes",
            "Vivaces",
            "Rosiers",
            "Grimpantes Fruitières",
            "Arbustes Décoratifs",
            "Arbustes Fleuris",
            "Arbustes Fruitiers"
    );


    public Category(String type) throws TypeException {
        if (types.contains(type))
            this.name = type;
        else
            throw new TypeException("cette categorie de plante n'existe pas");
    }
}
