package dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bo.Enchere;
import bo.Utilisateur;
import bo.Article;

/*
 * Implémentation des méthodes proposées par StagiaireDAO
 */
public class EnchereDAOJdbcImpl implements EnchereDAO {

    private static final String SELECTALL = "select * from encheres;";

    @Override
    public List<Enchere> selectAll() {
        List<Enchere> listeEncheres = new ArrayList<Enchere>();
        try (Connection cnx = ConnectionProvider.getConnection();) {

            PreparedStatement ps = cnx.prepareStatement(SELECTALL);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Date dateEnchere = rs.getDate("date_enchere");
                int montant = rs.getInt("montant_enchere");
                Utilisateur utilisateur = new Utilisateur();
                Article article = new Article();

                Enchere enchere = new Enchere(dateEnchere, montant, utilisateur, article);

                listeEncheres.add(enchere);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeEncheres;
    }

    @Override
    public Enchere selectById(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(int id) {
        // TODO Auto-generated method stub

    }

    @Override
    public void update(Enchere enchere) {
        // TODO Auto-generated method stub

    }

}
