package com.alek.influentialpeople.security.model;


import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import java.util.Collection;

@Builder
public class CurrentUser implements UserDetails {

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private boolean isEnabled;

    @NotNull
    private Collection<? extends GrantedAuthority> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
