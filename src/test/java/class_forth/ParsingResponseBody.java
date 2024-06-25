package class_forth;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class ParsingResponseBody {

    //Approach 1
    @Test(priority = 0)
    void testResponseBody() {

        given()
                .contentType("contentType.JSON")
                .when()
                .get("http://localhost:3000/student")
                .then()
                .statusCode(200)
                .log().all()
                .body("student[0].name", equalTo("Tom"));
    }

    //Approach 2
    @Test(priority = 1)
    void testResponseBody2() {
        Response res = given()
                .contentType("contentType.JSON")
                .when()
                .get("https://reqres.in/api/users?page=2");
        Assert.assertEquals(res.getStatusCode(), 200);
        String value = res.jsonPath().get("data[3].first_name").toString();
        System.out.println(value + "-----------");
        Assert.assertEquals(value, "Byron","Matched");

    }


}
