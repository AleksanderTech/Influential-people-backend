package com.alek.influentialpeople.article.service;

import java.util.ArrayList;
import java.util.List;

import com.alek.influentialpeople.article.domain.ArticleComment;
import com.alek.influentialpeople.article.repository.ArticleCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alek.influentialpeople.article.domain.Article;

@Service
public class ArticleCommentService {

	@Autowired
	ArticleCommentRepository articleCommentRepository;

	public List<ArticleComment> getAllArticleComments(long id) {
		List<ArticleComment> articleComments = new ArrayList<>();
		articleCommentRepository.findByArticle(new Article(id)).forEach(articleComments::add);;
		return articleComments;
	}
	
	
	public void addArticleComment(ArticleComment articleComment) {
		articleCommentRepository.save(articleComment);
	}

}