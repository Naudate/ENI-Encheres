package filtres;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.Utilisateur;

import java.io.IOException;

public class FiltreConnexion implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // initialization code goes here
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;       
        
        HttpSession session = req.getSession(false); // récupérer la session existante sans en créer une nouvelle
			if (session != null) {
			    long lastAccessedTime = session.getLastAccessedTime();
			    long currentTime = System.currentTimeMillis();
			    if (currentTime - lastAccessedTime > 300000) { // 5 minutes en millisecondes
			    	req.getSession().setAttribute("connected", null); // invalider la session pour déconnecter l'utilisateur
			    }
			}
        
        String path = req.getRequestURI().substring(req.getContextPath().length());

        if (!path.startsWith("/accueil") && !path.startsWith("/motDePasseOublie") && !path.startsWith("/updatePassword") && !path.startsWith("/connexion")&& !path.startsWith("/images") && !path.startsWith("/deconnexion") && !path.startsWith("/inscription") && ((HttpServletRequest) request).getSession().getAttribute("connected") == null) {
        	request.setAttribute("messageInfo", "Connectez-vous pour avoir accès aux enchères");
    		RequestDispatcher dispatcher = request.getRequestDispatcher("/accueil");
    		dispatcher.forward(request, response);
            return;
        }
        
        Utilisateur connected = (Utilisateur) ((HttpServletRequest) request).getSession().getAttribute("connected");
        
        if (path.startsWith("/admin") && connected.isAdministrateur() == false) {
        	request.setAttribute("messageInfo", "Il faut être administrateur pour accéder à cette page");
    		RequestDispatcher dispatcher = request.getRequestDispatcher("/accueil");
    		dispatcher.forward(request, response);
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // cleanup code goes here
    }
}
