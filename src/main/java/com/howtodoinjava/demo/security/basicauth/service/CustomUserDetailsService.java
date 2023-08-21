package com.howtodoinjava.demo.security.basicauth.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.howtodoinjava.demo.security.basicauth.model.AppUser;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private HashMap<String, AppUser> userRepository;

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		// Optional<AppUser> user = userRepository.findByEmail(s);
		AppUser appuser = userRepository.get(s);
		if (appuser != null) {
			return User.withUsername(appuser.getEmail()).password(appuser.getPassword()).authorities("USER").build();
		} else {
			throw new UsernameNotFoundException(String.format("Email[%s] not found", s));
		}
	}


}
