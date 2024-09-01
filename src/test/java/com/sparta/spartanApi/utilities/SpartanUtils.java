package com.sparta.spartanApi.utilities;

import com.sparta.spartanApi.AppConfig;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class SpartanUtils {
    private static final String SPARTANS = "api/Spartans";
    private static final String SPECIFIC_SPARTAN = "api/Spartans/{id}";

    private static RequestSpecification getCommonSpec(Map<String, String> headers) {
        return RestAssured
                .given()
                .headers(headers)
                .baseUri(AppConfig.getApiBaseUri());
    }

    public static Response getAllSpartans(Map<String, String> headers) {
        return getCommonSpec(headers)
                .basePath(SPARTANS)
                .get();
    }

    public static Response getSpartan(Map<String, String> headers, String id) {
        return getCommonSpec(headers)
                .basePath(SPECIFIC_SPARTAN)
                .pathParams(Map.of("id", id))
                .get();
    }

    public static Response createSpartan(Map<String, String> headers, String body) {
        return getCommonSpec(headers)
                .basePath(SPARTANS)
                .body(body)
                .contentType(ContentType.JSON)
                .post();
    }

    public static Response updateSpartan(Map<String, String> headers, String id, String body) {
        return getCommonSpec(headers)
                .basePath(SPECIFIC_SPARTAN)
                .pathParams(Map.of("id", id))
                .body(body)
                .contentType(ContentType.JSON)
                .put();
    }

    public static Response deleteSpartan(Map<String, String> headers, String id) {
        return getCommonSpec(headers)
                .basePath(SPECIFIC_SPARTAN)
                .pathParams(Map.of("id", id))
                .contentType(ContentType.JSON)
                .delete();
    }
}
