package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bll.UtilisateurBLL;
import bo.Utilisateur;


@WebServlet("/connexion")
public class ConnexionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UtilisateurBLL utilisateurBLL;
	
	@Override
	public void init() throws ServletException {
		utilisateurBLL = new UtilisateurBLL();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/connexion.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pseudo = request.getParameter("pseudo");
		String password = request.getParameter("password");

		Utilisateur util = utilisateurBLL.verifCompte(pseudo, password);
				
		if(util != null) {
			request.getSession().setAttribute("connected", util);
			response.sendRedirect("accueil");
		}else {
			request.getSession().setAttribute("connected", null);
			request.setAttribute("pseudo", pseudo);
			request.setAttribute("messageError", "Pseudo ou mot de passe incorrect");
			request.getRequestDispatcher("/connexion.jsp").forward(request, response);
		}				
	}

}
