package com.alek.influentialpeople.article.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleRequest {

    private String title;
    private String text;
    private String heroName;

}
