package com.alek.influentialpeople.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import com.alek.influentialpeople.article.domain.Article;
import com.alek.influentialpeople.article.articleComment.ArticleComment;
import com.alek.influentialpeople.hero.HeroScore;
import com.alek.influentialpeople.quote.Quote;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private long id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String role;
    @Column(columnDefinition = "int default 0")
    private int activation;
    @Column(updatable = false, nullable = false)
    private Long created_at;
    @Column(nullable = true)
    private String profileImagePath;
    @OneToMany(mappedBy = "user")
    private List<Article> articles = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private List<Quote> quotes = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private List<ArticleComment> articleComments = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private List<HeroScore> heroScores = new ArrayList<>();

    @PrePersist
    private void onCreate() {
        created_at = new Date().toInstant().getEpochSecond();
    }

    public User() {
        super();
    }

    public User(long id) {
        super();
        this.id = id;
    }


    public User(long id, String username, String password, String email, String role, int activation, Long created_at,
                String profileImagePath) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.activation = activation;
        this.created_at = created_at;
        this.profileImagePath = profileImagePath;
    }

    @Override
    public String toString() {
        return String.format(
                "User [id=%s, username=%s, password=%s, email=%s, role=%s, activation=%s, created_at=%s, profileImagePath=%s]",
                id, username, password, email, role, activation, created_at, profileImagePath);
    }

    public User(String username) {
        this.username = username;
    }


}
