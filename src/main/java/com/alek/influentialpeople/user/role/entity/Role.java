package com.alek.influentialpeople.user.role.entity;

import com.alek.influentialpeople.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    private String name;
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<User> users;

    public enum Roles {
        ROLE_USER, ROLE_ADMIN
    }

    public Role(String name) {
        this.name = name;
    }

    public Role(Role.Roles role) {
        this.name = role.name();
    }
}
