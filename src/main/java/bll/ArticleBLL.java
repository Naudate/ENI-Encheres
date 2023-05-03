package bll;

import java.sql.Date;
import java.util.List;

import bo.Utilisateur;
import bo.Article;
import bo.Categorie;
import dal.ArticleDAO;
import dal.DALException;
import dal.DAOFactory;

public class ArticleBLL {
    private ArticleDAO dao;

    public ArticleBLL() {
		dao = DAOFactory.getArticleDAO();
	}

    public List<Article> selectAll() {
        return dao.selectAll();
    }
    public Article insert(Article article) throws DALException {
    	
    	return dao.insert(article);   
    }


}
