package dal;

import java.util.List;

import bo.Article;
import bo.Enchere;
import bo.Utilisateur;

/*
 * Définition des méthodes utilisées auprès de la BDD
 */
public interface EnchereDAO {	
	
	public Enchere getEnchereByArticle(Article article);
	
	public void createEnchere(Article article, Utilisateur util, int montantEnchere);
	
	public void updateEnchere(Article article, Utilisateur util, int montantEnchere);
}
