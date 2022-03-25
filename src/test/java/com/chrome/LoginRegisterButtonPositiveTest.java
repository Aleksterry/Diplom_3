package com.chrome;

import com.LoginPage;
import com.MainPage;
import com.RegisterPage;
import com.model.*;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;


public class LoginRegisterButtonPositiveTest extends BaseSettingsChrome {


    private MainPage mainPage;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private User user;
    private UserMethods userMethods;
    private String accessToken;
    private TearDownUser tearDownUser;

    @Before
    public void setup() {
        // Создание пользователя
        createUser();
        // Открытие браузера, страницы регистрацмм
        openRegisterPage();
    }


    @Step("Before test: open register page")
    public void openRegisterPage() {
        registerPage = null;
        loginPage = page(LoginPage.class);
        mainPage = page(MainPage.class);

        // открыть браузер
        registerPage = open(RegisterPage.REGISTER_URL, RegisterPage.class);

        //Проверка перехода на страницу регистрации
        registerPage.checkUrlPage();
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
    @Description("Test of login through \"Login\" button on register page")
    public void testLoginRegisterButtonPositive() {

        //Кликнуть "Войти" на форме регистрации
        registerPage.clickLoginButton();

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
