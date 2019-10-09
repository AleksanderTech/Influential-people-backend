package com.alek.influentialpeople.user.model;

import com.alek.influentialpeople.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserResponse {

    private String username;
    private List<String> roles;

    public UserResponse(User user) {
        username = user.getUsername();
        roles = user.getRoles().stream().map(role -> role.getName()).collect(Collectors.toList());
    }
}
