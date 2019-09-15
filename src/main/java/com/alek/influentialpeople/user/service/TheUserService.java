package com.alek.influentialpeople.user.service;

import com.alek.influentialpeople.user.domain.User;
import com.alek.influentialpeople.user.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheUserService implements UserService {

	private static final String LONG_TO_INT_MESSAGE=" cannot be cast to int without changing its value.";

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		return users;
	}

	@Override
	public User getUserByName(String name){
	return userRepository.findById(name).get();
	}

	@Override
	public void addUser(User user) {
		userRepository.save(user);
	}

	@Override
	public void updateUser(User user) {
		userRepository.save(user);
		
	}



	@Override
	public List<User> getUsersForId(Long id) {
		List<User>users=new ArrayList<>();
		userRepository.findAll().forEach(users::add);
//		users=users.stream().filter(e->{return e.getId()>=id;}).collect(Collectors.toList());
		return users;
	}

	@Override
	public List<User> getUsersPaginated(Long start, Long size) {
		List<User>users=new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		if(!(start+size>users.size())){
		users=users.subList(safeLongToInt(start), safeLongToInt(start+size));
		}
		return users;
	}

	@Override
	public User getUser(String username) {
		return userRepository.findById(username).get();
	}

	public static int safeLongToInt(long longType) {
	    if (longType < Integer.MIN_VALUE || longType > Integer.MAX_VALUE) {
	        throw new IllegalArgumentException
	            (longType + LONG_TO_INT_MESSAGE);
	    }
	    return (int) longType;
	}

}
