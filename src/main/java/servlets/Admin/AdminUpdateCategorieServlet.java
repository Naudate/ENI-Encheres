package servlets.Admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exception.CategorieException;
import Exception.UtilisateurException;

import javax.servlet.http.Cookie;

import bll.CategorieBLL;
import bll.UtilisateurBLL;
import bo.Utilisateur;

@WebServlet("/admin/updateCategorie/*")
public class AdminUpdateCategorieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CategorieBLL categorieBLL;
	
	@Override
	public void init() throws ServletException {
		categorieBLL = new CategorieBLL();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String pathInfo = request.getPathInfo();		
			String[] pathParts = pathInfo.split("/");
			String id = pathParts[1];	
			
			Integer idCateg = Integer.parseInt(id);			
			
			String categorie = request.getParameter("categorie-"+idCateg);
					
			if(categorieBLL.update(idCateg, categorie)) {				
				request.setAttribute("messageSuccess", "Catégorie modifiée avec succès");
				response.sendRedirect(request.getContextPath()+ "/admin/categories");
			}else {
				request.setAttribute("messageError", "Erreur lors de la modification");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/categories");
				dispatcher.forward(request, response);
			}
		}catch(NullPointerException npe) {
			request.setAttribute("messageError", "L'url ne correspond pas");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/categories");
			dispatcher.forward(request, response);
		} catch (CategorieException e) {			
			request.setAttribute("messageError", e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/categories");
			dispatcher.forward(request, response);
		}
	}

}
