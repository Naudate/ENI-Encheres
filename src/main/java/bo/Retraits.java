package bo;

public class Retraits {

	private Article noArticle;
	private String rue;
	private String codePostal;
	private String ville;
	
	//CONSTRUCTEUR
	public Retraits(Article noArticle, String rue, String codePostal, String ville) {
		super();
		this.noArticle = noArticle;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}

	
	
	//GETTER //SETTER
	public Article getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(Article noArticle) {
		this.noArticle = noArticle;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}
	
	
}
