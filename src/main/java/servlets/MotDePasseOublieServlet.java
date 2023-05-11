package servlets;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.UUID;
import java.util.ArrayList;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bll.CategorieBLL;
import bll.EnchereArticleBLL;
import bll.UtilisateurBLL;
import bo.Categorie;
import bo.EnchereArticle;
import Exception.UtilisateurException;

@WebServlet("/motDePasseOublie")
public class MotDePasseOublieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UtilisateurBLL utilisateurBll;
	
	@Override
	public void init() throws ServletException {
		utilisateurBll = new UtilisateurBLL();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/motdepasseoublie.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		try {
			if(!utilisateurBll.verifEmail(email)){
				throw new UtilisateurException("Cette adresse mail n'est associé à aucun compte");
			}
			
			request.setAttribute("email", email);	
			UUID uuid = UUID.randomUUID();
			//RAF
			String urlV2 = "http://localhost:8080"+request.getContextPath()+"/updatePassword?token="+uuid;
			
			Cookie cookie = new Cookie(uuid.toString(), email);
			//5 minutes
	        cookie.setMaxAge(300);
	        response.addCookie(cookie);

			request.setAttribute("url", urlV2);
			request.getRequestDispatcher("/motdepasseoublie.jsp").forward(request, response);
		
		}catch(UtilisateurException ue) {
			request.setAttribute("email", email);			
			request.setAttribute("messageError", ue.getMessage());
			request.getRequestDispatcher("/motdepasseoublie.jsp").forward(request, response);
		} catch (Exception me) {
		    request.setAttribute("email", email);         
		    me.printStackTrace();
		    request.setAttribute("messageError", "Une erreur s'est produite lors de l'envoi de l'e-mail.");
		    request.getRequestDispatcher("/motdepasseoublie.jsp").forward(request, response);
		}
	}
}