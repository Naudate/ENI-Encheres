package servlets.Admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exception.UtilisateurException;

import javax.servlet.http.Cookie;

import bll.UtilisateurBLL;
import bo.Utilisateur;

@WebServlet("/admin/disableUser/*")
public class AdminDisableUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UtilisateurBLL utilisateurBLL;
	
	@Override
	public void init() throws ServletException {
		utilisateurBLL = new UtilisateurBLL();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//Récupération de l'id de l'utilisateur dans l'url
			String pathInfo = request.getPathInfo();		
			String[] pathParts = pathInfo.split("/");
			String id = pathParts[1];				
			Integer idUser = Integer.parseInt(id);
			
			//Récupération de l'utilisateur avec son ID
			Utilisateur util = utilisateurBLL.selectById(idUser);
			
			//Vérifier si le compte a désactiver est un admin
			if(util.isAdministrateur()) {
				throw new UtilisateurException("Pas possible de désactiver un compte admin");
			}
			
			//Faire le changement de l'état actif pour l'utilisateur
			if(utilisateurBLL.changeActif(util)) {
				request.setAttribute("messageSuccess", "Compte désactivé avec succès");
				response.sendRedirect(request.getContextPath()+ "/admin/allUser");
			}else {
				request.setAttribute("messageError", "Erreur lors de la désactivation");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/allUser");
				dispatcher.forward(request, response);
			}
		}catch(NullPointerException npe) {
			npe.printStackTrace();
			request.setAttribute("messageError", "L'url ne correspond pas");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/allUser");
			dispatcher.forward(request, response);
		}catch(UtilisateurException ue) {
			request.setAttribute("messageError", ue.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/allUser");
			dispatcher.forward(request, response);
		}
	}

}
