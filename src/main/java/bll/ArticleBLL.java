package bll;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import Exception.DetailArticleException;
import Exception.InscriptionException;
import bo.Article;
import bo.Enchere;
import bo.Utilisateur;
import bo.Categorie;
import dal.ArticleDAO;
import dal.DALException;
import dal.DAOFactory;
import dal.EnchereDAO;
import dal.UtilisateurDAO;

public class ArticleBLL {
    private ArticleDAO dao;
    private EnchereDAO daoEnchere;
    private UtilisateurDAO daoUtilisateur;

    public ArticleBLL() {
		dao = DAOFactory.getArticleDAO();
		daoEnchere = DAOFactory.getEnchereDAO();
		daoUtilisateur = DAOFactory.getUtilisateurDAO();
	}   

    public List<Article> selectAll() {
        return dao.selectAll();
    }
    public Article insert(Article article) throws DALException, ParseException {
    	return dao.insert(article);   
    }
    public Article selectById(int idArticle) {
        return dao.selectById(idArticle);
    }
    public boolean delete(Article article) throws InscriptionException {
    	if( !"CR".equals(article.getEtatVente())) {
    		throw new InscriptionException("La vente a commencé" + article.getEtatVente());
    	}else {
    			//Supprimer l'article	
    			daoEnchere.delete(article.getNoArticle());
    			return dao.delete(article.getNoArticle());
    			}
    	}
    
    public void checkProposition(Article article, int proposition, Utilisateur util) throws DetailArticleException {
   	
    	Enchere enchere = daoEnchere.getEnchereByArticle(article);
    	if(proposition > util.getCredit()) {
			String messageErreur = String.format("Il n'est pas possible de faire une proposition supérieur à votre nombre de crédit qui est de %d", util.getCredit());
			throw new DetailArticleException(messageErreur);
		}
    	//Si pas d'enchère la créée
    	if(enchere == null) {    		
    		daoEnchere.createEnchere(article, util,proposition);
    		daoUtilisateur.removeCredit(util, proposition);
    	}else {

    		//Vérifier que la proposition est supérieur au montant actuel
    		if(proposition > enchere.getMontant()) { 
    			//Si oui supprimer les crédit de l'utilisateur
    			util = daoUtilisateur.removeCredit(util, proposition);
    			Utilisateur utilisateurPrec = enchere.getUtilisateur();
    			if(util.getNoUtilisateur() == utilisateurPrec.getNoUtilisateur()) {
    				utilisateurPrec = util;
    			}
    			int offre = enchere.getMontant();    	
        		//recréditer l'ancien utilisateur
    			daoUtilisateur.addCredit(utilisateurPrec, offre);
    			//Mettre à jour l'enchère
    			//daoEnchere.updateEnchere(article, util, proposition);
    			daoEnchere.createEnchere(article, util,proposition);
    		}else {
    			throw new DetailArticleException("Il n'est pas possible de faire une proposition inférieur à la meilleure offre");
    		}
    	}
    }    
}
