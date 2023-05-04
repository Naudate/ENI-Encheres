package dal;

import java.util.List;

import bo.EnchereArticle;

public interface EnchereArticleDAO {
	
	List<EnchereArticle> selectJoin();
}
