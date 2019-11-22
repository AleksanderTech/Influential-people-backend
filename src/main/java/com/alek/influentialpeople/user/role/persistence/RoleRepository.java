package com.alek.influentialpeople.user.role.persistence;

import com.alek.influentialpeople.user.role.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
}
