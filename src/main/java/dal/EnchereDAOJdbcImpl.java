package dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bo.Enchere;
import bo.Retraits;
import bo.Utilisateur;
import bo.Article;
import bo.Categorie;

/*
 * Implémentation des méthodes proposées par StagiaireDAO
 */
public class EnchereDAOJdbcImpl implements EnchereDAO {

    private static final String SELECTALL = "select * from encheres;";
    private static final String SELECTUTILBYARTICLE = "select u.* from articles_vendus av inner join utilisateurs u on av.no_utilisateur = u.no_utilisateur where av.no_article = ?;";
    private static final String SELECTDETAILENCHERE = "select *, u.no_utilisateur as noUtilPrincipal, u.rue as utilRue, u.ville as utilVille, u.code_postal as utilCodePostal,\r\n"
    		+ "r.no_article as retraitNoArticle, r.rue as retraitRue, r.code_postal as retraitCodePostal, r.ville as retraitVille\r\n"
    		+ "from encheres en \r\n"
    		+ "inner join utilisateurs u on u.no_utilisateur = en.no_utilisateur\r\n"
    		+ "inner join articles_vendus av on av.no_article = en.no_article\r\n"
    		+ "inner join categories c on c.no_categorie = av.no_categorie\r\n"
    		+ "left join retraits r on r.no_article = av.no_article\r\n"
    		+ "where en.no_article = ?;";

    @Override
    public List<Enchere> selectAll() {
        List<Enchere> listeEncheres = new ArrayList<Enchere>();
        try (Connection cnx = ConnectionProvider.getConnection();) {

            PreparedStatement ps = cnx.prepareStatement(SELECTALL);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Date dateEnchere = rs.getDate("date_enchere");
                int montant = rs.getInt("montant_enchere");
                Utilisateur utilisateur = new Utilisateur();
                Article article = new Article();

                Enchere enchere = new Enchere(dateEnchere, montant, utilisateur, article);

                listeEncheres.add(enchere);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeEncheres;
    }

    @Override
    public Enchere selectById(int idArticle) {
    	
    	Enchere enchere = null;
        try (Connection cnx = ConnectionProvider.getConnection();) {

            PreparedStatement ps = cnx.prepareStatement(SELECTDETAILENCHERE);
            
            ps.setInt(1, idArticle);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
            	
            	//Récupération des infos de l'utilisateur de l'enchère
            	int noUtilisateur = rs.getInt("noUtilPrincipal");
            	String pseudo = rs.getString("pseudo");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String email = rs.getString("email");
				String telephone = rs.getString("telephone");
				String rueUtil = rs.getString("utilRue");
				String codePostalUtil = rs.getString("utilCodePostal");
				String villeUtil = rs.getString("utilVille");
				String motDePasse = rs.getString("mot_de_passe");
				int credit = rs.getInt("credit");
				boolean isAdministrateur = rs.getBoolean("administrateur");

            	Utilisateur utilisateurEnchere = new Utilisateur(noUtilisateur, pseudo, nom, prenom, email, telephone, rueUtil, codePostalUtil, villeUtil, motDePasse, credit, isAdministrateur);
            	
            	//Récupération des infos de catégorie
            	int noCategorie = rs.getInt("no_categorie");
            	String libelleCateg = rs.getString("libelle");
            	
            	Categorie categorie = new Categorie(noCategorie, libelleCateg);
            	
            	//Récupération des données pour le retrait
            	String retraitNoArticle = rs.getString("retraitNoArticle");
            	Retraits retrait = new Retraits();
            	if(retraitNoArticle != null) {
                	String rueRetrait = rs.getString("retraitRue");
                	String codePostalRetrait = rs.getString("retraitCodePostal");
                	String villeRetrait = rs.getString("retraitVille");            	
                	retrait = new Retraits(null, rueRetrait, codePostalRetrait, villeRetrait);
            	}  
            	
            	//Récupération des infos de l'article            	
            	int noArticle = rs.getInt("no_article");
            	String nomArticle = rs.getString("nom_article");
				String description = rs.getString("description");
				Date dateDebutEnchere = rs.getDate("date_debut_enchere");
				Date dateFinEnchere = rs.getDate("date_fin_enchere");
				int prixInitial = rs.getInt("prix_initial");
				int prixVente = rs.getInt("prix_vente");
				String etatVente = rs.getString("etat_vente");
				String image = rs.getString("image");
				
				//getUtil article				
				Utilisateur utilArticle = getUtilisateurByArticle(noArticle);
            	
                Article article = new Article(noArticle, nomArticle, description, dateDebutEnchere, dateFinEnchere, prixInitial, prixVente, utilArticle, categorie ,etatVente, image, retrait);
            	
                retrait.setarticle(article);            	          	
            	
            	//Enchere
                Date dateEnchere = rs.getDate("date_enchere");
                int montant = rs.getInt("montant_enchere");
                
                enchere = new Enchere(dateEnchere, montant, utilisateurEnchere, article);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enchere;
    }
    
    public Utilisateur getUtilisateurByArticle(int noArticle) {
    	Utilisateur util = null;
        try (Connection cnx = ConnectionProvider.getConnection();) {

            PreparedStatement ps = cnx.prepareStatement(SELECTUTILBYARTICLE);
            
            ps.setInt(1, noArticle);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
            	//Récupération des infos de l'utilisateur de l'enchère
            	int noUtilisateur = rs.getInt("no_utilisateur");
            	String pseudo = rs.getString("pseudo");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String email = rs.getString("email");
				String telephone = rs.getString("telephone");
				String rue = rs.getString("rue");
				String codePostal = rs.getString("code_postal");
				String ville = rs.getString("ville");
				String motDePasse = rs.getString("mot_de_passe");
				int credit = rs.getInt("credit");
				boolean isAdministrateur = rs.getBoolean("administrateur");

				util = new Utilisateur(noUtilisateur, pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, credit, isAdministrateur);
            }
        	
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return util;
    }

    @Override
    public void delete(int id) {
        // TODO Auto-generated method stub

    }

    @Override
    public void update(Enchere enchere) {
        // TODO Auto-generated method stub

    }

}
