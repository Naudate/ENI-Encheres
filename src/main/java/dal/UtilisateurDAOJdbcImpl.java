package dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bo.Enchere;
import bo.Utilisateur;
import bo.Article;

/*
 * Implémentation des méthodes proposées par StagiaireDAO
 */
public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {
	
	private static final String SELECTUSER = "select * from utilisateurs where (pseudo = ? and mot_de_passe = ?) OR (email = ? and mot_de_passe = ?);";
	
	@Override
	public Utilisateur verifCompte(String pseudo, String password) {
		Utilisateur util = null;
		try (Connection cnx = ConnectionProvider.getConnection();) {
			PreparedStatement ps = cnx.prepareStatement(SELECTUSER);
			
			ps.setString(1, pseudo);			
			ps.setString(2, password);
			ps.setString(3, pseudo);
			ps.setString(4, password);
			
			ResultSet rs = ps.executeQuery();	
			
			while(rs.next())
			{
				int noUtilisateur = rs.getInt("no_utilisateur") ;
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String email = rs.getString("email");
				String telephone = rs.getString("telephone");
				String rue = rs.getString("rue");
				String code_postal = rs.getString("code_postal");
				String ville = rs.getString("ville");
				int credit = rs.getInt("credit");
				boolean administrateur = rs.getBoolean("administrateur");

				util = new Utilisateur(noUtilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, password, credit, administrateur);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return util;
	}

	@Override
	public void insert(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		
	}

    @Override
    public Utilisateur selectById(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(int id) {
        // TODO Auto-generated method stub

    }

    @Override
    public void update(Utilisateur utilisateur) {
        // TODO Auto-generated method stub

    }

}
