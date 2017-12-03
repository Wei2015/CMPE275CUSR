package com.cmpe275.cusr.service;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.cmpe275.cusr.model.User;
import com.cmpe275.cusr.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	/*public User findUser() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user;
	}*/
	public User signin(String UId, String email) {
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