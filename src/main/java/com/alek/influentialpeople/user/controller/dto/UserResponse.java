package com.alek.influentialpeople.user.controller.dto;

import com.alek.influentialpeople.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserResponse {

    private String username;
    private Set<String> roles;

    public UserResponse(User user) {
        username = user.getUsername();
        roles = user.getRoles().stream().map(role -> role.getRole()).collect(Collectors.toSet());
    }
}
