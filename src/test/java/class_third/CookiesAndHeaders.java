package class_third;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;


public class CookiesAndHeaders {
    @Test(priority = 0)
    void getAllCookies() {
        given()

                .when()
                .get("https://www.youtube.com/")
                .then()
                .statusCode(200)
                .log().all();

    }

    @Test(priority = 1)
    void getSingleCookieInfo() {

        Response res = given()

                .when()
                .get("https://www.youtube.com/");
        String cookiesdetails = res.getCookie("DEVICE_INFO");
        System.out.println(cookiesdetails);
    }

    @Test(priority = 2)
    void getAllCookieInfo() {

        Response details = given()

                .when()
                .get("https://www.youtube.com/");

        Map<String, String> allCookiesName = details.getCookies();

        for (String keys : allCookiesName.keySet()) {
            String cookiesdetails = details.getCookie(keys);
            System.out.println(keys + " --> " + cookiesdetails);
        }
    }

    @Test(priority = 3)
    void getAllHeaders() {
        Response details = given()

                .when()
                .get("https://www.youtube.com/");

        Headers allheaders = details.getHeaders();

        for (Header headervalue : allheaders) {
            String headersdetails = headervalue.getValue();
            System.out.println(headervalue + " --> " + headersdetails);
        }
    }
}
