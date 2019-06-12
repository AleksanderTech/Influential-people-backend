package com.alek.influentialpeople.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alek.influentialpeople.persistance.BiographyRepository;
import com.alek.influentialpeople.persistance.entity.Biography;

@Service
public class BiographyService {

	@Autowired
	BiographyRepository biographyRepository;

	public List<Biography> getAllBiographies() {
		List<Biography> biographies = new ArrayList<>();
		biographyRepository.findAll().forEach(biographies::add);
		return biographies;
	}

	public void addBiography(Biography biography) {
		biographyRepository.save(biography);
	}

	// napisz metode zwracajaca liste userow uzywajac spring jpa crudrepository

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
