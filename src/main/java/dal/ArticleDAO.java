package dal;


import java.util.List;
import bo.Article;
import bo.Utilisateur;

public interface ArticleDAO {
	//J'ai BESOIN de vendre/inséré un Article
	public Article insert(Article article) throws DALException;
	
    List<Article> selectAll();
    Article selectById(int id);
    public boolean delete(int id);

    void update(Article enchere);

	public List<Article> getArticleFromUtil(Utilisateur tilisateur);
}