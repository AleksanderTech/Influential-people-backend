package com.alek.influentialpeople.article.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class ArticleResponse {

    private String title;
    private String content;
    private long createdAt;

}
