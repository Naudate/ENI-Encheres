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

@WebServlet("/admin/deleteUser/*")
public class AdminDeleteUserServlet extends HttpServlet {
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
			
			Integer idUser = Integer.parseInt(id);
			
			Utilisateur util = utilisateurBLL.selectById(idUser);
			
			if(util.isAdministrateur()) {
				throw new UtilisateurException("Pas possible de supprimer un compte admin");
			}
			
			if(utilisateurBLL.delete(util)) {				
				request.setAttribute("messageSuccess", "Compte supprimé avec succès");
				response.sendRedirect(request.getContextPath()+ "/admin/allUser");
			}else {
				request.setAttribute("messageError", "Erreur lors de la suppression");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/allUser");
				dispatcher.forward(request, response);
			}
		}catch(NullPointerException npe) {
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
