package servlets;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exception.InscriptionException;
import bll.ArticleBLL;
import bo.Article;
import bo.Categorie;
import bo.Utilisateur;
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
		request.setAttribute("listePanier", allArticle);

		
		request.getRequestDispatcher("AjoutArticle.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Récupération de tous les champs
		String nomArticle = request.getParameter("nom_article");
    	String description = request.getParameter("description");
    	String dateDebutEnchere = request.getParameter("date_debut_enchere");
        String dateFinEnchere = request.getParameter("date_fin_enchere");
        String prixInitial = request.getParameter("prix_initial");
        String prixVente = request.getParameter("prix_vente");
        Utilisateur utilisateur = new Utilisateur();
        Categorie categorie = new Categorie();
        String etatVente = request.getParameter("etat_vente");
        //String image = request.getParameter("image");
        System.out.println(nomArticle +"  "+ description +"  "+  dateDebutEnchere +"  "+ dateFinEnchere+"  "+ prixInitial+"  "+ prixVente +"  "+ utilisateur +"  "+ categorie+"  "+etatVente);
		try {
			  //convert String to LocalDate
			  LocalDate date1 = LocalDate.parse(dateDebutEnchere);
			  LocalDate date2 = LocalDate.parse(dateFinEnchere);
			  int prixInt = Integer.parseInt(prixInitial);
			  int prixVent = Integer.parseInt(prixVente);
			
	    	 Article articleInsert = new Article(nomArticle, description, date1, date2, prixInt, prixVent, utilisateur, categorie, etatVente,null,null,null);
				Article article = ArticleBLL.insert(articleInsert);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		response.sendRedirect("accueil");
	}


}