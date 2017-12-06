### Firebase Integration
Reference: https://github.com/savicprvoslav/Spring-Boot-starter
<br>
* Firebase needs to be enabled in application.properties
```
rs.pscode.firebase.enabled=true/false
```
* Dependency
```
<dependency>
	<groupId>com.google.firebase</groupId>
	<artifactId>firebase-server-sdk</artifactId>
	<version>3.0.1</version>
</dependency>
```
* Set up service account on Firebase console: https://firebase.google.com/docs/server/setup
    * Admin SDK configuration snippet
```
    FileInputStream serviceAccount =
  new FileInputStream("path/to/serviceAccountKey.json");

FirebaseOptions options = new FirebaseOptions.Builder()
  .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
  .setDatabaseUrl("https://cmpe275-cusr.firebaseio.com")
  .build();

FirebaseApp.initializeApp(options);
```
* Verifying ID tokens using Firebase Admin SDK
```
// idToken comes from the client app (shown above)
FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdTokenAsync(idToken).get();
String uid = decodedToken.getUid();
```

### Taking Firebase Token and setting user authentication in security context
* First verify firebase user with user in our own database (if not, create new DB user)
* Create Firebase AuthenticationToken which implements AbstractAuthToken
* Authentication auth = new FirebaseAuthenticationToken(uId, token)
* set security context authentication with auth
<br>
Reference: https://stackoverflow.com/questions/7900994/programmatically-login-in-a-user-using-spring-security

### Disable Spring security login screen?
Reference: https://stackoverflow.com/questions/23636368/how-to-disable-spring-security-login-screen
<br>
Reference: https://stackoverflow.com/questions/25639188/disable-basic-authentication-while-using-spring-security-java-configuration

### Securing API endpoints
* Using SecurityContext, set authentication with an auth token (I used a custom firebaseAuthenticationToken on top of an abstract token)
* Grant access to specific routes in SecurityConfig using antMatchers and roles
```
@Override
protected void configure(HttpSecurity http) throws Exception {
	http.authorizeRequests()
		.antMatchers("/user/**").hasRole("USER")
		.anyRequest().permitAll();
}
```

### Facebook Login
Reference: https://developers.facebook.com/docs/facebook-login/manually-build-a-login-flow
<br>
Reference: https://github.com/knalum/spring-auth

### Tasks
~~1. Disable Spring Basic Security Login Prompt. Done by just authenticating everything in security config.~~<br>
~~2. Make UserId available via UserService~~<br>
~~3. Test and debug current login/signup functionalities (database updated? getUserId through security context?)~~<br>
~~4. Migrate content in UserController to UserService~~<br>
~~5. Web Security Config and Fix issue with redirection~~<br>
~~6. Authenticate User in UserService to make sure UserId matches with Email~~<br>
7. Securing anonymous pages & implementing logout<br>
~~8. Replace default error page~~<br>
~~9. Replace token credentials with actual user model~~
<br>