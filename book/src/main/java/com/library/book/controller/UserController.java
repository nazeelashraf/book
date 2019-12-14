package com.library.book.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.book.exception.UserAlreadyExistsException;
import com.library.book.model.ApplicationUser;
import com.library.book.repository.RoleRepository;
import com.library.book.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private SCryptPasswordEncoder sCryptPasswordEncoder;
	
	@PostMapping("/sign-up")
	public void signUp(@RequestBody ApplicationUser user) {
		
		if(userRepository.findByUsername(user.getUsername()) != null) {
			throw new UserAlreadyExistsException();
		}
		
		user.setPassword(sCryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
		userRepository.save(user);
	}
	
	@GetMapping("/all")
	@Secured({"ROLE_ADMIN"})
	public List<ApplicationUser> findAll(){
		return userRepository.findAll();
	}
	
}
