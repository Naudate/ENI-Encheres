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
public class EnchereDAOJdbcImpl implements EnchereDAO {
	
	private static final String SELECTENCHERE = "select * from encheres en where en.no_article = ?;";
	private static final String INSERT = "insert into encheres(no_utilisateur, no_article, date_enchere, montant_enchere) values(?, ?, ?, ?);";
	private static final String UPDATE = "update encheres set no_utilisateur = ?, date_enchere = ?, montant_enchere = ? where no_article = ?";

	@Override
	public Enchere getEnchereByArticle(Article article) {
		Enchere enchere = null;
        try (Connection cnx = ConnectionProvider.getConnection();) {

            PreparedStatement ps = cnx.prepareStatement(SELECTENCHERE);
            
            ps.setInt(1, article.getNoArticle());

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {    
            	//Enchere
                Date dateEnchere = rs.getDate("date_enchere");
                int montant = rs.getInt("montant_enchere");               
                enchere = new Enchere(dateEnchere, montant, null, article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enchere;
	}

	@Override
	public void createEnchere(Article article, Utilisateur util, int montantEnchere) {
		try (Connection cnx = ConnectionProvider.getConnection();) {
			
			 PreparedStatement ps = cnx.prepareStatement(INSERT);
			 
			 Date dateEnchere = new Date(System.currentTimeMillis());
	            
	         ps.setInt(1, util.getNoUtilisateur());
	         ps.setInt(2, article.getNoArticle());
	         ps.setDate(3, dateEnchere);
	         ps.setInt(4, montantEnchere);
	         
	         ResultSet rs = ps.executeQuery();

		}catch(SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public void updateEnchere(Article article, Utilisateur util, int montantEnchere) {
		try (Connection cnx = ConnectionProvider.getConnection();) {
			
			 PreparedStatement ps = cnx.prepareStatement(UPDATE);
			 
			 Date dateEnchere = new Date(System.currentTimeMillis());
	            
	         ps.setInt(1, util.getNoUtilisateur());
	         ps.setDate(2, dateEnchere);
	         ps.setInt(3, montantEnchere);
	         ps.setInt(4, article.getNoArticle());
	         
	         ResultSet rs = ps.executeQuery();

		}catch(SQLException e) {
           e.printStackTrace();
       }
	}
	
}
