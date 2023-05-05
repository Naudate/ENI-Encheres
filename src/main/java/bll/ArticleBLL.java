package bll;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import Exception.DetailArticleException;
import bo.Article;
import bo.Enchere;
import bo.Utilisateur;
import bo.Categorie;
import dal.ArticleDAO;
import dal.DALException;
import dal.DAOFactory;
import dal.EnchereDAO;

public class ArticleBLL {
    private ArticleDAO dao;
    private EnchereDAO daoEnchere;

    public ArticleBLL() {
		dao = DAOFactory.getArticleDAO();
		daoEnchere = DAOFactory.getEnchereDAO();
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
    
    public void checkProposition(Article article, int proposition, Utilisateur util) throws DetailArticleException {
   	
    	Enchere enchere = daoEnchere.getEnchereByArticle(article);
    	    	
    	if(enchere == null) {
    		daoEnchere.createEnchere(article, util,proposition);
    	}else {
    		if(proposition > util.getCredit()) {
    			String messageErreur = String.format("Il n'est pas possible de faire une proposition supérieur à votre nombre de crédit qui est de %d", util.getCredit());
    			throw new DetailArticleException(messageErreur);
    		}
    		if(proposition > enchere.getMontant()) {
    			daoEnchere.updateEnchere(article, util, proposition);
    		}else {
    			throw new DetailArticleException("Il n'est pas possible de faire une proposition inférieur à la meilleure offre");
    		}
    	}
    }    
}