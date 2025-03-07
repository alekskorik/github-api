package org.example;


import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class GithubResourceTest {

    @Test
    public void testHelloEndpoint() {
        RestAssured.given()
                .when().get("/api/github/repos/octocat")
                .then()
                .statusCode(200);
    }
}