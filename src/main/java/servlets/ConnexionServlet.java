package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

import bll.UtilisateurBLL;
import bo.Utilisateur;


@WebServlet("/connexion")
public class ConnexionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UtilisateurBLL utilisateurBLL;
	
	@Override
	public void init() throws ServletException {
		utilisateurBLL = new UtilisateurBLL();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pseudo = null;
		boolean remember = false;
		
		Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("userRemember".equals(cookie.getName())) {
                	pseudo = cookie.getValue();
                	remember = true;
                }
            }
        }
        request.setAttribute("pseudo", pseudo);
        request.setAttribute("remember", remember);
		
		request.getRequestDispatcher("/connexion.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pseudo = request.getParameter("pseudo");
		String password = request.getParameter("password");
		String remember = request.getParameter("remember");
				
		if("on".equals(remember)) {
			Cookie cookie = new Cookie("userRemember", pseudo);
	        cookie.setMaxAge(Integer.MAX_VALUE);
	        response.addCookie(cookie);
	        request.setAttribute("remember", true);
		}else{
			request.setAttribute("remember", false);
			Cookie[] cookies = request.getCookies();
	        if (cookies != null) {
	            for (Cookie cookie : cookies) {
	                if ("userRemember".equals(cookie.getName())) {
	                	cookie.setMaxAge(0);
	                    response.addCookie(cookie);
	                    request.setAttribute("remember", true);
	                    break;
	                }
	            }
	        }
		}

		Utilisateur util = utilisateurBLL.verifCompte(pseudo, password);	
				
		if(util != null) {
			if(!util.isActif()) {
				request.getSession().setAttribute("connected", null);
				request.setAttribute("pseudo", pseudo);
				request.setAttribute("messageError", "Votre compte est désactivé");
				request.getRequestDispatcher("/connexion.jsp").forward(request, response);
			}else {
				request.getSession().setAttribute("connected", util);
				response.sendRedirect("accueil");
			}			
		}else {
			request.getSession().setAttribute("connected", null);
			request.setAttribute("pseudo", pseudo);
			request.setAttribute("messageError", "Pseudo ou mot de passe incorrect");
			request.getRequestDispatcher("/connexion.jsp").forward(request, response);
		}				
	}

}
