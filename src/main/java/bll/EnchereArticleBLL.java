package bll;

import java.util.List;

import bo.EnchereArticle;
import dal.DAOFactory;
import dal.EnchereArticleDAO;

public class EnchereArticleBLL {
	private EnchereArticleDAO dao;
	
	public EnchereArticleBLL() {
		dao = DAOFactory.getEnchereArticleDAO();
	}

    public List<EnchereArticle> selectJoin() {
        return dao.selectJoin();
    }
    
    public List<EnchereArticle> selectJoinLike(String nom_articlelike) {
        return dao.selectJoinLike(nom_articlelike);
    }
    public List<EnchereArticle> selectJoinCat(String categoriesql) {
        return dao.selectJoinCat(categoriesql);
    }
    
    public List<EnchereArticle> selectJoinCatLike(String categoriesql, String nom_articlelike) {
        return dao.selectJoinCatLike(categoriesql, nom_articlelike);
    }
}
