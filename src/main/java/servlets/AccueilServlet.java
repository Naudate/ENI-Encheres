package servlets;

import java.io.IOException;
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
import bll.UtilisateurBLL;
import bo.Categorie;
import bo.EnchereArticle;
import bo.Utilisateur;

@WebServlet("/accueil")
public class AccueilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArticleBLL articleBLL;
	EnchereArticleBLL enchereArticleBLL;
	CategorieBLL categorieBLL;
	UtilisateurBLL utilisateurBLL;

	@Override
	public void init() throws ServletException {
		enchereArticleBLL = new EnchereArticleBLL();
		categorieBLL = new CategorieBLL();
		articleBLL = new ArticleBLL();
		utilisateurBLL = new UtilisateurBLL();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<EnchereArticle> listeEnchereArticle = new ArrayList<EnchereArticle>();
		String categorie = (String) request.getParameter("selectCategory");
		String textArticle = (String) request.getParameter("textArticle");
		if (request.getSession().getAttribute("connected") != null) {
			Utilisateur user = (Utilisateur) request.getSession().getAttribute("connected");
			String mestrucs = request.getParameter("mestrucs");
			String mesachatsouverts = request.getParameter("mesachatsouverts");
			String mesachatsencheres = request.getParameter("mesachatsencheres");
			String mesachatsencheresremporte = request.getParameter("mesachatsencheresremporte");
			String mesventesencours = request.getParameter("mesventesencours");
			String mesventesnondebutees = request.getParameter("mesventesnondebutees");
			String mesventesterminees = request.getParameter("mesventesterminees");
			if (mestrucs != null) {
				request.setAttribute("mestrucs", mestrucs);
				if (mestrucs.equals("mesachats")) {
					if (mesachatsouverts != null) {
						listeEnchereArticle = enchereArticleBLL.selectJoin();
						request.setAttribute("mesachatsouverts", mesachatsouverts);
					}

					if (mesachatsencheres != null) {
						listeEnchereArticle = enchereArticleBLL.selectJoinByUserEnchere(user.getPseudo());
						for (EnchereArticle enchereArticle : listeEnchereArticle) {
							var u = enchereArticle.getNo_utilisateur();
							var p = utilisateurBLL.selectById(u);
							enchereArticle.setPseudo(p.getPseudo());
						}
						request.setAttribute("mesachatsencheres", mesachatsencheres);
					}

					if (mesachatsencheresremporte != null) {
						listeEnchereArticle = enchereArticleBLL.selectJoinByUserEnchereVD(user.getPseudo());
						for (EnchereArticle enchereArticle : listeEnchereArticle) {
							var u = enchereArticle.getNo_utilisateur();
							var p = utilisateurBLL.selectById(u);
							enchereArticle.setPseudo(p.getPseudo());
						}
						request.setAttribute("mesachatsencheresremporte", mesachatsencheresremporte);
					}

				}

				if (mestrucs.equals("mesventes")) {
					List<EnchereArticle> listeEnchereArticleEncours = new ArrayList<EnchereArticle>();
					List<EnchereArticle> listeEnchereArticledebutees = new ArrayList<EnchereArticle>();
					List<EnchereArticle> listeEnchereArticleterminees = new ArrayList<EnchereArticle>();
					List<EnchereArticle> listeEnchereArticleByUser = enchereArticleBLL
							.selectJoinByUser(user.getPseudo());
					
					System.out.println(listeEnchereArticleByUser);

					if (mesventesencours != null) {
						listeEnchereArticleEncours = listeEnchereArticleByUser.stream()
								.filter(t -> t.getEtat_vente().contains("EC")).collect(Collectors.toList());
						listeEnchereArticle.addAll(listeEnchereArticleEncours);
						request.setAttribute("mesventesencours", mesventesencours);
					}

					if (mesventesnondebutees != null) {
						listeEnchereArticledebutees = listeEnchereArticleByUser.stream()
								.filter(t -> t.getEtat_vente().contains("CR")).collect(Collectors.toList());
						listeEnchereArticle.addAll(listeEnchereArticledebutees);
						request.setAttribute("mesventesnondebutees", mesventesnondebutees);
					}

					if (mesventesterminees != null) {
						listeEnchereArticleterminees = listeEnchereArticleByUser.stream()
								.filter(t -> t.getEtat_vente().contains("VD")).collect(Collectors.toList());
						listeEnchereArticle.addAll(listeEnchereArticleterminees);
						request.setAttribute("mesventesterminees", mesventesterminees);
					}
				}

				if (categorie != null || textArticle != null) {
					if (categorie == "" || categorie == null) {
						listeEnchereArticle = listeEnchereArticle.stream()
								.filter(t -> t.getNom_article().contains(textArticle)).collect(Collectors.toList());
						request.setAttribute("textArticle", textArticle);
					}

					else if (textArticle == "" || textArticle == null) {
						listeEnchereArticle = listeEnchereArticle.stream()
								.filter(t -> t.getCategorie().contains(categorie)).collect(Collectors.toList());
						request.setAttribute("categorie", categorie);
					}

					else {
						listeEnchereArticle = listeEnchereArticle.stream()
								.filter(t -> t.getNom_article().contains(textArticle)).collect(Collectors.toList());

						listeEnchereArticle = listeEnchereArticle.stream()
								.filter(t -> t.getCategorie().contains(categorie)).collect(Collectors.toList());
						request.setAttribute("textArticle", textArticle);
						request.setAttribute("categorie", categorie);
					}
				}
			} else {
				listeEnchereArticle = enchereArticleBLL.selectJoin();
				if (categorie != null || textArticle != null) {
					if (categorie == "" || categorie == null) {
						listeEnchereArticle = listeEnchereArticle.stream()
								.filter(t -> t.getNom_article().contains(textArticle)).collect(Collectors.toList());
						request.setAttribute("textArticle", textArticle);
					}

					else if (textArticle == "" || textArticle == null) {
						listeEnchereArticle = listeEnchereArticle.stream()
								.filter(t -> t.getCategorie().contains(categorie)).collect(Collectors.toList());
						request.setAttribute("categorie", categorie);
					}

					else {
						listeEnchereArticle = listeEnchereArticle.stream()
								.filter(t -> t.getNom_article().contains(textArticle)).collect(Collectors.toList());

						listeEnchereArticle = listeEnchereArticle.stream()
								.filter(t -> t.getCategorie().contains(categorie)).collect(Collectors.toList());
						request.setAttribute("textArticle", textArticle);
						request.setAttribute("categorie", categorie);
					}
				}
			}

		} else {
			if (categorie != null || textArticle != null) {
				if (categorie == "" || categorie == null) {
					listeEnchereArticle = enchereArticleBLL.selectJoinLike(textArticle);
					request.setAttribute("textArticle", textArticle);
				} else if (textArticle == "" || textArticle == null) {
					listeEnchereArticle = enchereArticleBLL.selectJoinCat(categorie);
					request.setAttribute("categorie", categorie);
				} else {
					listeEnchereArticle = enchereArticleBLL.selectJoinCatLike(categorie, textArticle);
					request.setAttribute("textArticle", textArticle);
					request.setAttribute("categorie", categorie);
				}
			} else {
				listeEnchereArticle = enchereArticleBLL.selectJoin();
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