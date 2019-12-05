package com.alek.influentialpeople.article.comment.controller;

import com.alek.influentialpeople.article.comment.entity.ArticleComment;
import com.alek.influentialpeople.article.comment.model.ArticleCommentRequest;
import com.alek.influentialpeople.article.comment.model.ArticleCommentResponse;
import com.alek.influentialpeople.article.comment.service.ArticleCommentService;
import com.alek.influentialpeople.article.entity.Article;
import com.alek.influentialpeople.common.TwoWayConverter;
import com.alek.influentialpeople.exception.controller.ExceptionController;
import com.alek.influentialpeople.user.entity.User;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;

import static com.alek.influentialpeople.common.ConvertersFactory.ConverterType.ART_COMMENT_REQUEST_TO_ART_COMMENT;
import static com.alek.influentialpeople.common.ConvertersFactory.ConverterType.ART_COMMENT_TO_ART_COMMENT_RESPONSE;
import static com.alek.influentialpeople.common.ConvertersFactory.getConverter;


@RunWith(MockitoJUnitRunner.class)
public class ArticleCommentControllerTest {

    @InjectMocks
    private ArticleCommentController commentController;

    @Mock
    private ArticleCommentService commentService;

    private TwoWayConverter<ArticleComment, ArticleCommentResponse> commentResponseConverter = getConverter(ART_COMMENT_TO_ART_COMMENT_RESPONSE);
    private TwoWayConverter<ArticleCommentRequest, ArticleComment> commentRequestConverter = getConverter(ART_COMMENT_REQUEST_TO_ART_COMMENT);

    private MockMvc mockMvc;
    private Article article;
    private ArticleComment comment1;
    private ArticleComment comment2;

    @Before
    public void setUp() {

         article = new Article(1L);

        comment1 = ArticleComment.builder().content("comment1").article(article).user(new User("user1")).created_at(new Date().getTime()).build();
        comment2 = ArticleComment.builder().content("comment2").article(article).user(new User("user2")).created_at(new Date().getTime()).build();
        mockMvc = MockMvcBuilders.standaloneSetup(commentController).setControllerAdvice(new ExceptionController()).setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).build();
    }


    @Test
    public void findComments_commentsExist_returnsCommentsAndStatus200() throws Exception {

        Mockito.when(commentService.findArticleComments(Mockito.any(Pageable.class),Mockito.anyLong())).thenReturn(new PageImpl<>(Lists.list(comment1, comment2)));
        mockMvc.perform(MockMvcRequestBuilders.get("/article/"+article.getId()+"/comment")).andExpect(MockMvcResultMatchers.status().isOk());
    }

}