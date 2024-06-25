package Practice;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class Sample_1 {
    @Test
    public void test1() {
        Response res = RestAssured.get("https://www.google.com/");

        System.out.println(res.getStatusCode());
        System.out.println(res.body());
        System.out.println(res.headers()+"-------------");
    }

}
