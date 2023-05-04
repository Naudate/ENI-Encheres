package dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import bo.Utilisateur;
import bo.Article;
import bo.Categorie;

/*
 * Implémentation des méthodes proposées par StagiaireDAO
 */
public class ArticleDAOJdbcImpl implements ArticleDAO {

    private static final String SELECTALL = "select * from articles_vendus;";
    private static final String INSERT = "INSERT INTO articles_vendus VALUES( ?,?,?,?,?,?,?,?,?,?)";

    @Override
    public List<Article> selectAll() {
        List<Article> listeArticles= new ArrayList<Article>();
        try (Connection cnx = ConnectionProvider.getConnection();) {

            PreparedStatement ps = cnx.prepareStatement(SELECTALL);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
            	String nomArticle = rs.getString("nom_article");
            	String description =rs.getString("description");
            	Date dateDebutEnchere = rs.getDate("date_debut_enchere");
            	Date dateFinEnchere = rs.getDate("date_fin_enchere");
                int prixInitial = rs.getInt("prix_initial");
                int prixVente = rs.getInt("prix_vente");
                Utilisateur utilisateur = new Utilisateur();
                Categorie categorie = new Categorie();
                String etatVente = rs.getString("etat_vente");
                String image = rs.getString("image");
                
                LocalDate date1 = dateDebutEnchere.toLocalDate();
                
                LocalDate date2 = dateFinEnchere.toLocalDate();
                Article article = new Article(nomArticle,description,date1,date2,prixInitial,prixVente,utilisateur,categorie,etatVente,image);

                listeArticles.add(article);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeArticles;
    }

    @Override
    public Article selectById(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(int id) {
        // TODO Auto-generated method stub

    }

    @Override
    public void update(Article Article) {
        // TODO Auto-generated method stub

    }


	@Override
	public Article insert(Article article) {
		/// TODO Auto-generated method stub
		List<Article> listeArticles= new ArrayList<Article>();
		 try (Connection cnx = ConnectionProvider.getConnection();) { 
			  PreparedStatement ps = cnx.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			  ps.setString(1,article.getNomArticle());
			  ps.setString(2,article.getDescription());
			  ps.setDate(3, Date.valueOf(article.getdateDebutEnchere()));
			  ps.setDate(4, Date.valueOf(article.getDateFinEnchere()));
			  ps.setInt(5, article.getPrixInitial());
			  ps.setInt(6, article.getPrixVente());
			  ps.setInt(7,/*article.getNoUtilisateur().getNoUtilisateur()*/1);
			  ps.setInt(8, /*article.getNoCategorie().getNoCategorie()*/1);
			  ps.setString(9,article.getEtatVente());
			  ps.setString(10, article.getImage());
			  
			  ps.executeUpdate();
			  ResultSet keys = ps.getGeneratedKeys();
			  if(keys.next()) {
				  int idArticle = keys.getInt(1);
				  article.setNoArticle(idArticle);
			  }
	    }
	    catch(Exception e){ 
	      e.printStackTrace();
	    }
		return null;
	}

}