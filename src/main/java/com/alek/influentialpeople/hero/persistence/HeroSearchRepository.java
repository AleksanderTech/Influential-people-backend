package com.alek.influentialpeople.hero.persistence;

import com.alek.influentialpeople.hero.entity.Hero;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeroSearchRepository extends JpaRepository<Hero, String>, JpaSpecificationExecutor<Hero> {

    List<Hero> findAll(Specification<Hero> specification);

    Page<Hero> findAll(Specification<Hero> specification, Pageable pageable);
}
