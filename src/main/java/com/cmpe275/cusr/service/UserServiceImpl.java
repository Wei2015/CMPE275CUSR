package com.cmpe275.cusr.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.cmpe275.cusr.config.auth.firebase.FirebaseAuthenticationToken;
import com.cmpe275.cusr.config.auth.firebase.FirebaseTokenHolder;
import com.cmpe275.cusr.model.User;
import com.cmpe275.cusr.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired(required = false)
	private FirebaseService firebaseService;
	
	public String findUser() {
		String userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userId;
	}
	
	public void signInAuthentication(String firebaseToken) {
		if(StringUtils.isBlank(firebaseToken))
			throw new IllegalArgumentException("FirebaseTokenBlank");
		
		//validate token and return FirebaseTokenHolder instance
		FirebaseTokenHolder tokenHolder = firebaseService.parseToken(firebaseToken);
		System.out.println("Token authenticated");
		
		User loadedUser = getUserFromDB(tokenHolder.getUid(), tokenHolder.getEmail());
		Authentication auth = new FirebaseAuthenticationToken(loadedUser.getUserUId(), loadedUser.getEmail(), loadedUser.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);
	}
	
	public User getUserFromDB(String UId, String email) {
		User userLoaded = userRepository.findByUIdEmail(UId, email);
		
		if(userLoaded == null) {
			userLoaded = new User();
			userLoaded.setUserUId(UId);
			userLoaded.setEmail(email);
			userRepository.save(userLoaded);
		} 
		return userLoaded;
	}
	
}