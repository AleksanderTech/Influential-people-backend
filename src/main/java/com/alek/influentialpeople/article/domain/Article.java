package com.alek.influentialpeople.article.domain;

import com.alek.influentialpeople.article.model.ArticleHeader;
import com.alek.influentialpeople.hero.entity.Hero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Table
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;
    @Column(nullable = false, unique = true)
    private String title;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;
    @Column(updatable = false, nullable = false)
    private Long created_at;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hero_fullName", referencedColumnName = "fullName")
    private Hero hero;
//    @ManyToOne
//    @JoinColumn(name = "username", referencedColumnName = "username")
//    private User user;
//    @OneToMany(mappedBy = "article")
//    private List<ArticleComment> articleComments = new ArrayList<>();

    @PrePersist
    private void onCreate() {
        created_at = new Date().toInstant().getEpochSecond();
    }

    public Article() {
        super();
    }

    public Article(Long id, String title, String content, Long created_at) {
        super();
        this.id = id;
        this.title = title;
        this.content = content;
        this.created_at = created_at;
//        this.hero = person;
    }

    public Article(Long id) {
        super();
        this.id = id;
    }

    public ArticleHeader toArticleResponse() {
        return ArticleHeader.builder().id(this.id).title(this.title).createdAt(this.created_at).build();
    }

}
