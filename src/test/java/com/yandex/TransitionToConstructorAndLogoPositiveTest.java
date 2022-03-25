package com.yandex;

import com.AccountProfilePage;
import com.LoginPage;
import com.MainPage;
import com.model.BaseSettingsYandex;
import com.model.TearDownUser;
import com.model.User;
import com.model.UserMethods;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;


public class TransitionToConstructorAndLogoPositiveTest extends BaseSettingsYandex {

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
        // Открытие браузера, страницы логина
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
    @Description("Test of transition to constructor from account profile page though \"Constructor\" button")
    public void testTransitionToConstructorPagePositive() {

        // Проверка перехода на главную страницу после успешного логина
        mainPage.checkUrlPage();

        //Кликнуть "Личный кабинет"
        mainPage.clickPersonalAccountButton();

        // Проверить переход на страницу личного кабинета
        accountProfilePage.checkUrlPage();

        //Кликнуть "Конструктор"
        accountProfilePage.clickConstructorButton();

        // Проверить переход на главную страницу
        mainPage.checkUrlPage();
    }

    @Test
    @Description("Test of transition to constructor from account profile page though Stellar Burgers logo")
    public void testTransitionToStellarBurgersLogoPositive() {

        // Проверка перехода на главную страницу после успешного логина
        mainPage.checkUrlPage();

        //Кликнуть "Личный кабинет"
        mainPage.clickPersonalAccountButton();

        // Проверить переход на страницу личного кабинета
        accountProfilePage.checkUrlPage();

        //Кликнуть на логотип StellarBurgers
        accountProfilePage.clickStellarBurgersLogo();

        // Проверить переход на главную страницу
        mainPage.checkUrlPage();
    }
}
