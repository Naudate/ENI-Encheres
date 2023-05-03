package bo;

import java.sql.Date;

public class Enchere {

	private Date dateEnchere;
	private int montant;
	private Utilisateur utilisateur;
	private Article article;

	public Enchere() {
	}

	public Enchere(Date dateEnchere, int montant, Utilisateur utilisateur, Article article) {
		this.dateEnchere = dateEnchere;
		this.montant = montant;
		this.utilisateur = utilisateur;
		this.article = article;
	}

	public Date getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(Date dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public int getMontant() {
		return montant;
	}

	public void setMontant(int montant) {
		this.montant = montant;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
}
