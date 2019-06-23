package com.alek.influentialpeople.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alek.influentialpeople.email.EmailService;
import com.alek.influentialpeople.persistence.entity.User;
import com.alek.influentialpeople.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private EmailService emailService;

	private PasswordEncoder bCryptPasswordEncoder;
	private User user;

	public UserController(PasswordEncoder bCryptPasswordEncoder) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

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

	@RequestMapping(value = "/user/{id}/uploadFile", method = RequestMethod.POST)
	public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable String id) {

		File uploadedFile = new File("static/storage/user/image/", file.getOriginalFilename() + id);

		try {
			uploadedFile.createNewFile();
			FileOutputStream fileOutputStream = new FileOutputStream(uploadedFile);
			fileOutputStream.write(file.getBytes());
			fileOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>("file Uplaoded succesfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/user/{id}/uploadmultipleFiles", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Object> uploadmultipleFile(@RequestParam("files") MultipartFile[] files,
			@PathVariable String id) {
		FileOutputStream fileOutputStream = null;
		System.out.println("hae");
		System.out.println(files);
		for (MultipartFile multipartFile : files) {
			File uploadedFile = new File("src/main/resources/static/storage/user/image",
					multipartFile.getOriginalFilename() + id);
			System.out.println("hae2");
			try {
				System.out.println("hae3");
				uploadedFile.createNewFile();
				fileOutputStream = new FileOutputStream(uploadedFile);
				fileOutputStream.write(multipartFile.getBytes());
				fileOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		return new ResponseEntity<Object>("All file Uplaoded succesfully", HttpStatus.OK);
	}

	@RequestMapping(path = "/user", method = RequestMethod.POST)
	public void addUser(@RequestBody User user) {

		userService.addUser(user);
	}

	protected void validateRole() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		if (!validationService.validateRole(authentication, "ADMIN")) {
//			throw new RuntimeException(e);
//		}
	}

	@RequestMapping(path = "/user/{id}", method = RequestMethod.PUT)
	public void updateUser(@RequestBody User user, @PathVariable String id) {

		userService.updateUser(user);
	}

	@RequestMapping(path = "/user/{id}", method = RequestMethod.GET)
	public User getUser(@PathVariable String id) {

		return userService.getUser(Long.valueOf(id));
	}

	@PostMapping("/user/sign-up")
	public void sendEmail(@RequestBody User user) throws IOException {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		System.out.println("sending email ...");
		this.user = user;
		System.out.println(user.getUsername());
		emailService.sendMail(user.getUsername());
	}

	@GetMapping("/user/sign-up")
	public void signUp(@RequestParam(name = "username") String username) {
		System.out.println(user.getPassword());
//		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActivation(1);
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		userService.addUser(user);
	}

}
