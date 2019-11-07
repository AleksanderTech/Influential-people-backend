package com.alek.influentialpeople.hero.category.persistence;

import com.alek.influentialpeople.hero.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,String> {


}
