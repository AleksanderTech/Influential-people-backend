package com.alek.influentialpeople.article.comment.controller;

import com.alek.influentialpeople.article.comment.model.ArticleCommentRequest;
import com.alek.influentialpeople.article.comment.model.ArticleCommentResponse;
import com.alek.influentialpeople.article.comment.service.ArticleCommentService;
import com.alek.influentialpeople.article.comment.entity.ArticleComment;
import com.alek.influentialpeople.common.TwoWayConverter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.alek.influentialpeople.common.ConvertersFactory.ConverterType.ART_COMMENT_REQUEST_TO_ART_COMMENT;
import static com.alek.influentialpeople.common.ConvertersFactory.ConverterType.ART_COMMENT_TO_ART_COMMENT_RESPONSE;
import static com.alek.influentialpeople.common.ConvertersFactory.getConverter;

@RestController
public class ArticleCommentController {

    private final ArticleCommentService articleCommentService;

    private TwoWayConverter<ArticleComment, ArticleCommentResponse> commentResponseConverter = getConverter(ART_COMMENT_TO_ART_COMMENT_RESPONSE);
    private TwoWayConverter<ArticleCommentRequest, ArticleComment> commentRequestConverter = getConverter(ART_COMMENT_REQUEST_TO_ART_COMMENT);

    public ArticleCommentController(final ArticleCommentService articleCommentService) {
        this.articleCommentService = articleCommentService;
    }

    @RequestMapping(value = "/article/{articleId}/comment", method = RequestMethod.POST)
    public ResponseEntity<ArticleComment> addComment(@PathVariable(name = "articleId") long articleId, @RequestBody ArticleCommentRequest commentRequest) {

        commentRequest.setArticleIdIf(articleId);
        ArticleComment articleComment = commentRequestConverter.convert(commentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(articleCommentService.addComment(articleComment));
    }

    @RequestMapping(value = "/article/{articleId}/comment", method = RequestMethod.GET)
    public ResponseEntity<Page<ArticleCommentResponse>> findComments(Pageable pageable, @PathVariable(name = "articleId") long articleId) {

        return ResponseEntity.status(HttpStatus.CREATED).body(articleCommentService.findArticleComments(pageable, articleId).map(article -> commentResponseConverter.convert(article)));
    }

    @RequestMapping(value = "/article/{articleId}/comment/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteComment(@PathVariable(name = "articleId") long articleId, @RequestBody ArticleCommentRequest articleComment, @PathVariable(name = "id") long id) {

        articleCommentService.deleteComment(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
