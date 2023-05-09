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
import bo.Enchere;
import bo.Retraits;
import bo.Utilisateur;
import bo.Article;
import bo.Categorie;

/*
 * Implémentation des méthodes proposées par StagiaireDAO
 */
public class ArticleDAOJdbcImpl implements ArticleDAO {
	
	private UtilisateurDAO daoUtilisateur;

    private static final String SELECTALL = "select * from articles_vendus;";
    private static final String DELETE = "delete from articles_vendus where no_article = ?;";
    private static final String SELECTBYUSER= "select *, en.no_utilisateur as encherisseur from ARTICLES_VENDUS av\r\n"
    		+ "inner join ENCHERES en on en.no_article = av.no_article\r\n"
    		+ "where av.no_utilisateur = ?;";
    private static final String INSERT = "INSERT INTO articles_vendus VALUES( ?,?,?,?,?,?,?,?,?,?)";
    private static final String SELECTUTILBYARTICLEFROMENCHERE = "select u.* from encheres en inner join utilisateurs u on en.no_utilisateur = u.no_utilisateur where en.no_article = ?;";
    private static final String SELECTDETAILARTICLE = "select *, u.no_utilisateur as noUtilPrincipal, u.rue as utilRue, u.ville as utilVille, u.code_postal as utilCodePostal,\r\n"
    		+ "r.no_article as retraitNoArticle, r.rue as retraitRue, r.code_postal as retraitCodePostal, r.ville as retraitVille\r\n"
    		+ "from articles_vendus av\r\n"
    		+ "inner join categories c on c.no_categorie = av.no_categorie\r\n"
    		+ "left join retraits r on r.no_article = av.no_article\r\n"
    		+ "inner join utilisateurs u on u.no_utilisateur = av.no_utilisateur\r\n"
    		+ "left join encheres en on av.no_article = en.no_article\r\n"
    		+ "where av.no_article = ?;";

    
  public ArticleDAOJdbcImpl() {
		daoUtilisateur = DAOFactory.getUtilisateurDAO();
	} 

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
                Categorie categorie = new Categorie();
                String etatVente = rs.getString("etat_vente");
                String image = rs.getString("image");
                
                LocalDate date1 = dateDebutEnchere.toLocalDate();
                LocalDate date2 = dateFinEnchere.toLocalDate();
                Article article = new Article(nomArticle,description,date1,date2,prixInitial,prixVente,null,categorie,etatVente,image,null,null);
                
