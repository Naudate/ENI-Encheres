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
import bll.CategorieBLL;
import bll.RetraitsBLL;
import bo.Article;
import bo.Categorie;
import bo.Retraits;
import bo.Utilisateur;
import dal.DALException;


@WebServlet("/articles")
public class ArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArticleBLL articleBll;
	RetraitsBLL retraitBll;
	CategorieBLL categorieBll;
	
	@Override
	public void init() throws ServletException {
		articleBll = new ArticleBLL();
		categorieBll = new CategorieBLL();
		retraitBll = new RetraitsBLL();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur user = (Utilisateur) request.getSession().getAttribute("connected");
		List<Article> allArticle = articleBll.selectAll();
		List<Categorie> listCategorie = categorieBll.selectAll();
		Article allArticle2 = null;
		request.setAttribute("listePanier", allArticle);
		request.setAttribute("listCategorie", listCategorie);
		request.setAttribute("rueText", user.getRue());
		request.setAttribute("villeText", user.getVille());
		request.setAttribute("codeText", user.getCode_postal());
		
		request.getRequestDispatcher("AjoutArticle.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Récupération de tous les champs Articles
		String nomArticle = request.getParameter("nom_article");
    	String description = request.getParameter("description");
    	String dateDebutEnchere = request.getParameter("date_debut_enchere");
        String dateFinEnchere = request.getParameter("date_fin_enchere");
        String prixInitial = request.getParameter("prix_initial");
        Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("connected");
        Categorie categorie =  new Categorie(Integer.parseInt(request.getParameter("listCategorie")),null);
        //String image = request.getParameter("image");
        //Récupération de tous les champs Retraits
        String rue = request.getParameter("rue");
        String codePostal = request.getParameter("ville");
        String ville = request.getParameter("code_postal");
        
        
        try {
			  //convert String to LocalDate
			  LocalDate date1 = LocalDate.parse(dateDebutEnchere);
			  LocalDate date2 = LocalDate.parse(dateFinEnchere);
			  int prixInt = Integer.parseInt(prixInitial);
			  
			  //Insertion Article
			  Article articleInsert = new Article(nomArticle, description, date1, date2, prixInt, 0, utilisateur, categorie, "CR",null,null,null);
			  Article article = articleBll.insert(articleInsert);
			  //Insertion Retraits
			  Retraits retraitsInsert = new Retraits(article,rue,codePostal,ville);
			  Retraits retraits = retraitBll.insert(retraitsInsert);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		response.sendRedirect("accueil");
	}


}