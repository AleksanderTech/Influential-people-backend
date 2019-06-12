package com.alek.influentialpeople.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alek.influentialpeople.persistance.UserRepository;
import com.alek.influentialpeople.persistance.entity.User;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		return users;
	}

	public void addUser(User user) {
		userRepository.save(user);
	}

	public void updateUser(User user) {
		userRepository.save(user);
		
	}

	public User getUser(long id) {
		return userRepository.findById(id).get();
	}

	public List<User> getUsersForId(Long id) {
		List<User>users=new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		users=users.stream().filter(e->{return e.getId()>=id;}).collect(Collectors.toList());
		return users;
	}

	public List<User> getUsersPaginated(Long start, Long size) {
		List<User>users=new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		if(!(start+size>users.size())){
		users=users.subList(safeLongToInt(start), safeLongToInt(start+size));
		}
		return users;
	}
	
	public static int safeLongToInt(long l) {
	    if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
	        throw new IllegalArgumentException
	            (l + " cannot be cast to int without changing its value.");
	    }
	    return (int) l;
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
