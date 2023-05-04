package bll;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import bo.Utilisateur;
import bo.Article;
import bo.Categorie;
import dal.ArticleDAO;
import dal.DALException;
import dal.DAOFactory;

public class ArticleBLL {
    private static ArticleDAO dao;

    public ArticleBLL() {
		dao = DAOFactory.getArticleDAO();
	}

    public List<Article> selectAll() {
        return dao.selectAll();
    }
    public static Article insert(Article article) throws DALException, ParseException {
    	return dao.insert(article);   
    }


}
