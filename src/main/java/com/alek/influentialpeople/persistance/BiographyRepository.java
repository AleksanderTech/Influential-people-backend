package com.alek.influentialpeople.persistance;

import org.springframework.data.repository.CrudRepository;

import com.alek.influentialpeople.persistance.entity.Biography;

public interface BiographyRepository extends CrudRepository<Biography, Long>{

}
