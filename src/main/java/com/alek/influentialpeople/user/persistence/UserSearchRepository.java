package com.alek.influentialpeople.user.persistence;

import com.alek.influentialpeople.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserSearchRepository extends JpaRepository<User, String> {

    Page<User> findAll(Specification<User> specification, Pageable pageable);

    List<User> findAll(Specification<User> specification);
}
