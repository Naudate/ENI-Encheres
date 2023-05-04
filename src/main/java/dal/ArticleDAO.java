package dal;


import java.util.List;
import bo.Article;

public interface ArticleDAO {
	//J'ai BESOIN de vendre/inséré un Article
	public Article insert(Article article) throws DALException;
	
    List<Article> selectAll();
    Article selectById(int id);

    void delete(int id);

    void update(Article enchere);
}