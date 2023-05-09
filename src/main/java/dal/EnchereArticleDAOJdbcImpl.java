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
	private static final String SELECTJOINALL ="select a.no_article,"
													+ "a.nom_article,"
													+ "e.montant_enchere,"
													+ "a.prix_initial,"
													+ "a.date_fin_enchere,"
													+ "a.etat_vente,"
													+ "u.pseudo "
											+ "from ARTICLES_VENDUS a "
												+ "left join ENCHERES e on a.no_article = e.no_article "
												+ "inner join UTILISATEURS u on a.no_utilisateur = u.no_utilisateur "
											+ "WHERE a.etat_vente = 'EC'";
	private static final String SELECTJOINCAT = "select c.libelle,"
													+ "a.no_article,"
													+ "a.nom_article,"
													+ "e.montant_enchere,"
													+ "a.prix_initial,"
													+ "a.date_fin_enchere,"
													+ "a.etat_vente,"
													+ "u.pseudo "
												+ "from ARTICLES_VENDUS a "
													+ "left join ENCHERES e on a.no_article = e.no_article "
													+ "inner join UTILISATEURS u on a.no_utilisateur = u.no_utilisateur "
													+ "inner join CATEGORIES c on a.no_categorie=c.no_categorie "
												+ "WHERE a.etat_vente = 'EC' AND c.libelle = ?";
	private static final String SELECTJOINLIKE ="select a.no_article,"
													+ "a.nom_article,"
													+ "e.montant_enchere,"
													+ "a.prix_initial,"
													+ "a.date_fin_enchere,"
													+ "a.etat_vente,"
													+ "u.pseudo "
												+ "from ARTICLES_VENDUS a "
													+ "left join ENCHERES e on a.no_article = e.no_article "
													+ "inner join UTILISATEURS u on a.no_utilisateur = u.no_utilisateur "
												+ "WHERE a.etat_vente = 'EC' AND a.nom_article like ?";
	private static final String SELECTJOINCATLIKE = "select c.libelle,"
														+ "a.no_article,"
														+ "a.nom_article,"
														+ "e.montant_enchere,"
														+ "a.prix_initial,"
														+ "a.date_fin_enchere,"
														+ "a.etat_vente,"
														+ "u.pseudo "
													+ "from ARTICLES_VENDUS a "
														+ "left join ENCHERES e on a.no_article = e.no_article "
														+ "inner join UTILISATEURS u on a.no_utilisateur = u.no_utilisateur "
														+ "inner join CATEGORIES c on a.no_categorie=c.no_categorie "
													+ "WHERE a.etat_vente = 'EC' and c.libelle = ? AND a.nom_article like ? ";

	
	@Override
	public List<EnchereArticle> selectJoin() {
		List<EnchereArticle> list = new ArrayList<EnchereArticle>();
		try (Connection cnx = ConnectionProvider.getConnection();){
			PreparedStatement ps = cnx.prepareStatement(SELECTJOINALL);
			
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
	
	@Override
	public List<EnchereArticle> selectJoinCat(String categoriesql){
		List<EnchereArticle> list = new ArrayList<EnchereArticle>();
		try (Connection cnx = ConnectionProvider.getConnection();){
			cnx.setAutoCommit(false);
			PreparedStatement ps;

			ps = cnx.prepareStatement(SELECTJOINCAT);
			ps.setString(1, categoriesql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String categorie = rs.getString("libelle");
				Integer no_article = rs.getInt("no_article");
				String nom_article = rs.getString("nom_article");
				Integer prix_initial = rs.getInt("prix_initial");
				Date date_fin_enchere = rs.getDate("date_fin_enchere");
				String etat_vente = rs.getString("etat_vente");
				String pseudo = rs.getString("pseudo");
				Integer montant_enchere = rs.getInt("montant_enchere");
				
				EnchereArticle ea = new EnchereArticle(categorie,no_article,nom_article, prix_initial, date_fin_enchere, etat_vente, pseudo, montant_enchere);
				
				list.add(ea);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public List<EnchereArticle> selectJoinLike(String nom_articlelike){
		List<EnchereArticle> list = new ArrayList<EnchereArticle>();
		try (Connection cnx = ConnectionProvider.getConnection();){
			cnx.setAutoCommit(false);
			PreparedStatement ps;
			
			ps = cnx.prepareStatement(SELECTJOINLIKE);
			ps.setString(1, "%" + nom_articlelike + "%");
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
	@Override
	public List<EnchereArticle> selectJoinCatLike(String categoriesql, String nom_articlelike){
		List<EnchereArticle> list = new ArrayList<EnchereArticle>();
		try (Connection cnx = ConnectionProvider.getConnection();){
			cnx.setAutoCommit(false);
			PreparedStatement ps;

			ps = cnx.prepareStatement(SELECTJOINCATLIKE);
			ps.setString(1, categoriesql);
			ps.setString(2, "%" + nom_articlelike + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String categorie = rs.getString("libelle");
				Integer no_article = rs.getInt("no_article");
				String nom_article = rs.getString("nom_article");
				Integer prix_initial = rs.getInt("prix_initial");
				Date date_fin_enchere = rs.getDate("date_fin_enchere");
				String etat_vente = rs.getString("etat_vente");
				String pseudo = rs.getString("pseudo");
				Integer montant_enchere = rs.getInt("montant_enchere");
				
				EnchereArticle ea = new EnchereArticle(categorie,no_article,nom_article, prix_initial, date_fin_enchere, etat_vente, pseudo, montant_enchere);
				
				list.add(ea);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
