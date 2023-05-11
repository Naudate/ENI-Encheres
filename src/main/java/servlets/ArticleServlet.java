package servlets;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import bll.ArticleBLL;
import bll.CategorieBLL;
import bll.RetraitsBLL;
import bo.Article;
import bo.Categorie;
import bo.Image;
import bo.Retraits;
import bo.Utilisateur;
import dal.ImageDAOJdbc;

import Exception.ArticleException;

@WebServlet("/articles")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
public class ArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String SAVE_DIRECTORY = "uploads";

	ArticleBLL articleBll;
	RetraitsBLL retraitBll;
	CategorieBLL categorieBll;

	@Override
	public void init() throws ServletException {
		articleBll = new ArticleBLL();
		categorieBll = new CategorieBLL();
		retraitBll = new RetraitsBLL();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("connected");
		
		// Récupération de tous les champs Articles
					String nomArticle = request.getParameter("nom_article");
					String description = request.getParameter("description");
					String dateDebutEnchere = request.getParameter("date_debut_enchere");
					String dateFinEnchere = request.getParameter("date_fin_enchere");
					String prixInitial = request.getParameter("prix_initial");
					Categorie categorie = new Categorie(Integer.parseInt(request.getParameter("listCategorie")), null);
					// String image = request.getParameter("image");
					// Récupération de tous les champs Retraits
					String rue = request.getParameter("rue");
					String codePostal = request.getParameter("ville");
					String ville = request.getParameter("code_postal");

		try {

			if (!utilisateur.isActif()) {
				throw new ArticleException("Votre compte est désactivé. Vous ne pouvez pas faire de vente.");
			}

			

			// convert String to LocalDate
			LocalDate date1 = LocalDate.parse(dateDebutEnchere);
			LocalDate date2 = LocalDate.parse(dateFinEnchere);
			int prixInt = Integer.parseInt(prixInitial);

			// Insertion Article
			Article articleInsert = new Article(nomArticle, description, date1, date2, prixInt, 0, utilisateur,
					categorie, "CR", null, null, null);
			Article article = articleBll.insert(articleInsert);
			// Insertion Retraits
			Retraits retraitsInsert = new Retraits(article, rue, codePostal, ville);
			Retraits retraits = retraitBll.insert(retraitsInsert);

			// Ajout de l'image
			Image image = null;

			// Gets absolute path to root directory of web app.
			String appPath = request.getServletContext().getRealPath("");

			// Gets image informations
			Part part = request.getPart("pictureFile");

			// Save image File and get fileName
			String fileName = saveFile(appPath, part);

			// save Image in database
			image = new Image(fileName, article);
			ImageDAOJdbc daoImage = new ImageDAOJdbc();
			image = daoImage.insert(image);

			// Ajout des assocaition
			article.setRetrait(retraits);
			article.setImage(image);

		} catch (ArticleException e) {		
			request.setAttribute("nomArticle", nomArticle);
			request.setAttribute("description", description);
			request.setAttribute("dateDebutEnchere", dateDebutEnchere);
			request.setAttribute("dateFinEnchere", dateFinEnchere);
			request.setAttribute("prixInitial", prixInitial);
			//request.setAttribute("listCategorie", categorie.getNoCategorie());
			request.setAttribute("rueText", rue);
			request.setAttribute("villeText", codePostal);
			request.setAttribute("codeText", ville);		
			List<Categorie> listCategorie = categorieBll.selectAll();
			request.setAttribute("listCategorie", listCategorie);
			request.setAttribute("messageError", e.getMessage());
			request.getRequestDispatcher("/AjoutArticle.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("accueil");
	}

	/**
	 * Sauvegarder le fichier image
	 * 
	 * @param appPath
	 * @param part
	 * @return
	 * @throws IOException
	 */
	private String saveFile(String appPath, Part part) throws IOException {
		appPath = appPath.replace('\\', '/');

		// The directory to save uploaded file
		String fullSavePath = null;
		if (appPath.endsWith("/")) {
			fullSavePath = appPath + SAVE_DIRECTORY;
		} else {
			fullSavePath = appPath + "/" + SAVE_DIRECTORY;
		}

		// Creates the save directory if it does not exists
		File fileSaveDir = new File(fullSavePath);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}

		String filePath = null;

		String fileName = extractFileName(part);
		String[] fn = fileName.split("(\\.)");
		fileName = fn[0];
		String ext = fn[(fn.length - 1)];
		if (!ext.isEmpty()) {
			// generate a unique file name
			UUID uuid = UUID.randomUUID();
			fileName = fileName + "_" + uuid.toString() + "." + ext;
			if (fileName != null && fileName.length() > 0) {
				filePath = fullSavePath + File.separator + fileName;
				// Write to file
				part.write(filePath);
			}
		}
		return fileName;
	}

	/**
	 * extraire le nom du fichier provenant du client
	 * 
	 * @param part
	 * @return
	 */
	private String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				String clientFileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
				clientFileName = clientFileName.replace("\\", "/");
				int i = clientFileName.lastIndexOf('/');
				return clientFileName.substring(i + 1);
			}
		}
		return null;
	}

}