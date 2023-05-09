package servlets;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bll.ArticleBLL;
import bll.CategorieBLL;
import bll.EnchereArticleBLL;
import bo.Categorie;
import bo.EnchereArticle;
import bo.Utilisateur;

@WebServlet("/accueil")
public class AccueilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArticleBLL articleBLL;
	EnchereArticleBLL enchereArticleBLL;
	CategorieBLL categorieBLL;

	@Override
	public void init() throws ServletException {
		enchereArticleBLL = new EnchereArticleBLL();
		categorieBLL = new CategorieBLL();
		articleBLL = new ArticleBLL();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<EnchereArticle> listeEnchereArticle = null;
		String categorie = (String) request.getParameter("selectCategory");
		System.out.println(categorie);
		String textArticle = (String) request.getParameter("textArticle");
		System.out.println("'" + textArticle+ "'");
		if(request.getSession().getAttribute("connected") != null) {
			Utilisateur user = (Utilisateur) request.getSession().getAttribute("connected");
			System.out.println(user.getPseudo() + "user");
			String mesachats = request.getParameter("mesachats");
			String mesventes= request.getParameter("mesventes");
			boolean mesachatsouverts = request.getParameter("mesachatsouverts") != null;
			boolean mesachatsencheres = request.getParameter("mesachatsencheres") != null;
			boolean mesachatsencheresremporte = request.getParameter("mesachatsencheresremporte") != null;
			boolean mesventesencours = request.getParameter("mesventesencours") != null;
			boolean mesventesnondebutees = request.getParameter("mesventesnondebutees") != null;
			boolean mesventesterminees = request.getParameter("mesventesterminees") != null;
			if(mesachats != null) {
				
			}
			
			if(mesventes != null) {
				List<EnchereArticle> listeEnchereArticleEncours = null;
				List<EnchereArticle> listeEnchereArticledebutees = null;
				List<EnchereArticle> listeEnchereArticleterminees = null;
				List<EnchereArticle> listeEnchereArticleByUser = enchereArticleBLL.selectJoinByUser(user.getPseudo());
				System.out.println(listeEnchereArticleByUser);
				
				if(mesventesencours) {
					listeEnchereArticleEncours = listeEnchereArticleByUser.stream()
													.filter(t -> t.getEtat_vente().contains("EC"))
													.collect(Collectors.toList());
					listeEnchereArticle.addAll(listeEnchereArticleEncours);
				}
				
				if(mesventesnondebutees) {
					listeEnchereArticleEncours = listeEnchereArticleByUser.stream()
							.filter(t -> t.getEtat_vente().contains("CR"))
							.collect(Collectors.toList());
					listeEnchereArticle.addAll(listeEnchereArticledebutees);
				}
				
				if(mesventesterminees) {
					listeEnchereArticleEncours = listeEnchereArticleByUser.stream()
							.filter(t -> t.getEtat_vente().contains("VD"))
							.collect(Collectors.toList());				
					listeEnchereArticle.addAll(listeEnchereArticleterminees);
				}
			}
			
			else {
				listeEnchereArticle = enchereArticleBLL.selectJoin();
				if(categorie != null || textArticle != null) {
					if(categorie == "" || categorie == null) {
						listeEnchereArticle = listeEnchereArticle.stream()
													.filter(t -> t.getNom_article().contains(textArticle))
													.collect(Collectors.toList());
					}
					
					else if(textArticle == "" || textArticle == null) {
						listeEnchereArticle = listeEnchereArticle.stream()
														.filter(t -> t.getCategorie().contains(categorie))
														.collect(Collectors.toList());
					}
					
					else {
						listeEnchereArticle = listeEnchereArticle.stream()
														.filter(t -> t.getNom_article().contains(textArticle))
														.collect(Collectors.toList());
						
						listeEnchereArticle = listeEnchereArticle.stream()
														.filter(t -> t.getCategorie().contains(categorie))
														.collect(Collectors.toList());
					}
				}
			}
			
		} else {
			if(categorie != null || textArticle != null) {
				if(categorie == "" || categorie == null) {
					listeEnchereArticle = enchereArticleBLL.selectJoinLike(textArticle);
					System.out.println("like" + listeEnchereArticle);
					request.setAttribute("textArticle", textArticle);
				}
				else if(textArticle == "" || textArticle == null) {
					listeEnchereArticle = enchereArticleBLL.selectJoinCat(categorie);
					System.out.println("cat" + listeEnchereArticle);
					request.setAttribute("categorie", categorie);
				}
				else {
					listeEnchereArticle = enchereArticleBLL.selectJoinCatLike(categorie, textArticle);
					System.out.println("catlike" + listeEnchereArticle);
					request.setAttribute("textArticle", textArticle);
					request.setAttribute("categorie", categorie);
				}
			}
			else {
				listeEnchereArticle = enchereArticleBLL.selectJoin();
				System.out.println("classic" + listeEnchereArticle);
			}
		}
		List<Categorie> listeCategorie = categorieBLL.selectAll();
		request.setAttribute("listeEnchereArticle", listeEnchereArticle);
		request.setAttribute("listeCategorie", listeCategorie);
		request.getRequestDispatcher("accueil.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}