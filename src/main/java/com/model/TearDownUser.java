package com.model;

import io.restassured.response.ValidatableResponse;

public class TearDownUser {

    public UserMethods userMethods;
    public String accessToken;

    public void tearDown(String accessToken, UserMethods userMethods) {
        if (accessToken != null) {
            ValidatableResponse response = userMethods.delete(accessToken.substring(7));
            if (response.extract().statusCode() == 202) {
                System.out.println("\nuser is deleted\n");
            } else {
                System.out.println("\nuser was not be deleted\n");
            }
        }
    }
}
