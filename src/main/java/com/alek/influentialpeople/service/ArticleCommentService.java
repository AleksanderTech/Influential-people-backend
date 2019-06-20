package com.alek.influentialpeople.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alek.influentialpeople.persistance.ArticleCommentRepository;
import com.alek.influentialpeople.persistence.entity.Article;
import com.alek.influentialpeople.persistence.entity.ArticleComment;

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
