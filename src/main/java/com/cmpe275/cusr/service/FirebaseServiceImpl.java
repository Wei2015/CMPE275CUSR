package com.cmpe275.cusr.service;

import org.springframework.stereotype.Service;

import com.cmpe275.cusr.config.auth.firebase.FirebaseTokenHolder;
import com.cmpe275.cusr.service.FirebaseService;
import com.cmpe275.cusr.service.FirebaseParser;
import com.cmpe275.cusr.spring.conditionals.FirebaseCondition;

@Service
@FirebaseCondition
public class FirebaseServiceImpl implements FirebaseService {
	@Override
	public FirebaseTokenHolder parseToken(String firebaseToken) {
		return new FirebaseParser().parseToken(firebaseToken);
	}
}