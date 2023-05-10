package dal;

import java.util.List;

import bo.Article;
import bo.Categorie;

public interface CategorieDAO {
	List<Categorie> selectAll();
	
	Categorie selectByLibelle(String libelle);

	Categorie insert(Categorie categorie);
	
	void delete(int idCategorie);

	List<Article> allArticleByCateg(int idCategorie);

	void update(Integer idCateg, String libelle);
}
