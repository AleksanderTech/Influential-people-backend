package com.alek.influentialpeople.article.comment.entity;

import com.alek.influentialpeople.article.entity.Article;
import com.alek.influentialpeople.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArticleComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String content;
    @Column(updatable = false, nullable = false)
    private Long created_at;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", referencedColumnName = "id", nullable = false)
    private Article article;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username", referencedColumnName = "username", nullable = false)
    private User user;

    @PrePersist
    private void onCreate() {
        created_at = new Date().getTime();
    }

}
