package com.cmpe275.cusr.service;

import com.cmpe275.cusr.model.User;

public interface UserService {
	public User findUser();
	public void signInAuthentication(String firebaseToken);
	public User getUserFromDB(String Uid, String email, String name);
}
