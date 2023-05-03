package bll;

import java.util.List;

import bo.Utilisateur;
import dal.UtilisateurDAO;
import dal.DAOFactory;
import Exception.InscriptionException;

public class UtilisateurBLL {
    private UtilisateurDAO dao;

    public UtilisateurBLL() {
		dao = DAOFactory.getUtilisateurDAO();
	}

    public Utilisateur verifCompte(String pseudo, String password) {
        return dao.verifCompte(pseudo, password);
    }
    
    public Utilisateur inscription(String pseudo, String nom, String prenom, String email, String telephone, String rue, String codePostal, String ville, String motDePasse, String motDePasseConfirme) throws InscriptionException {
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
    	
    	//Vérifier mot de passe
    	if(!motDePasse.equals(motDePasseConfirme)) {
    		throw new InscriptionException("Les mots de passe ne correspondent pas.");
    	}
    	
    	//Vérifier mail
    	if(dao.checkEmail(email)) {
    		throw new InscriptionException("Cet email est déjà utilisé, veuillez changer.");
    	}
    	
    	//Vérifier pseudo
    	if(dao.checkPseudo(pseudo)) {
    		throw new InscriptionException("Ce pseudo est déjà utilisé, veuillez changer.");
    	}
    	
    	Utilisateur utilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, 100, false);
    	utilisateur = dao.insert(utilisateur);
    	return utilisateur;
    	
    	
    }
}
