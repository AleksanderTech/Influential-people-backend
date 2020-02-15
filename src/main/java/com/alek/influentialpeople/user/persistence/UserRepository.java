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

public interface UserRepository extends JpaRepository<User, String> {


    @Query(value = "select distinct user from User user inner join fetch user.roles roles"
            , countQuery = "select count(user) from User user left join user.roles")
    Page<User> findAllUsers(Pageable pageable);

    @Query(value = "select user from User user inner join fetch user.roles roles where user.username = :username")
    User findByUsername(@Param("username") String username);


    @Query(value = "select avatar_image_path from user where username = :username", nativeQuery = true)
    String findAvatarPath(@Param("username") String username);

    @Transactional
    @Modifying
    @Query(value = "update user set user.avatar_image_path = :path where user.username = :username", nativeQuery = true)
    void updateImagePath(@Param("path") String path, @Param("username") String username);

    Page<User> findAll(Specification<User> specification, Pageable pageable);

    List<User> findAll(Specification<User> specification);
}
