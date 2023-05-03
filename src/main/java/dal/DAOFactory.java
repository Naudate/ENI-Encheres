package dal;

/*
 * Role : donner un accès aux instances des DAO
 */
public abstract class DAOFactory {
    private static EnchereDAO enchereDAO;

    public static EnchereDAO getEnchereDAO() {
        if (enchereDAO == null) {
            enchereDAO = new EnchereDAOJdbcImpl();
        }
        return enchereDAO;
    }

}
