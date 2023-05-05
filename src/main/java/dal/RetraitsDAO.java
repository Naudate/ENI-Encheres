package dal;

import java.util.List;

import bo.Article;
import bo.Retraits;

public interface RetraitsDAO {
	List<Retraits> selectAll();
    Retraits selectById(int id);
    Retraits insert(Article article);
}
