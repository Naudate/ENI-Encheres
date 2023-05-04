package filtres;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class FiltreConditionDutilisation
 */
@WebFilter(dispatcherTypes = {
		DispatcherType.REQUEST,
		}
		, urlPatterns = { "/*" })
public class FiltreConditionDutilisation extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public FiltreConditionDutilisation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest HttpRequest = (HttpServletRequest) request;
		HttpServletResponse HttpResponse = (HttpServletResponse) response;
		if(HttpRequest.getSession().getAttribute("Connected") != null ||
			HttpRequest.getServletPath().startsWith("/accueil")||
			HttpRequest.getServletPath().startsWith("/connexion")||
			HttpRequest.getServletPath().startsWith("/inscription")) {
			chain.doFilter(request, response);
		}
		else {
			RequestDispatcher rd = HttpRequest.getRequestDispatcher("/accueil");
			rd.forward(HttpRequest, HttpResponse);
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
