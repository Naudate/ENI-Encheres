package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bll.ArticleBLL;
import bll.CategorieBLL;
import bll.RetraitsBLL;

@WebServlet("/articles/update")
public class ArticleUpdate extends HttpServlet {

	private static final long serialVersionUID = 1L;
	ArticleBLL articleBll;
	
	public void init() throws ServletException {
		articleBll = new ArticleBLL();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}
}
