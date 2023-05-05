package filtres;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

        String path = req.getRequestURI().substring(req.getContextPath().length());

        if (!path.startsWith("/accueil") && !path.startsWith("/connexion") && !path.startsWith("/deconnexion") && !path.startsWith("/inscription") && ((HttpServletRequest) request).getSession().getAttribute("connected") == null) {
        	request.setAttribute("messageInfo", "Connectez-vous à votre compte pour avoir accès aux enchères");
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
