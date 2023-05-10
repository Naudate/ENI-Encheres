package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exception.InscriptionException;
import bll.UtilisateurBLL;
import bo.Utilisateur;


@WebServlet("/user/*")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UtilisateurBLL utilisateurBLL;
	
	@Override
	public void init() throws ServletException {
		utilisateurBLL = new UtilisateurBLL();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		try {
			String pathInfo = request.getPathInfo();		
			String[] pathParts = pathInfo.split("/");
			String id = pathParts[1];	
			
			Utilisateur util = new Utilisateur();
			
			if("me".equals(id)) {
				util = (Utilisateur) request.getSession().getAttribute("connected");
				request.setAttribute("me", true);
			}else {
				try {
					int idUtil = Integer.parseInt(id);
					util = utilisateurBLL.selectById(idUtil);					
				}catch(NumberFormatException nbEx) {
					request.setAttribute("messageError", "L'id dans l'url n'est pas un nombre. Veuillez ne pas jouer avec l'url");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/accueil");
					dispatcher.forward(request, response);
				}
			}
			
			if(util == null) {
				request.setAttribute("messageError", "Cet utilisateur n'existe pas");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/accueil");
				dispatcher.forward(request, response);
			}
			
			request.setAttribute("util", util);
			
			request.getRequestDispatcher("/utilisateur.jsp").forward(request, response);
			
		}catch(NullPointerException npe) {
			request.setAttribute("messageError", "L'url ne correspond pas");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/accueil");
			dispatcher.forward(request, response);
		}	
	}
	
	@Override
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
				String newMotDePasse = request.getParameter("newMotDePasse");
				String confirmation = request.getParameter("confirmation");
				
				Utilisateur util = (Utilisateur) request.getSession().getAttribute("connected");
			
				try {
					util = utilisateurBLL.inscription(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, newMotDePasse, confirmation, util);
					request.getSession().setAttribute("connected", util);
					request.setAttribute("messageSuccess", "Compte modifié avec succès");					
					RequestDispatcher dispatcher = request.getRequestDispatcher("/accueil");
					dispatcher.forward(request, response);
				} catch (InscriptionException e) {
					e.printStackTrace();
					//Attribution des valeur pour pouvoir les remmetre dans le formulaire d'inscription
					request.setAttribute("util", util);
					request.setAttribute("me", true);
					request.setAttribute("messageError",e.getMessage());
					request.getRequestDispatcher("/utilisateur.jsp").forward(request, response);
				}
	}

}
