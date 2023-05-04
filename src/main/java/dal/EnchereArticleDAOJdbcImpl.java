package dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bo.EnchereArticle;

public class EnchereArticleDAOJdbcImpl implements EnchereArticleDAO {
	private static final String SELECTJOIN ="select a.no_article,a.nom_article,e.montant_enchere,a.prix_initial,a.date_fin_enchere,a.etat_vente,u.pseudo from ARTICLES_VENDUS a left join ENCHERES e on a.no_article = e.no_article inner join UTILISATEURS u on a.no_utilisateur = u.no_utilisateur WHERE a.etat_vente = 'EC'";

	@Override
	public List<EnchereArticle> selectJoin() {
		List<EnchereArticle> list = new ArrayList<EnchereArticle>();
		try (Connection cnx = ConnectionProvider.getConnection();){
			PreparedStatement ps = cnx.prepareStatement(SELECTJOIN);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Integer no_article = rs.getInt("no_article");
				String nom_article = rs.getString("nom_article");
				Integer prix_initial = rs.getInt("prix_initial");
				Date date_fin_enchere = rs.getDate("date_fin_enchere");
				String etat_vente = rs.getString("etat_vente");
				String pseudo = rs.getString("pseudo");
				Integer montant_enchere = rs.getInt("montant_enchere");
				
				EnchereArticle ea = new EnchereArticle(no_article,nom_article, prix_initial, date_fin_enchere, etat_vente, pseudo, montant_enchere);
				
				list.add(ea);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
