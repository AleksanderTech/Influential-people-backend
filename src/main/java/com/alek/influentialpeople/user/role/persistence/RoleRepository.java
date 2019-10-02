package com.alek.influentialpeople.user.role.persistence;

import com.alek.influentialpeople.user.role.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}