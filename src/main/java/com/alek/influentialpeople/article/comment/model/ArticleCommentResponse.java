package com.alek.influentialpeople.article.comment.model;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleCommentResponse {

    private String content;
    private String username;
    private long created_at;

}
