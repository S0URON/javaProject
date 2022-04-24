package metier;

import java.util.List;

public interface IGestionPlante {
    Plante ajouterPlante(Plante e);

    void supprimerPlante(int id);

    List<Plante> chercherPlanteParMC(String motClef);

    List<Plante> listeDesPlante();

    List<Plante> filtrerPlante(String Category, String fueillage,String typeSol, String exposition);

    List<Plante> chercherPlanteParPrix(int min, int max);

    void vendrePlante(String nom, int Qte) throws PlanteException;

    void acheterPlante(String nom, int Qte) throws PlanteException;

}
