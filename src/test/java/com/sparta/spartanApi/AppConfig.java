package com.sparta.spartanApi;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfig {

    private static String token;
    private static final Properties properties = new Properties();

    static {
        try (InputStream inputStream = AppConfig.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new IOException("Unable to find config.properties");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getApiBaseUri() {
        return properties.getProperty("api_uri");
    }

    public static String getUsername() {
        return properties.getProperty("username");
    }

    public static String getPassword() {
        return properties.getProperty("password");
    }

    public static String getToken() {
        if (token == null || token.isEmpty()) {
            token = fetchNewToken();
        }
        return token;
    }

    private static String fetchNewToken() {
        // Make a POST request to /Auth/login to get a new token
        Response response = RestAssured.given()
                .contentType("application/json")
                .body("{\"username\": \"sparta\", \"password\": \"global\"}")
                .post("/Auth/login");

        // Extract the token from the response
        return response.jsonPath().getString("token");
    }
}
