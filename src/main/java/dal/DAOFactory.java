package dal;

/*
 * Role : donner un accès aux instances des DAO
 */
public abstract class DAOFactory {
    private static EnchereDAO enchereDAO;    
    private static UtilisateurDAO utilisateurDAO;
    private static EnchereArticleDAO enchereArticleDAO;

    public static EnchereDAO getEnchereDAO() {
        if (enchereDAO == null) {
            enchereDAO = new EnchereDAOJdbcImpl();
        }
        return enchereDAO;
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
