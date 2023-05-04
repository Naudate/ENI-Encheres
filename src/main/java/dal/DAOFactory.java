package dal;

/*
 * Role : donner un accès aux instances des DAO
 */
public abstract class DAOFactory {
    private static ArticleDAO articleDAO;    
    private static UtilisateurDAO utilisateurDAO;
    private static EnchereArticleDAO enchereArticleDAO;


	public static ArticleDAO getArticleDAO() {
		if (articleDAO == null) {
        	articleDAO = new ArticleDAOJdbcImpl();
        }
        return articleDAO;
	}       
    public static UtilisateurDAO getUtilisateurDAO() {
        if (utilisateurDAO == null) {
        	utilisateurDAO = new UtilisateurDAOJdbcImpl();
        }
        return utilisateurDAO;
    }
    
    public static EnchereArticleDAO getEnchereArticleDAO() {
		if (enchereArticleDAO == null) {
			enchereArticleDAO = new EnchereArticleDAOJdbcImpl();
		}
    	return enchereArticleDAO;
    }
}
