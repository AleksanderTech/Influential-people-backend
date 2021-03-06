package com.alek.influentialpeople.article.comment.model;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleCommentResponse {

    private long id;
    private String content;
    private String username;
    private String avatarUrl;
    private long createdAt;

}
