package presentation;

public interface AddPlantInterface {
    void cancel();
    
    void add(String nom,String category, String typeSol, String feuillage, String exposition, float prix, int qte);
}
