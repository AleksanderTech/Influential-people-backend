package com.alek.influentialpeople.article.model;


import lombok.*;

@Builder
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class ArticleResponse {

    private long id;
    private String title;
    private String text;
    private long createdAt;
    private String heroName;
}
