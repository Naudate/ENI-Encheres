package dal;

import java.util.List;

import bo.Article;

/*
 * Définition des méthodes utilisées auprès de la BDD
 */
public interface ArticleDAO {

    Article selectById(int id);

}