                listeArticles.add(article);}
            
            }catch (SQLException e) {
                e.printStackTrace();
            }
        return listeArticles;
    }


    @Override
    public Article selectById(int idArticle) {
    	
    	Article article = null;
        try (Connection cnx = ConnectionProvider.getConnection();) {

            PreparedStatement ps = cnx.prepareStatement(SELECTDETAILARTICLE);
            
            ps.setInt(1, idArticle);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
            	
            	//Récupération des infos de l'utilisateur de l'article
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
				boolean isActif = rs.getBoolean("actif");

            	Utilisateur utilisateur = new Utilisateur(noUtilisateur, pseudo, nom, prenom, email, telephone, rueUtil, codePostalUtil, villeUtil, motDePasse, credit, isAdministrateur, isActif);
            	
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
            	
            	//Enchere
                Date dateEnchere = rs.getDate("date_enchere");
                int montant = rs.getInt("montant_enchere");
                Utilisateur utilisateurEnchere = getUtilisateurByArticleFromEnchere(idArticle);                
                Enchere enchere = new Enchere(dateEnchere, montant, utilisateurEnchere, null);
            	
            	//Récupération des infos de l'article   
            	String nomArticle = rs.getString("nom_article");
				String description = rs.getString("description");
				LocalDate dateDebutEnchere = rs.getDate("date_debut_enchere").toLocalDate();
				LocalDate dateFinEnchere = rs.getDate("date_fin_enchere").toLocalDate();
				int prixInitial = rs.getInt("prix_initial");
				int prixVente = rs.getInt("prix_vente");
				String etatVente = rs.getString("etat_vente");
				String image = rs.getString("image");
				            	
                 article = new Article(idArticle, nomArticle, description, dateDebutEnchere, dateFinEnchere, prixInitial, prixVente, utilisateur, categorie ,etatVente, image, retrait, enchere);
                retrait.setarticle(article);  
                enchere.setArticle(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return article;
    }

    @Override
    public void delete(int id) {
    	/// TODO Auto-generated method stub
		 try (Connection cnx = ConnectionProvider.getConnection();) { 
			  PreparedStatement ps = cnx.prepareStatement(DELETE);
			  ps.setInt(1, id);		 
			  ps.executeUpdate();			 
	    }
	    catch(Exception e){ 
	      e.printStackTrace();
	    }
    }

    @Override
    public void update(Article Article) {
        // TODO Auto-generated method stub

    }


	@Override
	public Article insert(Article article) {
		/// TODO Auto-generated method stub
		 try (Connection cnx = ConnectionProvider.getConnection();) { 
			  PreparedStatement ps = cnx.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			  ps.setString(1,article.getNomArticle());
			  ps.setString(2,article.getDescription());
			  ps.setDate(3, Date.valueOf(article.getdateDebutEnchere()));
			  ps.setDate(4, Date.valueOf(article.getDateFinEnchere()));
			  ps.setInt(5, article.getPrixInitial());
			  ps.setInt(6, article.getPrixVente());
			  ps.setInt(7,article.getUtilisateur().getNoUtilisateur());
			  ps.setInt(8, article.getCategorie().getNoCategorie());
			  ps.setString(9,"CR");
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
		return article;
	}
    
    public Utilisateur getUtilisateurByArticleFromEnchere(int noArticle) {
    	Utilisateur util = null;
        try (Connection cnx = ConnectionProvider.getConnection();) {

            PreparedStatement ps = cnx.prepareStatement(SELECTUTILBYARTICLEFROMENCHERE);
            
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
				boolean isActif = rs.getBoolean("actif");

				util = new Utilisateur(noUtilisateur, pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, credit, isAdministrateur, isActif);
            }
        	
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return util;
    }


	@Override
	public List<Article> getArticleFromUtil(Utilisateur util) {
		List<Article> listeArticles = new ArrayList<Article>();
        try (Connection cnx = ConnectionProvider.getConnection();) {

            PreparedStatement ps = cnx.prepareStatement(SELECTBYUSER);
            
            ps.setInt(1, util.getNoUtilisateur());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
            	//Récupération info article
            	int noArticle = rs.getInt("no_article");
            	String nomArticle = rs.getString("nom_article");
            	String description =rs.getString("description");
            	Date dateDebutEnchere = rs.getDate("date_debut_enchere");
            	Date dateFinEnchere = rs.getDate("date_fin_enchere");
                int prixInitial = rs.getInt("prix_initial");
                int prixVente = rs.getInt("prix_vente");
                String etatVente = rs.getString("etat_vente");
                String image = rs.getString("image");                
                LocalDate date1 = dateDebutEnchere.toLocalDate();                
                LocalDate date2 = dateFinEnchere.toLocalDate();
                //Récupération info enchere
                int montant = rs.getInt("montant_enchere");     
                Utilisateur encherisseur = daoUtilisateur.selectById(rs.getInt("encherisseur"));
                Enchere enchere = new Enchere(null, montant, encherisseur, null);                
                //Création article
                Article article = new Article(noArticle, nomArticle,description,date1,date2,prixInitial,prixVente,util,null,etatVente,image, null, enchere);
                enchere.setArticle(article);
                
                listeArticles.add(article);}
            
            }catch (SQLException e) {
                e.printStackTrace();
            }
        return listeArticles;
	}
    
}

