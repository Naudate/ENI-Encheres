package dal;

/*
 * Role : donner un accès aux instances des DAO
 */
public abstract class DAOFactory {
    private static EnchereDAO enchereDAO;
    private static ArticleDAO articleDAO;

    public static EnchereDAO getEnchereDAO() {
        if (enchereDAO == null) {
            enchereDAO = new EnchereDAOJdbcImpl();
        }
        return enchereDAO;
    }
	public static ArticleDAO getArticleDAO() {
		if (articleDAO == null) {
        	articleDAO = new ArticleDAOJdbcImpl();
        }
        return articleDAO;
	}

}
