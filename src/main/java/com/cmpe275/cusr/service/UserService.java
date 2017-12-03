package com.cmpe275.cusr.service;

import com.cmpe275.cusr.model.User;

public interface UserService {
	//public User findUser();
	public User signin(String Uid, String email);
}
