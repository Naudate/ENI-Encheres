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

    boolean delete(int id);

    void update(Utilisateur utilisateur);

	Utilisateur removeCredit(Utilisateur util, int proposition);

	void addCredit(Utilisateur util, int offre);

	void updateWithoutPassword(Utilisateur utilisateur);

	List<Utilisateur> selectAll();

	boolean changeActif(Utilisateur utilisateur);

	void updatePasswordByEmail(String email, String password);
}
