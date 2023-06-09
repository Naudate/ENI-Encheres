package bo;

public class Categorie {
	
	private int noCategorie;
	private String libelle;
	
	//CONSTRUCTEUR
	public Categorie(int noCategorie, String libelle) {
		super();
		this.noCategorie = noCategorie;
		this.libelle = libelle;
	}

	
	public Categorie() {
		// TODO Auto-generated constructor stub
	}

	public Categorie(String libelle) {
		this.libelle = libelle;
	}


	//GETTER //SETTER
	public int getNoCategorie() {
		return noCategorie;
	}

	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}


	@Override
	public String toString() {
		return "Categorie [noCategorie=" + noCategorie + ", libelle=" + libelle + "]";
	}
	
	
}
