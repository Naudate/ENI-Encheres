package bo;

import java.sql.Date;

public class EnchereArticle {
	private Integer no_article;
	private String nom_article;
	private Integer prix_initial;
	private Date date_fin_enchere;
	private String etat_vente;
	private String pseudo;
	private Integer montant_enchere;
	
	public EnchereArticle() { 
		
	}

	public EnchereArticle(Integer no_article,String nom_article, Integer prix_initial, Date date_fin_enchere, String etat_vente,
			String pseudo, Integer montant_enchere) {
		this.setNo_article(no_article);
		this.nom_article = nom_article;
		this.prix_initial = prix_initial;
		this.date_fin_enchere = date_fin_enchere;
		this.etat_vente = etat_vente;
		this.pseudo = pseudo;
		this.montant_enchere = montant_enchere;
	}

	public String getNom_article() {
		return nom_article;
	}

	public void setNom_article(String nom_article) {
		this.nom_article = nom_article;
	}

	public Integer getPrix_initial() {
		return prix_initial;
	}

	public void setPrix_initial(Integer prix_initial) {
		this.prix_initial = prix_initial;
	}

	public Date getDate_fin_enchere() {
		return date_fin_enchere;
	}

	public void setDate_fin_enchere(Date date_fin_enchere) {
		this.date_fin_enchere = date_fin_enchere;
	}

	public String getEtat_vente() {
		return etat_vente;
	}

	public void setEtat_vente(String etat_vente) {
		this.etat_vente = etat_vente;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public Integer getMontant_enchere() {
		return montant_enchere;
	}

	public void setMontant_enchere(Integer montant_enchere) {
		this.montant_enchere = montant_enchere;
	}

	public Integer getNo_article() {
		return no_article;
	}

	public void setNo_article(Integer no_article) {
		this.no_article = no_article;
	}
	
	
	
}
