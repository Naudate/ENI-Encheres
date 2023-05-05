package dal;

/*
 * Role : donner un accès aux instances des DAO
 */
public abstract class DAOFactory {
    private static ArticleDAO articleDAO;    
    private static UtilisateurDAO utilisateurDAO;
    private static EnchereArticleDAO enchereArticleDAO;
    private static CategorieDAO categorieDAO;
    private static EnchereDAO enchereDAO;

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

    public static CategorieDAO getCategorieDAO() {
		if (categorieDAO == null) {
			categorieDAO = new CategorieJdbcImpl();
		}
    	return categorieDAO;
    
    public static EnchereDAO getEnchereDAO() {
		if (enchereDAO == null) {
			enchereDAO = new EnchereDAOJdbcImpl();
		}
    	return enchereDAO;

    }
}

