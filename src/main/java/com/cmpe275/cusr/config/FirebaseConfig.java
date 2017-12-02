package com.cmpe275.cusr.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

@Configuration
public class FirebaseConfig {

	@Bean
	public DatabaseReference firebaseDatabse() {
		DatabaseReference firebase = FirebaseDatabase.getInstance().getReference();
		return firebase;
	}

	@Value("https://cmpe275-cusr.firebaseio.com")
	private String databaseUrl;

	@Value("cmpe275-cusr-firebase-adminsdk-b4bca-1111977888.json")
	private String configPath;

	@PostConstruct
	public void init() {
		
		try {
			FileInputStream serviceAccount = new FileInputStream(configPath);
			FirebaseOptions options = new FirebaseOptions.Builder()
					  .setServiceAccount(serviceAccount)
					  .setDatabaseUrl(databaseUrl)
					  .build();
			FirebaseApp.initializeApp(options);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
	}
}