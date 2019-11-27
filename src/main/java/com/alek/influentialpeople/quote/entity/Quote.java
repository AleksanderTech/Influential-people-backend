package com.alek.influentialpeople.quote.entity;

import com.alek.influentialpeople.hero.entity.Hero;
import com.alek.influentialpeople.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
@AllArgsConstructor
@Builder
@Setter
@Getter
@NoArgsConstructor
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String content;
    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;
    @ManyToMany
    @JoinTable(name = "favourite_user_quote", joinColumns = @JoinColumn(name = "quote_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "username", referencedColumnName = "username"))
    private Set<User> userFavourites;
    @ManyToOne
    @JoinColumn(name = "hero_name", referencedColumnName = "name")
    private Hero hero;

}
