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
	private Utilisateur utilisateur;
	private Categorie categorie;
	private String etatVente;
	private String image;
	private Retraits retrait;

	public Article() {
	}

	// CONSTRUCTEUR AVEC TOUT LES ATTRIBUTS
	public Article(int noArticle, String nomArticle, String description, Date datDebutEnchere, Date dateFinEnchere,
			int prixInitial, int prixVente, Utilisateur utilisateur, Categorie categorie, String etatVente,
			String image, Retraits retrait) {
		super();
		this.setNoArticle(noArticle);
		this.nomArticle = nomArticle;
		this.description = description;
		this.datDebutEnchere = datDebutEnchere;
		this.dateFinEnchere = dateFinEnchere;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.utilisateur = utilisateur;
		this.categorie = categorie;
		this.etatVente = etatVente;
		this.image = image;
		this.retrait = retrait;
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

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public String getEtatVente() {
		return etatVente;
	}

	public void setEtatVente(String etatVente) {
		this.etatVente = etatVente;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Retraits getRetrait() {
		return retrait;
	}

	public void setRetrait(Retraits retrait) {
		this.retrait = retrait;
	}
	
}
