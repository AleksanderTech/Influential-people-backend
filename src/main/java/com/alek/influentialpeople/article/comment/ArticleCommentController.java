package com.alek.influentialpeople.article.comment;

import com.alek.influentialpeople.common.TwoWayConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.alek.influentialpeople.common.ConvertersFactory.ConverterType.ART_COMMENT_REQUEST_TO_ART_COMMENT;
import static com.alek.influentialpeople.common.ConvertersFactory.getConverter;

@RestController
public class ArticleCommentController {

    private final ArticleCommentService articleCommentService;

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

    @RequestMapping(value = "/article/{articleId}/comment/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteComment(@PathVariable(name = "articleId") long articleId, @RequestBody ArticleCommentRequest articleComment, @RequestParam(name = "id") long id) {

        articleCommentService.deleteComment(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
