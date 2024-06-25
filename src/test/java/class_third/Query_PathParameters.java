package class_third;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class Query_PathParameters {

    @Test
    void queryandPatparameter() {

        given()
                .pathParams("path", "users")
                .queryParam("page", 2)
                .when()
                .get("https://reqres.in/api/{path}")
                .then()
                .statusCode(200)
                .log().all();

    }


}
