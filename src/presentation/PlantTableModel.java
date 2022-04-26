package presentation;

import metier.Plante;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class PlantTableModel extends AbstractTableModel {
    private List<Plante> list = new ArrayList<>();
    private String titre[] ={"id","nom","categorie","typesol","feuillage", "exposition", "prix","Qte"};

    @Override
    public int getRowCount() {
        return this.list.size();
    }

    @Override
    public int getColumnCount() {
        return this.titre.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0:
                return this.list.get(rowIndex).getId();
            case 1:
                return this.list.get(rowIndex).getNom();
            case 2:
                return this.list.get(rowIndex).getCategory();
            case 3:
                return this.list.get(rowIndex).getTypeSol();
            case 4:
                return this.list.get(rowIndex).getFeuillage();
            case 5:
                return this.list.get(rowIndex).getExposition();
            case 6:
                return this.list.get(rowIndex).getPrix();
            case 7:
                return this.list.get(rowIndex).getQte();
        }
        return null;
    }

    @Override
    public String getColumnName(int c){
        return titre[c];
    }

    public void charger(List<Plante> updatedList) {
        this.list = updatedList;
        fireTableDataChanged();
    }
}
