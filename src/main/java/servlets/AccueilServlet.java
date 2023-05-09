package servlets;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bll.CategorieBLL;
import bll.EnchereArticleBLL;
import bo.Categorie;
import bo.EnchereArticle;

@WebServlet("/accueil")
public class AccueilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EnchereArticleBLL enchereArticleBLL;
	CategorieBLL categorieBLL;
	
	@Override
	public void init() throws ServletException {
		enchereArticleBLL = new EnchereArticleBLL();
		categorieBLL = new CategorieBLL();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<EnchereArticle> listeEnchereArticle;
		String categorie = (String) request.getParameter("selectCategory");
		System.out.println(categorie);
		String textArticle = (String) request.getParameter("textArticle");
		System.out.println("'" + textArticle+ "'");
		if(request.getSession().getAttribute("connected") != null)
		if(categorie != null || textArticle != null) {
			if(categorie == "" || categorie == null) {
				listeEnchereArticle = enchereArticleBLL.selectJoinLike(textArticle);
				System.out.println("like" + listeEnchereArticle);
				request.setAttribute("textArticle", textArticle);
			}
			else if(textArticle == "" || textArticle == null) {
				listeEnchereArticle = enchereArticleBLL.selectJoinCat(categorie);
				System.out.println("cat" + listeEnchereArticle);
				request.setAttribute("categorie", categorie);
			}
			else {
				listeEnchereArticle = enchereArticleBLL.selectJoinCatLike(categorie, textArticle);
				System.out.println("catlike" + listeEnchereArticle);
				request.setAttribute("textArticle", textArticle);
				request.setAttribute("categorie", categorie);
			}
		}
		else {
			listeEnchereArticle = enchereArticleBLL.selectJoin();
			System.out.println("classic" + listeEnchereArticle);
		}
		List<Categorie> listeCategorie = categorieBLL.selectAll();
		request.setAttribute("listeEnchereArticle", listeEnchereArticle);
		request.setAttribute("listeCategorie", listeCategorie);
		request.getRequestDispatcher("accueil.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}