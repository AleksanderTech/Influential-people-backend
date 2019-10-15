package com.alek.influentialpeople.user.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class UserResponse {

    private String username;
    private List<String> roles;

}
