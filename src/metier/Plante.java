package metier;

public class Plante {
    private int id;
    private String nom;
    private Category category;
    private Sol typeSol;
    private Feuillage feuillage;
    private Exposition exposition;
    private float prix;
    private int qte;

    public Plante(String nom,String category, String typeSol, String feuillage, String exposition, float prix, int qte) throws TypeException{
        this.nom = nom;
        this.category = new Category(category);
        this.typeSol = new Sol(typeSol);
        this.feuillage = new Feuillage(feuillage);
        this.exposition = new Exposition(exposition);
        this.prix = prix;
        this.qte = qte;
    }

    public Plante(int id, String nom,String category, String typeSol, String feuillage, String exposition, float prix, int qte) throws TypeException{
        this.id = id;
        this.nom = nom;
        this.category = new Category(category);
        this.typeSol = new Sol(typeSol);
        this.feuillage = new Feuillage(feuillage);
        this.exposition = new Exposition(exposition);
        this.prix = prix;
        this.qte = qte;
    }

	public String getNom() {
        return this.nom;
    }

    public String getTypeSol() {
        return this.typeSol.type;
    }

    public String getFeuillage() {
        return this.feuillage.name;
    }

    public String getExposition() {
        return this.exposition.name;
    }

    public String getCategory() {
        return this.category.name;
    }

    public int getId() {
        return this.id;
    }

    public float getPrix() {
        return prix;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }
}
