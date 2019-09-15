package com.alek.influentialpeople.user.role;

import lombok.*;

import javax.persistence.*;

@Entity
@Table
@Getter
@AllArgsConstructor
public class Role {

    @Id
    private Roles role;

    public enum Roles {
        USER, ADMIN
    }
}
