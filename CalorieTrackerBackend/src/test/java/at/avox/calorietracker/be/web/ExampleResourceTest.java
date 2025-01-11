package at.avox.calorietracker.be.web;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class ExampleResourceTest {
    @Test
    void testHelloEndpoint() {
        given()
            .when().get("/v1/hello")
            .then()
            .statusCode(200)
            .body(is("Hello from Quarkus REST"));
    }

}