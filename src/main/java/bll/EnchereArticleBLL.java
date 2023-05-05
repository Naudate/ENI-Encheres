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
        return dao.selectJoin();
    }
    public List<EnchereArticle> selectJoinCat(String categoriesql) {
        return dao.selectJoin();
    }
    
    public List<EnchereArticle> selectJoinCatLike(String categoriesql, String nom_articlelike) {
        return dao.selectJoin();
    }
}
