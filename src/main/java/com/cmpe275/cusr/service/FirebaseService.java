package com.cmpe275.cusr.service;

import com.cmpe275.cusr.config.auth.firebase.FirebaseTokenHolder;

public interface FirebaseService {
	FirebaseTokenHolder parseToken(String idToken);
}
