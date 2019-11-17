package com.alek.influentialpeople.article.entity;

import com.alek.influentialpeople.hero.entity.Hero;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

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
    @Column(nullable = false, columnDefinition = "TEXT")
    private String text;
    @Column(updatable = false, nullable = false)
    private Long created_at;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hero_fullName", referencedColumnName = "name")
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





}
