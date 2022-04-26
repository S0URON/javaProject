package metier;

import dao.GererPlante;

import java.util.ArrayList;
import java.util.List;

public class GestionPlante implements IGestionPlante{

    private List<Plante> plantes;
    private GererPlante orm = new GererPlante();

    public GestionPlante(){
        this.plantes = this.orm.findPlante();
    }

    @Override
    public Plante ajouterPlante(Plante e) {
        if(e != null){
            this.orm.ajouterPlante(e.getNom(), Category.types.indexOf(e.getCategory()) + 1, Sol.types.indexOf(e.getTypeSol()) + 1,Feuillage.types.indexOf(e.getFeuillage()) + 1,Exposition.types.indexOf(e.getExposition()) + 1,e.getPrix(), e.getQte());
            this.plantes = this.orm.findPlante();
        }
        return e;
    }

    @Override
    public void supprimerPlante(int id) {
//        int index = searchIndexById(id);
//        if(index >= 0){
//            this.plantes.remove(index);
//        }
            this.orm.suppPlante(id);
            this.plantes = this.orm.findPlante();
    }

    @Override
    public List<Plante> chercherPlanteParMC(String motClef) {
        return this.plantes.stream().filter((Plante e) -> e.getNom().contains(motClef)).toList();
    }

    @Override
    public List<Plante> listeDesPlante() {
        return this.plantes;
    }

    @Override
    public List<Plante> filtrerPlante(String category, String fueillage, String typeSol, String exposition) {
        return this.plantes.stream().filter(plante -> (plante.getCategory().equals(category) || category.equals("all"))&&
                (plante.getFeuillage().equals(fueillage) || fueillage.equals("all"))&&
                (plante.getTypeSol().equals(typeSol) || typeSol.equals("all"))&&
                (plante.getExposition().equals(exposition) || exposition.equals("all"))).toList();
    }

    @Override
    public List<Plante> chercherPlanteParPrix(int min, int max) {
        return this.plantes.stream().filter(plante -> plante.getPrix() >= min && plante.getPrix() <= max).toList();
    }

    @Override
    public void vendrePlante(String nom, int qte) throws PlanteException{
        int index = searchIndexByName(nom);
        if(index == -1)
            throw new PlanteException("la plante n'existe pas");
        int availableQte = this.plantes.get(index).getQte();
        if(qte <= 0)
            throw new PlanteException("qte à vendre doit etre > 0");
        if(availableQte < qte)
            throw new PlanteException("Qte dans le stock insuffisante");
        this.orm.reductionQte(this.plantes.get(index).getId(), qte);
        this.plantes = this.orm.findPlante();
    }

    @Override
    public void acheterPlante(String nom, int qte) throws PlanteException {
        int index = searchIndexByName(nom);
        if(index == -1)
            throw new PlanteException("la plante n'existe pas");
        int availableQte = this.plantes.get(index).getQte();
        if(qte <= 0)
            throw new PlanteException("qte à acheter doit etre > 0");
        this.orm.augmentationQte(this.plantes.get(index).getId(), qte);
        this.plantes = this.orm.findPlante();
    }

    private int searchIndexById(int id) {
        for(int i = 0; i < this.plantes.size();i++){
            if(plantes.get(i).getId() == id)
                return i;
        }
        return -1;
    }

    private int searchIndexByName(String name){
        for(int i = 0; i < this.plantes.size();i++){
            if(plantes.get(i).getNom().equals(name))
                return i;
        }
        return -1;
    }

    public void setPlantes(List<Plante> p){
        this.plantes = p;
    }
}
