package com.alek.influentialpeople.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alek.influentialpeople.persistance.entity.User;
import com.alek.influentialpeople.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public UserController(BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@CrossOrigin
	@RequestMapping(path = "/user", method = RequestMethod.GET)
	public List<User> getAllUsers(@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "start", required = false) Long start,
			@RequestParam(value = "size", required = false) Long size,
			@CookieValue(value = "aCookie", required = false) String aValue,
			@RequestHeader MultiValueMap<String, String> headers) {
		System.out.println(headers.entrySet());
		if (id != null) {
			return userService.getUsersForId(id);
		} else if (start != null && size != null) {
			return userService.getUsersPaginated(start, size);
		}

		return userService.getAllUsers();
	}

	@RequestMapping(path = "/user", method = RequestMethod.POST)
	public void addUser(@RequestBody User user) {
		userService.addUser(user);
	}

	@RequestMapping(path = "/user/{id}", method = RequestMethod.PUT)
	public void updateUser(@RequestBody User user, @PathVariable String id) {

		userService.updateUser(user);
	}

	@RequestMapping(path = "/user/{id}", method = RequestMethod.GET)
	public User getUser(@PathVariable String id) {

		return userService.getUser(Long.valueOf(id));
	}

	@PostMapping("/sign-up")
	public void signUp(@RequestBody User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userService.addUser(user);
	}
//
//	@RequestMapping(path = "/user", method = RequestMethod.GET)
//	public User getUser5() {
//
//		return new User();
//	}
//
//	@RequestMapping(path = "/user", method = RequestMethod.GET)
//	public User getUs1er() {
//
//		return new User();
//	}

}
