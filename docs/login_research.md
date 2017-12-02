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

### Facebook Login
Reference: https://developers.facebook.com/docs/facebook-login/manually-build-a-login-flow
<br>
Reference: https://github.com/knalum/spring-auth