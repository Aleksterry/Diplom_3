package com.chrome;

import com.AccountProfilePage;
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


public class TransitionToPersonalAccountPagePositiveTest extends BaseSettingsChrome {

    private MainPage mainPage;
    private LoginPage loginPage;
    private AccountProfilePage accountProfilePage;
    private User user;
    private UserMethods userMethods;
    private String accessToken;
    private TearDownUser tearDownUser;

    @Before
    public void setup() {
        // Создание пользователя
        createUser();
        // Открытие браузераб страницы логина
        openLoginPage();
        // Логин пользователя
        loginUser(user);
    }

    @Step("Before test: open login page")
    public void openLoginPage() {
        // открыть браузер
        loginPage = null;
        loginPage = open(LoginPage.LOGIN_URL, LoginPage.class);

        mainPage = page(MainPage.class);
        accountProfilePage = page(AccountProfilePage.class);

        //Проверка перехода на страницу логина
        loginPage.checkUrlPage();
    }

    @Step("Before test: send POST request to /api/register - to create user")
    public void createUser() {
        userMethods = new UserMethods();
        user = User.getRandom();
        // Создание пользователя
        accessToken = userMethods.create(user).assertThat().statusCode(200).and().extract().path("accessToken");
    }

    @Step("Before test: login user")
    public void loginUser(User user) {
        // Логин пользователя
        loginPage.login(user);
    }

    @After
    @Step("After test: send DELETE request to api/auth/user - to delete user")
    public void tearDownUser() {
        tearDownUser = new TearDownUser();
        tearDownUser.tearDown(accessToken, userMethods);
    }


    @Test
    @Description("Test of transition to account profile page from main page")
    public void testTransitionToPersonalAccountPagePositive() {

        // Проверка перехода на главную страницу после успешного логина
        mainPage.checkUrlPage();

        //Кликнуть "Личный кабинет"
        mainPage.clickPersonalAccountButton();

        // Проверить переход на страницу личного кабинета
        accountProfilePage.checkUrlPage();

        // Проверить наличия элементов на странице личного кабинета
        accountProfilePage.checkAccountProfilePageElementsIsVisible();

        // Проверить значений в полях "Имя", "Email"
        accountProfilePage.checkAccountCredentialsFieldsValue(user);
    }
}
