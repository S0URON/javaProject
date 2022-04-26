package dao;

import metier.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class GererPlante {
	public void ajouterPlante(String nom, int category_id, int sol_id, int feuillage_id, int exposition_id, float prix, int qte) {
		Connection c = SingletonClass.getConnection();
		try {
			PreparedStatement pr = c.prepareStatement("insert into plante (nom,category_id,sol_id,feuillage_id,exposition_id,prix,qte) values (?,?,?,?,?,?,?)");
			pr.setString(1, nom);
			pr.setInt(2, category_id);
			pr.setInt(3, sol_id);
			pr.setInt(4, feuillage_id);
			pr.setInt(5, exposition_id);
			pr.setFloat(6, prix);
			pr.setInt(7, qte);
			pr.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void suppPlante(int id) {
		Connection c = SingletonClass.getConnection();
		try {
			PreparedStatement pr = c.prepareStatement("DELETE FROM plante WHERE id=?");
			pr.setInt(1, id);
			pr.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List chercherPlante(String motClef) {
		Connection c = SingletonClass.getConnection();
		 List<Plante> liste = new ArrayList<>();
		try {
//			plante.id, plante.nom, category.id AS 'category_id', category.type AS 'category_type', sol.id AS 'sol_id', sol.type AS 'sol_type', exposition.id AS 'exposition_id', exposition.type AS 'exposition_type', feuillage.id AS 'feuillage_id', feuillage.type AS 'feuillage_type'
			PreparedStatement s = c.prepareStatement("SELECT plante.id, plante.nom, plante.prix, plante.qte, category.type, sol.type, feuillage.type, exposition.type  FROM plante INNER JOIN category ON plante.category_id = category.id INNER JOIN sol ON plante.sol_id = sol.id INNER JOIN exposition ON plante.exposition_id = exposition.id INNER JOIN feuillage ON plante.feuillage_id = feuillage.id WHERE nom LIKE CONCAT('%',?,'%')");
			s.setString(1, motClef);
			ResultSet res = s.executeQuery();
			while(res.next()) {
				List<String> element = new ArrayList<>();
				for (int i = 1; i<9; i++) {
					element.add(res.getString(i));
				}
//	output		[3, plante_test3, 30.0, 250, Arbustes Fleuris, Pauvre, Persistant, Mi-ombre]
				System.out.println(element.toString());
				Plante newPlant = new Plante(element.get(1), element.get(4), element.get(5), element.get(6), element.get(7), Float.parseFloat(element.get(2)), Integer.parseInt(element.get(3)));
				liste.add(newPlant);
			}
		} catch (SQLException | NumberFormatException | TypeException e) {
			e.printStackTrace();
		}
		return liste;
	}
	
	public List findPlante() {
		Connection c = SingletonClass.getConnection();
		List<Plante> listePlantes = new ArrayList<>();
		try {
			PreparedStatement ps = c.prepareStatement("SELECT plante.id, plante.nom, plante.prix, plante.qte, category.type, sol.type, feuillage.type, exposition.type  FROM plante INNER JOIN category ON plante.category_id = category.id INNER JOIN sol ON plante.sol_id = sol.id INNER JOIN exposition ON plante.exposition_id = exposition.id INNER JOIN feuillage ON plante.feuillage_id = feuillage.id");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				List<String> element = new ArrayList<>();
				for (int i = 1; i<9; i++) {
					element.add(rs.getString(i));
				}
				System.out.println(element.toString());
				Plante newPlant = new Plante(element.get(1), element.get(4), element.get(5), element.get(6), element.get(7), Float.parseFloat(element.get(2)), Integer.parseInt(element.get(3)));
				listePlantes.add(newPlant);
			}
			return listePlantes;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	public void reductionQte(int id, int qte) {
		Connection cx = SingletonClass.getConnection();
		try {
			PreparedStatement ps = cx.prepareStatement("UPDATE plante SET qte=qte-? WHERE id=?");
			ps.setInt(1, qte);
			ps.setInt(2,id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void augmentationQte(int id, int qte) {
		Connection cx = SingletonClass.getConnection();
		try {
			PreparedStatement ps = cx.prepareStatement("UPDATE plante SET qte=qte+? WHERE id=?");
			ps.setInt(1, qte);
			ps.setInt(2,id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List searchByIndex(int id) {
		Connection cx = SingletonClass.getConnection();
		List<String> element = new ArrayList<>();
		try {
			PreparedStatement ps = cx.prepareStatement("SELECT * FROM plante WHERE id=?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			rs.first();
			for(int i = 1; i<9; i++) {
				element.add(rs.getString(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return element;
	}
	
	
	public int searchIdByName(String nom) {
		Connection cx = SingletonClass.getConnection();
		try {
			PreparedStatement ps = cx.prepareStatement("SELECT * FROM plante WHERE nom LIKE ?");
			ps.setString(1, nom);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	
}