package dal;

/*
 * Role : donner un accès aux instances des DAO
 */
public abstract class DAOFactory {    
    private static UtilisateurDAO utilisateurDAO;
    private static EnchereArticleDAO enchereArticleDAO;
    private static ArticleDAO articleDAO;
    private static EnchereDAO enchereDAO;

    
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
    
    public static ArticleDAO getArticleDAO() {
    	if (articleDAO == null) {
			articleDAO = new ArticleDAOJdbcImpl();
		}
    	return articleDAO;
    }
    
    public static EnchereDAO getEnchereDAO() {
    	if (enchereDAO == null) {
    		enchereDAO = new EnchereDAOJdbcImpl();
		}
    	return enchereDAO;
    }

}
