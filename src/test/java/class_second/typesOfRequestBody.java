package class_second;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class typesOfRequestBody {

    /*Hashmap
     * json.org
     * POJO
     * External json file
     */
    @Test
    void bodyUsingHashmap() {

        HashMap hm = new HashMap();
        hm.put("id", 01);
        hm.put("name", "radhe");
        hm.put("lastname", "shyam");

        given()
                .contentType("application/json")
                .body(hm)
                .when()
                .post("http://localhost:3000/student")
                .then()
                .statusCode(201)
                .body("id", equalTo(01))
                .body("name", equalTo("radhe"))
                .body("lastname", equalTo("shyam"))
                .log().all();
    }

    @Test
    void usingjsonOrg() {
        JSONObject jo = new JSONObject();
        jo.put("id", 04);
        jo.put("name", "sole");
        jo.put("lastname", "sastri");
        given()
                .contentType("application/json")
                .body(jo.toString())
                .when()
                .post("http://localhost:3000/student")
                .then()
                .statusCode(201)
                .body("id", equalTo(04))
                .body("name", equalTo("sole"))
                .body("lastname", equalTo("nyan"))
                .log().all();
    }

    @Test
    void usingPOJOClass() {
        pojo pj = new pojo();
        pj.setId(07);
        pj.setName("guru");
        pj.setLastname("pandyan");

        given()
                .contentType("application/json")
                .body(pj)
                .when()
                .post("http://localhost:3000/student")
                .then()
                .statusCode(201)
                .body("id", equalTo(07))
                .body("name", equalTo("guru"))
                .body("lastname", equalTo("pandyan"))
                .log().all();

    }

    @Test
    void usingExternalFile() throws FileNotFoundException {
        File file = new File(".\\body.json");
        FileReader fr = new FileReader(file);
        JSONTokener jt = new JSONTokener(fr);
        JSONObject jo = new JSONObject(jt);

        given()
                .contentType("application/json")
                .body(jo.toString())
                .when()
                .post("http://localhost:3000/student")
                .then()
                .statusCode(201)
                .body("id", equalTo("05"))
                .body("name", equalTo("Ravi"))
                .body("lastname", equalTo("guru"))
                .log().all();
    }
}
