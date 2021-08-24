package org.myapp;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class GreetingResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
                .auth().basic("test", "test")
          .when().get("/hello/Tom")
          .then()
             .statusCode(200)
             .body(is("Hi Tom"));
    }

    @Test
    public void testInValidCredentialsHelloEndpoint() {
        //401 authentication failure bad password
        given()
                .auth().basic("admin", "user")
                .when().get("/hello/Tom")
                .then()
                .statusCode(401);
    }

    @Test
    public void testInValidUserHelloEndpoint() {
        //403 authenticated but not authorised response
        given()
                .auth().basic("user", "user")
                .when().get("/hello/Tom")
                .then()
                .statusCode(403);
    }

}