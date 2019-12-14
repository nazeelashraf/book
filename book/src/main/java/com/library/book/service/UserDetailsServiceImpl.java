package com.library.book.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.library.book.model.ApplicationUser;
import com.library.book.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ApplicationUser applicationUser = userRepository.findByUsername(username);
		
		if(applicationUser == null) {
			throw new UsernameNotFoundException(username);
		}
		
		Collection<GrantedAuthority> authorities = 
				applicationUser.getRoles().stream().map(
						role -> {
							return role.getName();
						}).collect(Collectors.toList()).stream().map(
								SimpleGrantedAuthority::new
						).collect(Collectors.toList());
		
		return new User(applicationUser.getUsername(), applicationUser.getPassword(), authorities);
	}

}
