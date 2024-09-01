package com.sparta.spartanApi.utilities;

import com.sparta.spartanApi.AppConfig;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import com.sparta.spartanApi.pojos.LoginRequest;

public class LoginUtils {
    private static final String AUTH_PATH = "Auth/login";

    public static Response sendLoginRequest(LoginRequest body) {
        return RestAssured
                .given()
                .baseUri(AppConfig.getApiBaseUri())
                    .basePath(AUTH_PATH)
                    .body(body)
                    .contentType(ContentType.JSON)
                .post();
    }
}
