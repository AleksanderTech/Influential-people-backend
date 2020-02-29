package com.alek.influentialpeople.article.comment.controller;

import com.alek.influentialpeople.article.comment.entity.ArticleComment;
import com.alek.influentialpeople.article.comment.model.ArticleCommentRequest;
import com.alek.influentialpeople.article.comment.model.ArticleCommentResponse;
import com.alek.influentialpeople.article.comment.service.ArtCommentRequestConverter;
import com.alek.influentialpeople.article.comment.service.ArtCommentResponseConverter;
import com.alek.influentialpeople.article.comment.service.ArticleCommentService;
import com.alek.influentialpeople.common.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
public class ArticleCommentController {

    private TwoWayConverter<ArticleComment, ArticleCommentResponse> commentResponseConverter = new ArtCommentResponseConverter();
    private TwoWayConverter<ArticleCommentRequest, ArticleComment> commentRequestConverter = new ArtCommentRequestConverter();

    private final Properties properties;
    private final ArticleCommentService articleCommentService;

    public ArticleCommentController(Properties properties, ArticleCommentService articleCommentService) {
        this.properties = properties;
        this.articleCommentService = articleCommentService;
    }

    @RequestMapping(value = "/article/{articleId}/comment", method = RequestMethod.POST)
    public ResponseEntity<ArticleCommentResponse> addComment(@PathVariable(name = "articleId") long articleId, @RequestBody ArticleCommentRequest commentRequest) {
        commentRequest.setArticleIdIf(articleId);
        ArticleComment articleComment = commentRequestConverter.convert(commentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentResponseConverter.convert(articleCommentService.addComment(articleComment)));
    }

    @RequestMapping(value = "/article/{articleId}/comment", method = RequestMethod.GET)
    public ResponseEntity<Page<ArticleCommentResponse>> findComments(Pageable pageable, @PathVariable(name = "articleId") long articleId) {
        return ResponseEntity.status(HttpStatus.OK).body(articleCommentService.findArticleComments(pageable, articleId).map(articleCom -> {
            ArticleCommentResponse response = commentResponseConverter.convert(articleCom);
            response.setAvatarUrl(ImageManager.createUrl(articleCom.getUser().getAvatarImagePath(), properties.getConfig("server.url"), ImageType.USER, response.getUsername()));
            return response;
        }));
    }

    @RequestMapping(value = "/article/{articleId}/comment/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteComment(@PathVariable(name = "articleId") long articleId, @PathVariable(name = "id") long id) {
        articleCommentService.deleteComment(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
