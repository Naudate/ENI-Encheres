package bll;

import java.util.List;

import bo.Article;
import dal.ArticleDAO;
import dal.DAOFactory;

public class ArticleBLL {
    private ArticleDAO dao;

    public ArticleBLL() {
		dao = DAOFactory.getArticleDAO();
	}
    
    public Article selectById(int idArticle) {
        return dao.selectById(idArticle);
    }
    
}
