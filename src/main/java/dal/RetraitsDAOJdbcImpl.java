package dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import bo.Retraits;
import bo.Utilisateur;

public class RetraitsDAOJdbcImpl implements RetraitsDAO {
	public ArticleDAOJdbcImpl article;
	private static final String INSERT = "INSERT INTO RETRAITS VALUES( ?,?,?,?)";
	@Override
	public Retraits insert(Retraits retraits) {
		/// TODO Auto-generated method stub
		 try (Connection cnx = ConnectionProvider.getConnection();) { 
			  PreparedStatement ps = cnx.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			  ps.setInt(1,retraits.getarticle().getNoArticle());
			  ps.setString(2,retraits.getRue());
			  ps.setString(3,retraits.getCodePostal());
			  ps.setString(4, retraits.getVille());
			  
			  ps.executeUpdate();
	    }
	    catch(Exception e){ 
	      e.printStackTrace();
	    }
		return retraits;
	}
   
}
