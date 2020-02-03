package com.alek.influentialpeople.user.entity;

import com.alek.influentialpeople.common.Urls;
import com.alek.influentialpeople.user.role.entity.Role;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@ToString
@AllArgsConstructor
public class User {

    @Id
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;
    @Column(columnDefinition = "boolean default false")
    private boolean enabled;
    private String avatarImagePath;
    @Transient
    private String avatarImageUrl;
    @Column(nullable = false)
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "username", referencedColumnName = "username"),
            inverseJoinColumns = @JoinColumn(name = "role_name", referencedColumnName = "name"))
    private Set<Role> roles = new HashSet<>();

    public User(String username) {
        this.username = username;
    }

    public String buildAndSetAvatarUrl() {
        this.avatarImageUrl = Urls.ROOT_URL + Urls.USER + "/" + this.username + Urls.IMAGE;
        return this.avatarImageUrl;
    }
}
