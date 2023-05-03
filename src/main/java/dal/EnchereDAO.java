package dal;

import java.util.List;

import bo.Enchere;

/*
 * Définition des méthodes utilisées auprès de la BDD
 */
public interface EnchereDAO {

    List<Enchere> selectAll();

    Enchere selectById(int id);

    void delete(int id);

    void update(Enchere enchere);
}
