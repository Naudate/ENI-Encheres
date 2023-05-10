package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exception.InscriptionException;
import bll.UtilisateurBLL;
import bo.Utilisateur;


@WebServlet("/inscription")
public class InsciptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UtilisateurBLL utilisateurBLL;
	
	@Override
	public void init() throws ServletException {
		utilisateurBLL = new UtilisateurBLL();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/inscription.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Récupération de tous les champs
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codePostal");
		String ville = request.getParameter("ville");
		String motDePasse = request.getParameter("motDePasse");
		String motDePasseConfirme = request.getParameter("motDePasseConfirme");
	
		try {
			Utilisateur util = utilisateurBLL.inscription(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, null, motDePasse, motDePasseConfirme, null);
			request.getSession().setAttribute("connected", util);
		} catch (InscriptionException e) {
			e.printStackTrace();
			//Attribution des valeur pour pouvoir les remmetre dans le formulaire d'inscription
			request.setAttribute("pseudo", pseudo);
			request.setAttribute("nom", nom);
			request.setAttribute("prenom", prenom);
			request.setAttribute("email", email);
			request.setAttribute("telephone", telephone);
			request.setAttribute("rue", rue);
			request.setAttribute("codePostal", codePostal);
			request.setAttribute("ville", ville);			
			request.setAttribute("messageError", e.getMessage());
			request.getRequestDispatcher("/inscription.jsp").forward(request, response);
		}

		response.sendRedirect("accueil");
	}

}
