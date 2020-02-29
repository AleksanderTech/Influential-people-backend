package com.alek.influentialpeople.article.comment.service;

import com.alek.influentialpeople.article.comment.entity.ArticleComment;
import com.alek.influentialpeople.article.comment.persistence.ArticleCommentRepository;
import com.alek.influentialpeople.exception.ExceptionMessages;
import com.alek.influentialpeople.user.entity.User;
import com.alek.influentialpeople.user.service.UserDataHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class TheArticleCommentService implements ArticleCommentService {

    private ArticleCommentRepository repository;
    private UserDataHolder<User> userHolder;

    public TheArticleCommentService(ArticleCommentRepository repository, UserDataHolder<User> userHolder) {
        this.repository = repository;
        this.userHolder = userHolder;
    }

    @Override
    public ArticleComment addComment(ArticleComment articleComment) {
        articleComment.setUser(new User(userHolder.getUsername()));
        return repository.save(articleComment);
    }

    @Override
    public void deleteComment(long id) {
        Optional<ArticleComment> articleComment = repository.findById(id);
        if (articleComment.isPresent()) {
            if (isAllowed(userHolder.getUsername(), articleComment.get().getUser().getUsername())) {
                repository.deleteById(id);
            } else {
                throw new AccessDeniedException(ExceptionMessages.ACCESS_DENIED_MESSAGE);
            }
        }
        throw new EntityNotFoundException(ExceptionMessages.NOT_FOUND_ARTICLE_COMMENT_MESSAGE);
    }

    @Override
    public Page<ArticleComment> findArticleComments(Pageable pageable, long articleId) {
        return repository.findByArticle(pageable, articleId);
    }

    private boolean isAllowed(String currentUser, String owner) {
        return currentUser.equals(owner) || userHolder.isUserAdmin(userHolder.getUser());
    }
}
