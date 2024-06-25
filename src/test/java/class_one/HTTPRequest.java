package class_one;

/*  Given()
        Content type, Set cookies,add auth,add parameter,set header
    When()
        Get, Post,Put,Delete (Requests)
    Then()
        Status code, body ,header  ,response
*/

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class HTTPRequest {

    int id;

    @Test(priority = 0)
    void getUsers() {
        given()

                .when()

                .get("https://reqres.in/api/users?page=2")

                .then()

                .statusCode(200)
                .body("page", equalTo(2))
                .log().all();
    }

    @Test(priority = 1)
    void createUser() {
        HashMap hm = new HashMap();
        hm.put("name", "jhon");
        hm.put("job", "trainer");

        id = given()
                .contentType("application/json")
                .body(hm)
                .when()
                .post("https://reqres.in/api/users")
                .jsonPath().getInt("id")
//                .then()
//                .statusCode(201)
//                .log().all()
        ;
    }

    @Test(priority = 2, dependsOnMethods = {"createUser"})
    void updateUser() {
        HashMap hm = new HashMap();
        hm.put("name", "akbar");
        hm.put("job", "terorist");
        given()
                .contentType("application/json")
                .body(hm)
                .when()
                .put("https://reqres.in/api/users/" + id)
                .then()
                .statusCode(200)
                .log().all()
        ;

    }

    @Test(priority = 4)
    void deleteUser() {
        given()
                .when()
                .delete("https://reqres.in/api/users/" + id)
                .then()
                .statusCode(204);
    }

}
