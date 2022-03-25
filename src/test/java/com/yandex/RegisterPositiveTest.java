package com.yandex;

import com.LoginPage;
import com.MainPage;
import com.RegisterPage;
import com.model.*;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;


public class RegisterPositiveTest extends BaseSettingsYandex {

    private MainPage mainPage;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private UserMethods userMethods;
    private String accessToken;
    private TearDownUser tearDownUser;


    @Before
    public void setup() {
        // открыть браузер
        mainPage = null;
        mainPage = open(MainPage.BASE_URL, MainPage.class);

        loginPage = page(LoginPage.class);
        registerPage = page(RegisterPage.class);

        //Проверка перехода на главную страницу и её загрузки
        mainPage.checkUrlPage();
        mainPage.checkTextOnMainPage();
    }


    @Step("After test: get user accessToken from response")
    public void getUserAccessToken(User user) {
        userMethods = new UserMethods();
        ValidatableResponse response = userMethods.login(new UserCredentials(user.password, user.email));

        // Запись accessToken пользователя для последующего удаления
        accessToken = response.extract().path("accessToken");
    }

    @After
    @Step("After test: send DELETE request to api/auth/user - to delete user")
    public void tearDownUser() {
        tearDownUser = new TearDownUser();
        tearDownUser.tearDown(accessToken, userMethods);
    }


    @Test
    @Description("User has been successfully registered")
    public void testRegisterPositive() {

        //Кликнуть "Войти в аккаунт"
        mainPage.clickLoginInAccountButton();

        // Проверить переход  и загрузку страницы логина
        loginPage.checkUrlPage();
        loginPage.checkTextOnLoginPage();

        // Кликнуть "Зарегистироваться"
        loginPage.clickRegisterButton();

        //Проверка перехода на страницу регистрации
        registerPage.checkUrlPage();

        // Проверить наличие элементов на странице регистрации
        registerPage.checkRegisterPageElementsIsVisible();

        // Заполнить форму регистрации данными
        User user = User.getRandom();
        registerPage.fillRegisterFields(user);

        // Клик по кнопке "Зарегистрироваться"
        registerPage.clickRegisterButton();

        // Проверка перехода на страницу входа
        loginPage.checkUrlPage();
        loginPage.checkTextOnLoginPage();

        //Вход под зарегистрированным пользователем
        loginPage.login(user);

        //Проверка перехода на главную страницу после входа
        mainPage.checkUrlPage();
        mainPage.checkTextOnMainPage();

        // Проверка, что кнопка "Войти в аккаунт" изменилась на "Оформить заказ" - вход успешно совершен
        mainPage.checkPlaceOrderButtonText();

        //Получить токен зарегистрированного пользователя для последующего удаления
        getUserAccessToken(user);
    }

}
