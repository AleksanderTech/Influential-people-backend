//package com.alek.influentialpeople.service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.alek.influentialpeople.persistance.ArticleCommentRepository;
//import com.alek.influentialpeople.persistance.entity.ArticleComment;
//
//@Service
//public class ArticleCommentService {
//
//	@Autowired	
//	ArticleCommentRepository biographyCommentRepository;
//
//	public List<ArticleComment> getAllBiographyComments() {
//		List<ArticleComment> biographyComments = new ArrayList<>();
//		biographyCommentRepository.findAll().forEach(biographyComments::add);
//		return biographyComments;
//	}
//
//	public void addBiographyComment(ArticleComment biographyComment) {
//		biographyCommentRepository.save(biographyComment);
//	}
//
//	// napisz metode zwracajaca liste userow uzywajac spring jpa crudrepository
//
////	public Topic getTopicByID(String id) {
////		return topicRepository.findById(id).orElse(new Topic());
////	}
////
////	public void addTopic(Topic topic) {
////		topicRepository.save(topic);
////	}
////
////	public void updateTopic(String id, Topic topic) {
////
////		topicRepository.save(topic);
////	}
////
////	public void deleteTopic(String id) {
////		topicRepository.deleteById(id);
////	}
//}
