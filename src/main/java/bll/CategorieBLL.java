package bll;

import java.util.List;
import bo.Categorie;
import dal.CategorieDAO;
import dal.DAOFactory;

public class CategorieBLL {
	private CategorieDAO dao;
	
	public CategorieBLL() {
	
		dao = DAOFactory.getCategorieDAO();
	}

	public List<Categorie> selectAll() {
        return dao.selectAll();
    }
}
