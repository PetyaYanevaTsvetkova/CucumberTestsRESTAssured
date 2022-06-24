package helper;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;

public class ResponseHelper {
    public static <K, V> Response responseWithParameter(Map map, String endpoint){
        return RestAssured.given()
                .with()
                .queryParams(map)
                .when()
                .get(endpoint);
    }

    public static <K, V> Response responseWithParameter(Map map){
        return RestAssured.given()
                .with()
                .queryParams(map)
                .when()
                .get();
    }
}
