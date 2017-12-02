package com.cmpe275.cusr.service;

//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.cmpe275.cusr.model.User;

@Service
public class UserServiceImpl implements UserService {
	
	/*public User findUser() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user;
	}*/
}