package com.alek.influentialpeople.quote.entity;

import com.alek.influentialpeople.hero.entity.Hero;
import com.alek.influentialpeople.user.entity.User;
import lombok.*;

import javax.persistence.*;

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
    @Column(updatable = false)
    private long id;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;
    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;
    @ManyToOne
    @JoinColumn(name = "hero_fullName", referencedColumnName = "name")
    private Hero hero;

}
