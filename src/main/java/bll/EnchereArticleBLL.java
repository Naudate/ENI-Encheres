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
}
