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
import bo.Enchere;
import bo.Utilisateur;

public class CategorieDAOJdbcImpl implements CategorieDAO {
	private static final String SELECTALL = "select * from categories;";
	private static final String CHECKVALUEEXIST = "select * from categories where libelle = ?;";
	private static final String INSERT = "insert into categories(libelle) values(?);";
	private static final String DELETE = "delete from categories where no_categorie = ?;";
	private static final String SELECTARTICLEBUCATEGORIE = "select *, en.no_utilisateur as encherisseur from ARTICLES_VENDUS av\r\n"
			+ "inner join CATEGORIES cat on cat.no_categorie = av.no_categorie\r\n"
			+ "left join ENCHERES en on av.no_article = en.no_article\r\n"
			+ "where cat.no_categorie = ?;";
	
	private UtilisateurDAO utilisateurDao;
	
	public CategorieDAOJdbcImpl() {
		utilisateurDao = DAOFactory.getUtilisateurDAO();
	}

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

	@Override
	public Categorie insert(Categorie categorie) {
        try (Connection cnx = ConnectionProvider.getConnection();) {

            PreparedStatement ps = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            
            ps.setString(1, categorie.getLibelle());

            ps.executeUpdate();
            
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				categorie.setNoCategorie(rs.getInt(1));
			}
			rs.close();
			ps.close();

			cnx.commit();
            
            }catch (SQLException e) {
                e.printStackTrace();
            }
        return categorie;
	}

	@Override
	public Categorie selectByLibelle(String libelle) {
		Categorie categorie = null;
        try (Connection cnx = ConnectionProvider.getConnection();) {

            PreparedStatement ps = cnx.prepareStatement(CHECKVALUEEXIST);
            
            ps.setString(1, libelle);

            
			ResultSet rs =  ps.executeQuery();
			if (rs.next()) {
				categorie = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle") );
			}
			rs.close();
			ps.close();

			cnx.commit();
            
            }catch (SQLException e) {
                e.printStackTrace();
            }
        return categorie;
	}


	@Override
	public void delete(int idCategorie) {
        try (Connection cnx = ConnectionProvider.getConnection();) {

            PreparedStatement ps = cnx.prepareStatement(DELETE);
            
            ps.setInt(1, idCategorie);
            
			ps.executeUpdate();			
		
			ps.close();

			cnx.commit();
            
            }catch (SQLException e) {
                e.printStackTrace();
            }
	}

	@Override
	public List<Article> allArticleByCateg(int idCategorie) {
		List<Article> listeArticles = new ArrayList<Article>();
        try (Connection cnx = ConnectionProvider.getConnection();) {

            PreparedStatement ps = cnx.prepareStatement(SELECTARTICLEBUCATEGORIE);
            
            ps.setInt(1, idCategorie);

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
                Utilisateur encherisseur = utilisateurDao.selectById(rs.getInt("encherisseur"));
                Enchere enchere = new Enchere(null, montant, encherisseur, null);                
                //Création article
                Article article = new Article(noArticle, nomArticle,description,date1,date2,prixInitial,prixVente,null,null,etatVente,image, null, enchere);
                enchere.setArticle(article);
                
                listeArticles.add(article);}
            
            }catch (SQLException e) {
                e.printStackTrace();
            }
        return listeArticles;
	}
}
