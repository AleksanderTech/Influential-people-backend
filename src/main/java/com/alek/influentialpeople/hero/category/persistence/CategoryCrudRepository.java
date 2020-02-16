package com.alek.influentialpeople.hero.category.persistence;

import com.alek.influentialpeople.hero.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryCrudRepository extends JpaRepository<Category,String> {

    List<Category> findByNameIn(List<String> categories);
}
