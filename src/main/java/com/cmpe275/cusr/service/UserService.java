package com.cmpe275.cusr.service;

import com.cmpe275.cusr.model.User;

public interface UserService {
	public String findUser();
	public void signInAuthentication(String firebaseToken);
	public User getUserFromDB(String Uid, String email);
}
