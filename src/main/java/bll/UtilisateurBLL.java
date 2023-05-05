package bll;

import java.util.Iterator;
import java.util.List;

import bo.Article;
import bo.Utilisateur;
import dal.UtilisateurDAO;
import dal.ArticleDAO;
import dal.DAOFactory;
import dal.EnchereDAO;
import Exception.InscriptionException;

public class UtilisateurBLL {
    private UtilisateurDAO dao;
    private ArticleDAO articleDao;
    private EnchereDAO enchereDao;

    public UtilisateurBLL() {
		dao = DAOFactory.getUtilisateurDAO();
		articleDao = DAOFactory.getArticleDAO();
		enchereDao = DAOFactory.getEnchereDAO();
	}

    public Utilisateur verifCompte(String pseudo, String password) {
        return dao.verifCompte(pseudo, password);
    }
    
    public Utilisateur inscription(String pseudo, String nom, String prenom, String email, String telephone, String rue, String codePostal, String ville, String actualMotDePasse, String motDePasse, String motDePasseConfirme, Utilisateur actualUser) throws InscriptionException {
    	//Vérification des données avant d'insérer les données
    	if(pseudo == null || nom == null || prenom == null || email == null || telephone == null || rue == null || codePostal == null || ville == null || motDePasse == null || motDePasseConfirme == null) {
    		throw new InscriptionException("Un des champs est vide");
    	}    	
    	
    	//Vérifier téléphone
    	if(telephone.length() != 10 || !(telephone.matches("[0-9]{10}"))) {
    		throw new InscriptionException("Le format du téléphone doit être de 10 chiffres sans espace.");
    	}

    	//Vérifier code postal
    	if(codePostal.length() != 5 || !(codePostal.matches("[0-9]{5}"))) {
    		throw new InscriptionException("Le format du code postal doit être de 5 chiffres sans espace.");
    	}
    	
    	//Vérifier mot de passe actuel si dans le cas de la modification de compte
    	if(actualMotDePasse != null && dao.verifCompte(actualUser.getPseudo(), actualMotDePasse) == null) {    		
    		throw new InscriptionException("Le mot de passe actuel ne corresponds pas");  
    	}
    	if(!motDePasse.equals(motDePasseConfirme)) {
    		throw new InscriptionException("Les mots de passe ne correspondent pas.");
    	}
    	
    	//Vérifier mot de passe
    	if(!motDePasse.equals(motDePasseConfirme)) {
    		throw new InscriptionException("Les mots de passe ne correspondent pas.");
    	}
    	
    	//Vérifier mail
    	if(!email.equals(actualUser.getEmail()) && dao.checkEmail(email)) {
    		throw new InscriptionException("Cet email est déjà utilisé, veuillez changer.");
    	}
    	
    	//Vérifier pseudo
    	if(!pseudo.equals(actualUser.getPseudo()) && dao.checkPseudo(pseudo)) {
    		throw new InscriptionException("Ce pseudo est déjà utilisé, veuillez changer.");
    	}
    	
    	if(actualMotDePasse == null) {
    		Utilisateur utilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, 100, false);
        	utilisateur = dao.insert(utilisateur);
        	return utilisateur;
    	}else{
    		Utilisateur utilisateur = new Utilisateur(actualUser.getNoUtilisateur(), pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, actualUser.getCredit(), false);
        	dao.update(utilisateur);
        	return utilisateur;
    	}
    	
    }

	public Utilisateur selectById(int idUtil) {
		return dao.selectById(idUtil);
	}

	public boolean delete(Utilisateur util) {		
		//Récupérer tout les article en vente de l'utilisateur
		List<Article> articles = articleDao.getArticleFromUtil(util);
		//Re-créditer les utilisateurs
		for (Article article : articles) {	
			int offre = article.getEnchere().getMontant();
			Utilisateur utilACrediter = article.getEnchere().getUtilisateur();
			dao.addCredit(utilACrediter, offre);
			enchereDao.delete(article.getNoArticle());
			articleDao.delete(article.getNoArticle());
		}
		//Supprimer le comptes et vérifier que les article et les enchères disparaissent		
		return dao.delete(util.getNoUtilisateur());
	}
}
