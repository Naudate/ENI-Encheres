package dal;

/*
 * Role : donner un accès aux instances des DAO
 */
public abstract class DAOFactory {
    private ArticleDAO articleDAO;    
    private UtilisateurDAO utilisateurDAO;
    private EnchereArticleDAO enchereArticleDAO;

	public ArticleDAO getArticleDAO() {
		if (articleDAO == null) {
        	articleDAO = new ArticleDAOJdbcImpl();
        }
        return articleDAO;
	}       
    public UtilisateurDAO getUtilisateurDAO() {
        if (utilisateurDAO == null) {
        	utilisateurDAO = new UtilisateurDAOJdbcImpl();
        }
        return utilisateurDAO;
    }
    
    public EnchereArticleDAO getEnchereArticleDAO() {
		if (enchereArticleDAO == null) {
			enchereArticleDAO = new EnchereArticleDAOJdbcImpl();
		}
    	return enchereArticleDAO;
    }
}
