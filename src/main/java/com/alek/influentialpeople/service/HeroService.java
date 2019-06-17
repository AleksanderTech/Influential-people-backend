package com.alek.influentialpeople.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alek.influentialpeople.persistance.HeroRepository;
import com.alek.influentialpeople.persistance.entity.Hero;

@Service
public class HeroService {

	@Autowired
	HeroRepository heroRepository;

	public List<Hero> getAllPersons() {
		List<Hero> persons = new ArrayList<>();
		heroRepository.findAll().forEach(persons::add);
		return persons;
	}

	public void addPerson(Hero person) {
		heroRepository.save(person);
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
