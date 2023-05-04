package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exception.DetailEnchereException;
import bll.EnchereBLL;
import bo.Enchere;
import services.ConvertionService;


@WebServlet("/detailEnchere/*")
public class DetailEnchereServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EnchereBLL enchereBLL;
	
	@Override
	public void init() throws ServletException {
		enchereBLL = new EnchereBLL();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
						
		//Récupération de l'id
		try {
			String pathInfo = request.getPathInfo();		
			String[] pathParts = pathInfo.split("/");
			String idArticle = pathParts[1]; // {value}		
			Enchere enchere = enchereBLL.selectById(Integer.parseInt(idArticle));		
			if(enchere == null) {
				throw new DetailEnchereException("Cet article n'existe pas");
			}
			request.setAttribute("enchere", enchere);			
			request.getRequestDispatcher("/detailEnchere.jsp").forward(request, response);	
			
		}catch(NumberFormatException nbEx) {
			request.setAttribute("message", "L'id dans l'url n'est pas un nombre. Veuillez ne pas jouer avec l'url");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/accueil");
			dispatcher.forward(request, response);
			
		}catch(DetailEnchereException dee) {
			request.setAttribute("message", dee.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/accueil");
			dispatcher.forward(request, response);;			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/accueil");
			dispatcher.forward(request, response);
		}
		
	}

}
