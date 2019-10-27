package com.alek.influentialpeople.user.model;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAccount {

    private String username;
    private String password;
    private String email;
    private boolean enabled;
    private Set<String> roles;
}
