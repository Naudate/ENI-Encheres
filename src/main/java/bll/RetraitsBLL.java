package bll;

import bo.Retraits;
import dal.DAOFactory;
import dal.RetraitsDAO;

public class RetraitsBLL {
	private RetraitsDAO dao;

	public RetraitsBLL() {
		dao =  DAOFactory.getRetraitsDAO();
	}
	public Retraits insert(Retraits retraits){
    	return dao.insert(retraits);   
    }
	
}
