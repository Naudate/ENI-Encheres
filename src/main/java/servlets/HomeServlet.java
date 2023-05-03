package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bll.EnchereBLL;
import bo.Enchere;


@WebServlet("/listes")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EnchereBLL enchereBll;
	
	@Override
	public void init() throws ServletException {
		enchereBll = new EnchereBLL();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Enchere> allEnchere = enchereBll.selectAll();
		
		System.out.println(allEnchere);
		
		request.setAttribute("listePanier", allEnchere);
		
		request.getRequestDispatcher("/WEB-INF/encheresRAF.jsp").forward(request, response);
	}

}
