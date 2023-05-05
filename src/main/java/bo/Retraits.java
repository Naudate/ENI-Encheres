package bo;

public class Retraits {

	private Article article;
	private String rue;
	private String codePostal;
	private String ville;
	
	//CONSTRUCTEUR
	public Retraits(Article article, String rue, String codePostal, String ville) {
		super();
		this.article = article;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}
	
	public Retraits() {
	}
	
	//GETTER //SETTER
	public Article getarticle() {
		return article;
	}

	public void setarticle(Article noArticle) {
		this.article = noArticle;
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

	@Override
	public String toString() {
		return "Retraits [article=" + article + ", rue=" + rue + ", codePostal=" + codePostal + ", ville=" + ville
				+ "]";
	}
	
	
}
