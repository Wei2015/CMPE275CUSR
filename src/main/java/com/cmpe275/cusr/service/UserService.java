package com.cmpe275.cusr.service;

import com.cmpe275.cusr.model.User;

public interface UserService {
	public User findUser();
	public void signInAuthentication(String firebaseToken);
	public void signout();
	public User getUserFromDB(String email);
}
