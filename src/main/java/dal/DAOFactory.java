package dal;

/*
 * Role : donner un accès aux instances des DAO
 */
public abstract class DAOFactory {
    private static EnchereDAO enchereDAO;    
    private static UtilisateurDAO utilisateurDAO;

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

}