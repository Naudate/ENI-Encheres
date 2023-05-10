package bll;

import java.util.List;

import bo.Article;
import bo.Categorie;
import bo.Utilisateur;
import dal.ArticleDAO;
import dal.CategorieDAO;
import dal.DAOFactory;
import dal.EnchereDAO;
import dal.UtilisateurDAO;
import Exception.CategorieException;

public class CategorieBLL {
	private CategorieDAO dao;
	private EnchereDAO enchereDao;
	private ArticleDAO articleDao;
	private UtilisateurDAO utilisateurDao;
	
	public CategorieBLL() {
	
		dao = DAOFactory.getCategorieDAO();
		enchereDao = DAOFactory.getEnchereDAO();
		articleDao = DAOFactory.getArticleDAO();
		utilisateurDao = DAOFactory.getUtilisateurDAO();
	}

	public List<Categorie> selectAll() {
        return dao.selectAll();
    }
	
	public void insert(String libelle) throws CategorieException {	
		if(libelle.length() > 30) {
			throw new CategorieException("Le nom est trop long, il ne doit pas dépasser 30 caractères");
		}
		if(dao.selectByLibelle(libelle) != null) {
			throw new CategorieException("Une catégorie existe déjà avec ce libelle");
		}				
        dao.insert(new Categorie(libelle));
    }
	
	public boolean delete(int idCategorie) throws CategorieException {	
		List<Article> articles = dao.allArticleByCateg(idCategorie);
		for (Article article : articles) {	
			int offre = article.getEnchere().getMontant();
			Utilisateur utilACrediter = article.getEnchere().getUtilisateur();
			if(article.getEtatVente().equals("EC") && utilACrediter != null) {
				utilisateurDao.addCredit(utilACrediter, offre);
			}			
			enchereDao.delete(article.getNoArticle());
			articleDao.delete(article.getNoArticle());
		}
		dao.delete(idCategorie);
		return true;
    }

	public boolean update(Integer idCateg, String categorie) throws CategorieException {
		if(dao.selectByLibelle(categorie) != null) {
			throw new CategorieException("Une catégorie existe déjà avec ce libelle");
		}
		dao.update(idCateg, categorie);
		return true;
	}
}
