package com.model;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

public class BasePage {

    public String accessToken;


    public void deleteUser(String accessToken, UserMethods userMethods) {
        if (accessToken != null) {
            ValidatableResponse response = userMethods.deleteUser(accessToken.substring(7));
            if (response.extract().statusCode() == 202) {
                System.out.println("\nuser is deleted\n");
            } else {
                System.out.println("\nuser was not be deleted\n");
            }
        }
    }

    public String createUser(User user, UserMethods userMethods) {
        // Создание пользователя
        return accessToken = userMethods.createUser(user).assertThat().statusCode(200).and().extract().path("accessToken");
    }

    /**
     * Проверка урла страницы
     */
    public void checkUrlPage(String url) {
        webdriver().shouldHave(url(url));
    }
}

