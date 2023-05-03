package bo;

import java.sql.Date;

public class Article {

	private int noArticle; // article
	private String nomArticle;
	private String description;
	private Date datDebutEnchere;
	private Date dateFinEnchere;
	private int prixInitial;
	private int prixVente;
	private Utilisateur noUtilisateur;
	private Categorie noCategorie;
	private char etatVente;
	private String image;

	public Article() {
	}

	// CONSTRUCTEUR AVEC TOUT LES ATTRIBUTS
	public Article(int noArticle, String nomArticle, String description, Date datDebutEnchere, Date dateFinEnchere,
			int prixInitial, int prixVente, Utilisateur noUtilisateur, Categorie noCategorie, char etatVente,
			String image) {
		super();
		this.setNoArticle(noArticle);
		this.nomArticle = nomArticle;
		this.description = description;
		this.datDebutEnchere = datDebutEnchere;
		this.dateFinEnchere = dateFinEnchere;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.noUtilisateur = noUtilisateur;
		this.noCategorie = noCategorie;
		this.etatVente = etatVente;
		this.image = image;
	}

	// GETTERs/SETTERs
	public int getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}

	public String getNomArticle() {
		return nomArticle;
	}

	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDatDebutEnchere() {
		return datDebutEnchere;
	}

	public void setDatDebutEnchere(Date datDebutEnchere) {
		this.datDebutEnchere = datDebutEnchere;
	}

	public Date getDateFinEnchere() {
		return dateFinEnchere;
	}

	public void setDateFinEnchere(Date dateFinEnchere) {
		this.dateFinEnchere = dateFinEnchere;
	}

	public int getPrixInitial() {
		return prixInitial;
	}

	public void setPrixInitial(int prixInitial) {
		this.prixInitial = prixInitial;
	}

	public int getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}

	public Utilisateur getNoUtilisateur() {
		return noUtilisateur;
	}

	public void setNoUtilisateur(Utilisateur noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}

	public Categorie getNoCategorie() {
		return noCategorie;
	}

	public void setNoCategorie(Categorie noCategorie) {
		this.noCategorie = noCategorie;
	}

	public char getEtatVente() {
		return etatVente;
	}

	public void setEtatVente(char etatVente) {
		this.etatVente = etatVente;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
