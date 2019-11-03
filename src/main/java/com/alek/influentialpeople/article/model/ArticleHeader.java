package com.alek.influentialpeople.article.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class ArticleHeader {

    private long id;
    private String title;
    private long createdAt;

}
