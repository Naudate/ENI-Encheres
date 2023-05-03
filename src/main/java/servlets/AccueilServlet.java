package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bll.EnchereArticleBLL;
import bll.EnchereBLL;
import bll.UtilisateurBLL;
import bo.Enchere;
import bo.EnchereArticle;

@WebServlet("/accueil")
public class AccueilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EnchereArticleBLL enchereArticleBLL;
	
	@Override
	public void init() throws ServletException {
		enchereArticleBLL = new EnchereArticleBLL();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<EnchereArticle> listeEnchereArticle = enchereArticleBLL.selectJoin();
		request.setAttribute("listeEnchereArticle", listeEnchereArticle);
		request.getRequestDispatcher("accueil.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}