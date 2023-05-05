package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bll.UtilisateurBLL;
import bo.Utilisateur;


@WebServlet("/deleteUser")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UtilisateurBLL utilisateurBLL;
	
	@Override
	public void init() throws ServletException {
		utilisateurBLL = new UtilisateurBLL();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		Utilisateur util = (Utilisateur) request.getSession().getAttribute("connected");
		try {
			if(utilisateurBLL.delete(util)) {
				request.getSession().setAttribute("connected", null);
				request.setAttribute("messageSuccess", "Compte supprimé avec succès");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/accueil");
				dispatcher.forward(request, response);
			}else {
				request.setAttribute("messageError", "Erreur lors de la suppression");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/accueil");
				dispatcher.forward(request, response);
			}
			
		}catch(Exception e) {
			
		}
	}

}
