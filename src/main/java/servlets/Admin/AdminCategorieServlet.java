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

import javax.servlet.http.Cookie;

import bll.CategorieBLL;
import bo.Categorie;

@WebServlet("/admin/categories")
public class AdminCategorieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CategorieBLL categorieBLL;
	
	@Override
	public void init() throws ServletException {
		categorieBLL = new CategorieBLL();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Récupérer liste des utilisateurs
		
		List<Categorie> listCategorie = categorieBLL.selectAll();
		
		request.setAttribute("listCategorie", listCategorie);
		
		request.getRequestDispatcher("/admin/categories.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {					
			String categorie = (String) request.getParameter("categorie");
			if(categorie != null && !"".equals(categorie)) {
				categorieBLL.insert(categorie);
				request.setAttribute("messageSuccess", "Catégorie créée avec succès.");				
			}
			doGet(request, response);
			
		} catch (CategorieException e) {			
			request.setAttribute("messageError", e.getMessage());
			doGet(request, response);
		}catch(Exception e) {
			request.setAttribute("messageError", e.getMessage());
			doGet(request, response);
		}
	}

}
