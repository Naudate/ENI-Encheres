package servlets;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.UUID;
import java.util.ArrayList;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
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

@WebServlet("/updatePassword")
public class UpdatePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UtilisateurBLL utilisateurBll;
	
	@Override
	public void init() throws ServletException {
		utilisateurBll = new UtilisateurBLL();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/updatepassword.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password = request.getParameter("password");
		String token = request.getParameter("token");
		String email = null;
		boolean find = false;
		
		Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (token.equals(cookie.getName())) {
                	email = cookie.getValue();
                	utilisateurBll.updatePasswordByEmail(email, password);
                	cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    find = true;
                    request.setAttribute("messageSuccess", "Mot de passe réinitialisé avec succès");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("accueil");
                    dispatcher.forward(request, response);           	
                }
            }
        }			
        if(!find) {
            request.setAttribute("messageError", "Vous n'avez pas le droit de modifier le mot de passe");
    		
    		request.getRequestDispatcher("/updatepassword.jsp").forward(request, response);
        }

	}
}