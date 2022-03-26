package com.chrome;

import com.LoginPage;
import com.MainPage;
import com.model.*;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;


public class LoginPersonalAccountButtonPositiveTest extends BaseSettingsChrome {

    private MainPage mainPage;
    private LoginPage loginPage;
    private User user;
    private UserMethods userMethods;
    private String accessToken;
    private TearDownUser tearDownUser;

    @Before
    public void setup() {
        // Создание пользователя
        createUser();

        // Открытие браузера
        openMainPage();
    }

    @Step("Before test: open main page")
    public void openMainPage() {

        mainPage = null;
        loginPage = page(LoginPage.class);

        // открыть браузер
        mainPage = open(MainPage.BASE_URL, MainPage.class);

        //Проверка перехода на главную страницу
        mainPage.checkUrlPage();
        //Проверка загрузки главной страницы
        mainPage.checkTextOnMainPage();
    }

    @Step("Before test: send POST request to /api/register - to create user")
    public void createUser() {
        userMethods = new UserMethods();
        user = User.getRandom();

        // Создание пользователя
        accessToken = userMethods.create(user).assertThat().statusCode(200).and().extract().path("accessToken");
    }


    @After
    @Step("After test: send DELETE request to api/auth/user - to delete user")
    public void tearDownUser() {
        tearDownUser = new TearDownUser();
        tearDownUser.tearDown(accessToken, userMethods);
    }


    @Test
    @Description("Test of login through \"Personal account\" button on main page")
    public void testLoginPositive() {

        //Кликнуть "Личный кабинет"
        mainPage.clickPersonalAccountButton();

        // Проверить переход на страницу логина
        loginPage.checkUrlPage();

        // Проверить наличия элементов на странице входа
        loginPage.checkLoginPageElementsIsVisible();

        // Заполнение полей логина и пароля
        loginPage.fillLoginFields(user);

        // Кликнуть "Войти"
        loginPage.clickLoginButton();

        // Проверка, что вход совершен
        mainPage.checkUrlPage();
        mainPage.checkTextOnMainPage();
        mainPage.checkPlaceOrderButtonText();

    }
}