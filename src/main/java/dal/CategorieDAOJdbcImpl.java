package dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bo.Article;
import bo.Categorie;
import bo.Utilisateur;

public class CategorieDAOJdbcImpl implements CategorieDAO {
	private static final String SELECTALL = "select * from categories;";

	@Override
	public List<Categorie> selectAll() {
		List<Categorie> listeCategorie= new ArrayList<Categorie>();
        try (Connection cnx = ConnectionProvider.getConnection();) {

            PreparedStatement ps = cnx.prepareStatement(SELECTALL);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
            	int noCategorie = rs.getInt("no_categorie");
            	String libelle =rs.getString("libelle");
            	
            	Categorie categorie = new Categorie(noCategorie,libelle);
                
            	listeCategorie.add(categorie);}
            
            }catch (SQLException e) {
                e.printStackTrace();
            }
        return listeCategorie;
	}
}
