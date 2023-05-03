package bll;

import java.util.List;

import bo.Enchere;
import dal.EnchereDAO;
import dal.DAOFactory;

public class EnchereBLL {
    private EnchereDAO dao;

    public EnchereBLL() {
		dao = DAOFactory.getEnchereDAO();
	}

    public List<Enchere> selectAll() {
        return dao.selectAll();
    }


}
