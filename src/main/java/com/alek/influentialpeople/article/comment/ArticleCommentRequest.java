package com.alek.influentialpeople.article.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ArticleCommentRequest {

    private Long articleId;
    private String content;

    public void setArticleIdIf(long articleId) {
        if (this.articleId != null) {
            this.articleId = articleId;
        }
    }

}
