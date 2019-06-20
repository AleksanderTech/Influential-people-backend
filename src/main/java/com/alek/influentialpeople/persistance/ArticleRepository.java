package com.alek.influentialpeople.persistance;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.alek.influentialpeople.persistence.entity.Article;
import com.alek.influentialpeople.persistence.entity.Hero;
import com.alek.influentialpeople.persistence.entity.User;

public interface ArticleRepository extends CrudRepository<Article, Long>{

	List<Article>findByHero(Hero hero);

	List<Article> findByUser(User user);
	
}
