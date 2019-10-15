package com.alek.influentialpeople.security.model;

import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistration {

    private String username;
    private String password;
    private String email;

}
