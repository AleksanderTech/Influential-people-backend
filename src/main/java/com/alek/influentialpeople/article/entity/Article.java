package com.alek.influentialpeople.article.entity;

import com.alek.influentialpeople.article.comment.entity.ArticleComment;
import com.alek.influentialpeople.hero.entity.Hero;
import com.alek.influentialpeople.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Table
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;
    @Column(nullable = false, unique = true)
    private String title;
    @Column(nullable = false)
    private String text;
    @Column(updatable = false, nullable = false)
    private Long createdAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hero_fullName", referencedColumnName = "name")
    private Hero hero;
    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;
    @OneToMany(mappedBy = "article")
    private List<ArticleComment> articleComments = new ArrayList<>();
    @ManyToMany
    @JoinTable(name = "favourite_user_article", joinColumns = @JoinColumn(name = "article_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "username", referencedColumnName = "username"))
    private Set<User> userFavourites;


    @PrePersist
    private void onCreate() {
        createdAt = new Date().getTime();
    }

    public Article(Long id) {
        this.id = id;
    }
}
