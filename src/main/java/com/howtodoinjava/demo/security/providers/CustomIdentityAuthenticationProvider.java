package com.howtodoinjava.demo.security.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.howtodoinjava.demo.security.basicauth.service.CustomUserDetailsService;
 
@Component
public class CustomIdentityAuthenticationProvider
    implements AuthenticationProvider {
	
	@Autowired
	CustomUserDetailsService userDetailService;

  // TODO:
  // In this function we need to connect with identity provider
  // and validate the user
  // we are hardcoding for a single user for demo purposes
  UserDetails isValidUser(String username, String password) {
	UserDetails userDetails = userDetailService.loadUserByUsername(username);  
	Authentication authentication= new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()); 
	SecurityContextHolder.getContext().setAuthentication(authentication);
	if(password.equals(userDetails.getPassword())){
		return userDetails;
	}
	return null;
  }

  @Override
  public Authentication authenticate(Authentication authentication) {
	  
	SecurityContext context =  SecurityContextHolder.getContext();
	Authentication auth =  context.getAuthentication();
	String username = authentication.getName();
    String password = authentication.getCredentials().toString();

    
    UserDetails userDetails = isValidUser(username, password);

    if (userDetails != null) {
      return new UsernamePasswordAuthenticationToken(
	          username,
	          password,
	          userDetails.getAuthorities());
    } else {
      throw new BadCredentialsException("Incorrect user credentials !!");
    }
  }

  @Override
  public boolean supports(Class<?> authenticationType) {
    return authenticationType
        .equals(UsernamePasswordAuthenticationToken.class);
  }
}