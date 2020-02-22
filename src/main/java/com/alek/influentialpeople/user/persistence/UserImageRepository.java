package com.alek.influentialpeople.user.persistence;

import com.alek.influentialpeople.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserImageRepository extends JpaRepository<User, String> {

    @Query(value = "select avatar_image_path from user where username = :username", nativeQuery = true)
    String findAvatarPath(@Param("username") String username);

    @Transactional
    @Modifying
    @Query(value = "update user set user.avatar_image_path = :path where user.username = :username", nativeQuery = true)
    void updateImagePath(@Param("path") String path, @Param("username") String username);
}
