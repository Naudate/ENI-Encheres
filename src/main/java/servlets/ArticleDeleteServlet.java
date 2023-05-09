package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exception.DetailArticleException;
import Exception.InscriptionException;
import bll.ArticleBLL;
import bo.Article;
import bo.Utilisateur;


@WebServlet("/deleteArticle/*")
public class ArticleDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArticleBLL articleBLL;
	
	@Override
	public void init() throws ServletException {
		articleBLL = new ArticleBLL();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String pathInfo = request.getPathInfo();		
		String[] pathParts = pathInfo.split("/");
		int idArticle = Integer.parseInt(pathParts[1]);
		
		Article article = articleBLL.selectById(idArticle);
		Utilisateur util = (Utilisateur) request.getSession().getAttribute("connected");
		System.out.println("ETAT VENTE AVANT " + article.getEtatVente());
		try {
			if(article.getUtilisateur().getNoUtilisateur() == util.getNoUtilisateur() && !articleBLL.delete(article)){
				request.setAttribute("messageSuccess", "Article supprimé avec succès");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/accueil");
				dispatcher.forward(request, response);
				
			}else {
				request.setAttribute("messageError", "Erreur lors de la suppression");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/accueil");
				dispatcher.forward(request, response);
			}
			
		}catch(InscriptionException e) {
			System.out.println("ETAT VENTE " + article.getEtatVente());
			request.setAttribute("messageError", e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/accueil");
			dispatcher.forward(request, response);
		}
	}
}

