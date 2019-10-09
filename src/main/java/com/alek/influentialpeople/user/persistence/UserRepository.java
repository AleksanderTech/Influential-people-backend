package com.alek.influentialpeople.user.persistence;

import com.alek.influentialpeople.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
