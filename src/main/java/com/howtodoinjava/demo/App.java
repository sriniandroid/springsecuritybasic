package com.howtodoinjava.demo;

import java.util.HashMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.howtodoinjava.demo.security.basicauth.model.AppUser;

@SpringBootApplication
public class App 
{
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
    
	@Bean
	HashMap<String, AppUser> getMap() {
		HashMap<String, AppUser> map = new HashMap<String, AppUser>();
		map.put("user", new AppUser("user", "password"));
		return map;
	}
}
