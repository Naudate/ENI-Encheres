package bll;

import java.util.List;

import bo.Utilisateur;
import dal.UtilisateurDAO;
import dal.DAOFactory;

public class UtilisateurBLL {
    private UtilisateurDAO dao;

    public UtilisateurBLL() {
		dao = DAOFactory.getUtilisateurDAO();
	}

    public Utilisateur verifCompte(String pseudo, String password) {
        return dao.verifCompte(pseudo, password);
    }
}
