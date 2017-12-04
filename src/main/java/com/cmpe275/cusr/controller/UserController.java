package com.cmpe275.cusr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.apache.commons.lang3.StringUtils;

import com.cmpe275.cusr.config.auth.firebase.FirebaseAuthenticationToken;
import com.cmpe275.cusr.config.auth.firebase.FirebaseTokenHolder;
import com.cmpe275.cusr.model.User;
import com.cmpe275.cusr.service.FirebaseService;
import com.cmpe275.cusr.service.UserService;
import com.cmpe275.cusr.service.UserServiceImpl;

@Controller
public class UserController {
	
	@Autowired(required = false)
	private FirebaseService firebaseService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/signin")
	public String signinSuccess(Model model, @RequestBody String firebaseToken) {
		if(StringUtils.isBlank(firebaseToken))
			throw new IllegalArgumentException("FirebaseTokenBlank");
		
		//validate token and return FirebaseTokenHolder instance
		FirebaseTokenHolder tokenHolder = firebaseService.parseToken(firebaseToken);
		System.out.println(tokenHolder.getUid());
		return "usertickets";
	}
	@GetMapping("/signin")
	public String signinSuccessGet(Model model, @RequestParam(value="firebaseToken", required=true) String firebaseToken) {
		
		//consider migrating all of this into UserService
		if(StringUtils.isBlank(firebaseToken))
			throw new IllegalArgumentException("FirebaseTokenBlank");
		
		//validate token and return FirebaseTokenHolder instance
		FirebaseTokenHolder tokenHolder = firebaseService.parseToken(firebaseToken);
		System.out.println("Token authenticated");
		User loadedUser = userService.signin(tokenHolder.getUid(), tokenHolder.getEmail());
		Authentication auth = new FirebaseAuthenticationToken(loadedUser.getUserUId(), loadedUser.getEmail(), loadedUser.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		return "usertickets";
		//redirection keeps giving 403 FORBIDDEN
		//return "redirect:/registration";
	}
	
}
