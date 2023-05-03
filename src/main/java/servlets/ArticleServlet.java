package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bll.ArticleBLL;
import bo.Article;
import dal.DALException;


@WebServlet("/articles")
public class ArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArticleBLL articleBll;
	
	@Override
	public void init() throws ServletException {
		articleBll = new ArticleBLL();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Article> allArticle = articleBll.selectAll();
		Article allArticle2 = null;
		try {
			allArticle2 = articleBll.insert(allArticle2);
		} catch (DALException e) {
			e.printStackTrace();
		}
		
		System.out.println(allArticle);
		
		request.setAttribute("listePanier", allArticle);
		request.setAttribute("test", allArticle2);
		
		request.getRequestDispatcher("/WEB-INF/encheresRAF.jsp").forward(request, response);
	}

}