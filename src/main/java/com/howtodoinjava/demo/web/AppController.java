package com.howtodoinjava.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;

import com.howtodoinjava.demo.security.basicauth.AppBasicAuthenticationEntryPoint;

@RestController
public class AppController {

  @Autowired
  private AppBasicAuthenticationEntryPoint logoutHandler;

  @GetMapping("/")
  public String home(){
	String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
	System.out.println("JSession ID "+ sessionId);
    return "Hello World !!";
  }

  @GetMapping("/public")
  public String publicResource(){
    return "Access allowed to all !!";
  }
}
