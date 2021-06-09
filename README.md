# mockDemo
- use maven to manage dependencies
- use JUnit5 as test framework
- use springboot to implement a simple api and worked as a real backend
- use wiremock as mock-server
- use rest-assured to test api

# build/run the project
```./mvnw spring-boot:run```

# stub test demo
- run src/test/java/com/example/restservice/GreetingStubTest.java
 - start wiremock server at port 8089
 - config client at the same port to listen
 - mock response
 - stop server
 
