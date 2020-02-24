package com.alek.influentialpeople.user.model;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private String username;
    private String avatarImageUrl;
    private String email;
    private boolean enabled;
    private Set<String> roles;
}
