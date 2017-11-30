package com.cmpe275.cusr.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FacebookController {
	/*private final String REDIRECT_URI = "https://localhost:8080/";
	private final String APP_ID;
	private final String APP_SECRET;
	
	public FacebookController(String REDIRECT_URI, String APP_ID, String APP_SECRET) {
		this.REDIRECT_URI = REDIRECT_URI;
		this.APP_ID = APP_ID;
		this.APP_SECRET = APP_SECRET;
	}
	
	@GetMapping("/facebook/login")
	public ResponseEntity<?> facebookLogin(@RequestParam("code") String code, @RequestParam("state") String state, HttpServletResponse httpServletResponse) throws IOException {
		//process token and access details
		return ResponseEntity.ok().build();
	}
	
	//generate URI for client side login link
	@GetMapping("/facebook/getLoginUri")
	public String getLoginUri() {
		String CSRF = UUID.randomUUID().toString();
		String uri = "https://www.facebook.com/v2.9/dialog/oauth?client_id=" + APP_ID + "&redirect_uri=" + REDIRECT_URI
	            + "&state=" + CSRF;
	        return uri;
	}
*/
}
