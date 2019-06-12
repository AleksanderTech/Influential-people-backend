package com.alek.influentialpeople.persistance;

import org.springframework.data.repository.CrudRepository;

import com.alek.influentialpeople.persistance.entity.BiographyComment;

public interface BiographyCommentRepository extends CrudRepository<BiographyComment, Long>{

}
