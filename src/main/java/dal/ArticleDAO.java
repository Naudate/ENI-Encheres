package dal;

import bo.Article;

public interface ArticleDAO {
	
	//J'ai BESOIN de vendre/ inséré un Article
	public void insert(Article article) throws DALException;
}
