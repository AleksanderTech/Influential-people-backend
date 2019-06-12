package com.alek.influentialpeople.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alek.influentialpeople.persistance.PersonRepository;
import com.alek.influentialpeople.persistance.entity.Person;

@Service
public class PersonService {

	@Autowired
	PersonRepository personRepository;

	public List<Person> getAllPersons() {
		List<Person> persons = new ArrayList<>();
		personRepository.findAll().forEach(persons::add);
		return persons;
	}

	public void addPerson(Person person) {
		personRepository.save(person);
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
