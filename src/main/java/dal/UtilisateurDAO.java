package dal;

import java.util.List;

import bo.Utilisateur;

/*
 * Définition des méthodes utilisées auprès de la BDD
 */
public interface UtilisateurDAO {
	
	Utilisateur verifCompte(String pseudo, String pasword);
	
	boolean checkEmail(String email);
	
	boolean checkPseudo(String pseudo);
	
	Utilisateur insert(Utilisateur utilisateur);

    Utilisateur selectById(int id);

    void delete(int id);

    void update(Utilisateur utilisateur);
}
