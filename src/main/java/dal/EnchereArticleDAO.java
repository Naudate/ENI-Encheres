package dal;

import java.util.List;

import bo.EnchereArticle;

public interface EnchereArticleDAO {
	
	List<EnchereArticle> selectJoin();
	List<EnchereArticle> selectJoinCat(String categoriesql);
	List<EnchereArticle> selectJoinLike(String nom_articlelike);
	List<EnchereArticle> selectJoinCatLike(String categoriesql, String nom_articlelike);
}
