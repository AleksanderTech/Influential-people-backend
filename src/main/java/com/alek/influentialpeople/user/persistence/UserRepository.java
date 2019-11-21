package com.alek.influentialpeople.user.persistence;

import com.alek.influentialpeople.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, String> {


    @Query(value = "select distinct user from User user inner join fetch user.roles roles"
            , countQuery = "select count(user) from User user left join user.roles")
    Page<User> findAllUsers(Pageable pageable);

    @Query(value = "select user from User user inner join fetch user.roles roles where user.username = :username")
    User findByUsername(@Param("username") String username);


}
