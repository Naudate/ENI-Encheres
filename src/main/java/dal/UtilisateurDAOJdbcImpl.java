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
public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {
	
	private static final String SELECTUSER = "select * from utilisateurs where (pseudo = ? and mot_de_passe = ?) OR (email = ? and mot_de_passe = ?);";
	private static final String SELECTBYPSEUDO = "select * from utilisateurs where pseudo = ?;";
	private static final String SELECTBYEMAIL = "select * from utilisateurs where email = ?;";
	private static final String SELECTBYID = "select * from utilisateurs where no_utilisateur = ?;";
	private static final String INSERT = "insert into utilisateurs(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	private static final String DELETE = "delete from utilisateurs where no_utilisateur = ?;";
	private static final String UPDATECREDIT = "update utilisateurs set credit = ? where no_utilisateur = ?;";
	private static final String UPDATE = "update UTILISATEURS set pseudo = ?, nom = ?, prenom = ?, email = ?, telephone = ?, rue = ?, code_postal = ?, ville = ?, mot_de_passe = ? where no_utilisateur = ?;";
	private static final String UPDATEWITHOUTPASSWORD = "update UTILISATEURS set pseudo = ?, nom = ?, prenom = ?, email = ?, telephone = ?, rue = ?, code_postal = ?, ville = ? where no_utilisateur = ?;";
	
	@Override
	public Utilisateur verifCompte(String pseudo, String password) {
		Utilisateur util = null;
		try (Connection cnx = ConnectionProvider.getConnection();) {
			PreparedStatement ps = cnx.prepareStatement(SELECTUSER);
			
			ps.setString(1, pseudo);			
			ps.setString(2, password);
			ps.setString(3, pseudo);
			ps.setString(4, password);
			
			ResultSet rs = ps.executeQuery();	
			
			if(rs.next())
			{
				int noUtilisateur = rs.getInt("no_utilisateur") ;
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String email = rs.getString("email");
				String telephone = rs.getString("telephone");
				String rue = rs.getString("rue");
				String code_postal = rs.getString("code_postal");
				String ville = rs.getString("ville");
				int credit = rs.getInt("credit");
				boolean administrateur = rs.getBoolean("administrateur");

				util = new Utilisateur(noUtilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, null, credit, administrateur);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return util;
	}
	
	@Override
	public boolean checkEmail(String email) {
		boolean exist = false;
		try (Connection cnx = ConnectionProvider.getConnection();) {
			PreparedStatement ps = cnx.prepareStatement(SELECTBYEMAIL);			
			ps.setString(1, email);			
			ResultSet rs = ps.executeQuery();	
			
			if(rs.next())
			{
				exist = true;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return exist;
	}

	@Override
	public boolean checkPseudo(String pseudo) {
		boolean exist = false;
		try (Connection cnx = ConnectionProvider.getConnection();) {
			PreparedStatement ps = cnx.prepareStatement(SELECTBYPSEUDO);			
			ps.setString(1, pseudo);			
			ResultSet rs = ps.executeQuery();	
			
			if(rs.next())
			{
				exist = true;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return exist;
	}

	@Override
	public Utilisateur insert(Utilisateur utilisateur) {
		try (Connection cnx = ConnectionProvider.getConnection();) {
			PreparedStatement ps = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, utilisateur.getPseudo());
			ps.setString(2, utilisateur.getNom());
			ps.setString(3, utilisateur.getPrenom());
			ps.setString(4, utilisateur.getEmail());
			ps.setString(5, utilisateur.getTelephone());
			ps.setString(6, utilisateur.getRue());
			ps.setString(7, utilisateur.getCode_postal());
			ps.setString(8, utilisateur.getVille());
			ps.setString(9, utilisateur.getMotDePasse());
			ps.setInt(10, utilisateur.getCredit());
			ps.setBoolean(11, utilisateur.isAdministrateur());
			
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next())
			{
				utilisateur.setNoUtilisateur(rs.getInt(1));
			}
			rs.close();
			ps.close();
			
			cnx.commit();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return utilisateur;
		
	}

    @Override
    public Utilisateur selectById(int id) {
    	Utilisateur util = null;
		try (Connection cnx = ConnectionProvider.getConnection();) {
			PreparedStatement ps = cnx.prepareStatement(SELECTBYID);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();	
			
			if(rs.next())
			{
				String pseudo = rs.getString("pseudo") ;
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String email = rs.getString("email");
				String telephone = rs.getString("telephone");
				String rue = rs.getString("rue");
				String code_postal = rs.getString("code_postal");
				String ville = rs.getString("ville");
				int credit = rs.getInt("credit");
				boolean administrateur = rs.getBoolean("administrateur");

				util = new Utilisateur(id, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, null, credit, administrateur);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return util;
    }

    @Override
    public boolean delete(int id) {
    	boolean delete = false;
    	try (Connection cnx = ConnectionProvider.getConnection();) {
			PreparedStatement ps = cnx.prepareStatement(DELETE);
			ps.setInt(1, id);
			
			ps.executeUpdate();		
			ps.close();
			
			cnx.commit();	
			delete = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return delete;
    }

    @Override
    public void update(Utilisateur utilisateur) {
    	try (Connection cnx = ConnectionProvider.getConnection();) {
			PreparedStatement ps = cnx.prepareStatement(UPDATE);
						
			ps.setString(1, utilisateur.getPseudo());
			ps.setString(2, utilisateur.getNom());
			ps.setString(3, utilisateur.getPrenom());
			ps.setString(4, utilisateur.getEmail());
			ps.setString(5, utilisateur.getTelephone());
			ps.setString(6, utilisateur.getRue());
			ps.setString(7, utilisateur.getCode_postal());
			ps.setString(8, utilisateur.getVille());
			ps.setString(9, utilisateur.getMotDePasse());
			ps.setInt(10, utilisateur.getNoUtilisateur());
			
			ps.executeUpdate();		
			ps.close();
			
			cnx.commit();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

	@Override
	public Utilisateur removeCredit(Utilisateur util, int proposition) {
		try (Connection cnx = ConnectionProvider.getConnection();) {
			
			PreparedStatement ps = cnx.prepareStatement(UPDATECREDIT);
			int credit = util.getCredit() - proposition;		
			
			ps.setInt(1, credit);
			ps.setInt(2, util.getNoUtilisateur());
			
			   ps.executeUpdate();	
				ps.close();
				cnx.commit();
			
			util.setCredit(credit);
			return util;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;			
	}

	@Override
	public void addCredit(Utilisateur util, int offre) {
try (Connection cnx = ConnectionProvider.getConnection();) {
			
			PreparedStatement ps = cnx.prepareStatement(UPDATECREDIT);
			int credit = util.getCredit() + offre;		
			
			ps.setInt(1, credit);
			ps.setInt(2, util.getNoUtilisateur());
			
		   ps.executeUpdate();	
			ps.close();
			cnx.commit();
			
			util.setCredit(credit);
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateWithoutPassword(Utilisateur utilisateur) {
		try (Connection cnx = ConnectionProvider.getConnection();) {
			PreparedStatement ps = cnx.prepareStatement(UPDATEWITHOUTPASSWORD);
						
			ps.setString(1, utilisateur.getPseudo());
			ps.setString(2, utilisateur.getNom());
			ps.setString(3, utilisateur.getPrenom());
			ps.setString(4, utilisateur.getEmail());
			ps.setString(5, utilisateur.getTelephone());
			ps.setString(6, utilisateur.getRue());
			ps.setString(7, utilisateur.getCode_postal());
			ps.setString(8, utilisateur.getVille());
			ps.setInt(9, utilisateur.getNoUtilisateur());
			
			ps.executeUpdate();		
			ps.close();
			
			cnx.commit();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
