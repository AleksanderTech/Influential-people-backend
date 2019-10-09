package com.alek.influentialpeople.security.service;

import com.alek.influentialpeople.security.model.CurrentUser;
import com.alek.influentialpeople.user.entity.User;
import com.alek.influentialpeople.user.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findById(username)
                .map(user -> CurrentUser.builder().username(user.getUsername())
                        .password(user.getPassword())
                        .roles(getAuthorities(user))
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
          String[] userRoles = user.getRoles().stream().map(role -> role.getName()).toArray(String[]::new);
          Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
          return authorities;
      }
}