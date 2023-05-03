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
    public Article insert() throws DALException {
    	//Test inséré Utilisateur + Catégorie + Article
    	Utilisateur user = new Utilisateur(6,"laure","Bluth","Laure","laurebluth@campus.fr","0202020202","rue de Nantes","44000","Nantes","Pa$$w0rd",0,false);
    	Categorie categorie = new Categorie(1,"Informatique");
    	Date date = new Date(5, 5, 5);
    	Article art = new Article("xbox","truc",date,date,1,1,user,categorie,"VD","truc");
    	return dao.insert(art);   
    }


}
