package com.alek.influentialpeople.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alek.influentialpeople.persistance.ArticleCommentRepository;
import com.alek.influentialpeople.persistence.entity.ArticleComment;

@Service
public class ArticleCommentService {

	@Autowired	
	ArticleCommentRepository articleCommentRepository;

	public List<ArticleComment> getAllArticleComments() {
		List<ArticleComment> articleComments = new ArrayList<>();
		articleCommentRepository.findAll().forEach(articleComments::add);
		return articleComments;
	}

	public void addArticleComment(ArticleComment articleComment) {
		articleCommentRepository.save(articleComment);
	}


//	public Topic getTopicByID(String id) {
//		return topicRepository.findById(id).orElse(new Topic());
//	}
//
//	public void addTopic(Topic topic) {
//		topicRepository.save(topic);
//	}
//
//	public void updateTopic(String id, Topic topic) {
//
//		topicRepository.save(topic);
//	}
//
//	public void deleteTopic(String id) {
//		topicRepository.deleteById(id);
//	}
}
