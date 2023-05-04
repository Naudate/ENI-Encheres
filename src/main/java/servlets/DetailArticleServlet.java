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
import bll.ArticleBLL;
import bo.Article;


@WebServlet("/detailArticle/*")
public class DetailArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArticleBLL articleBLL;
	
	@Override
	public void init() throws ServletException {
		articleBLL = new ArticleBLL();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
						
		//Récupération de l'id
		try {
			String pathInfo = request.getPathInfo();		
			String[] pathParts = pathInfo.split("/");
			String idArticle = pathParts[1]; // {value}		
			Article article = articleBLL.selectById(Integer.parseInt(idArticle));		
			if(article == null) {
				throw new DetailArticleException("Cet article n'existe pas");
			}
			request.setAttribute("article", article);			
			request.getRequestDispatcher("/detailArticle.jsp").forward(request, response);	
			
		}catch(NumberFormatException nbEx) {
			request.setAttribute("messageError", "L'id dans l'url n'est pas un nombre. Veuillez ne pas jouer avec l'url");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/accueil");
			dispatcher.forward(request, response);
			
		}catch(DetailArticleException dee) {
			request.setAttribute("messageError", dee.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/accueil");
			dispatcher.forward(request, response);		
		}catch(Exception e) {
			System.out.println(e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/accueil");
			dispatcher.forward(request, response);
		}
		
	}

}
