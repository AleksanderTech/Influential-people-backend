package com.alek.influentialpeople.article.articleComment;

import com.alek.influentialpeople.article.domain.Article;
import com.alek.influentialpeople.user.entity.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class ArticleComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private long id;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;
    @Column(updatable = false, nullable = false)
    private Long created_at;
    @ManyToOne
    @JoinColumn(name = "article_id", referencedColumnName = "id", nullable = false)
    private Article article;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username", nullable = false)
    private User user;

    @PrePersist
    private void onCreate() {
        created_at = new Date().toInstant().getEpochSecond();
    }

    public ArticleComment() {
        super();
    }

    public ArticleComment(long id, String content, Long created_at, Article article) {
        super();
        this.id = id;
        this.content = content;
        this.created_at = created_at;
        this.article = article;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Long created_at) {
        this.created_at = created_at;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @Override
    public String toString() {
        return String.format("ArticleComment [id=%s, content=%s, created_at=%s, article=%s]", id, content, created_at,
                article);
    }

}
