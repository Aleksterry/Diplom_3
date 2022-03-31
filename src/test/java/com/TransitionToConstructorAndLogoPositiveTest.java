package com;

import com.model.BaseSettings;
import com.model.User;
import com.model.UserMethods;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

@RunWith(Parameterized.class)
public class TransitionToConstructorAndLogoPositiveTest extends BaseSettings {

    private final String browser;
    private MainPage mainPage;
    private LoginPage loginPage;
    private AccountProfilePage accountProfilePage;
    private User user;
    private UserMethods userMethods;
    private String accessToken;

    public TransitionToConstructorAndLogoPositiveTest(String browser) {
        this.browser = browser;
    }

    @Parameterized.Parameters
    public static Object[][] getBrowser() {
        return new Object[][]{
                {"chrome"},
                {"yandex"},
        };
    }

    @Before
    public void setup() {
        init(browser);

        userMethods = new UserMethods();

        // Создание пользователя
        user = User.getRandom();
        accessToken = createUser(user, userMethods);
        // Открытие браузера, страницы логина
        openLoginPage();
        // Логин пользователя
        loginUser(user);
    }

    @After
    public void tearDownUser() {
        // Удаление пользователя
        deleteUser(accessToken, userMethods);
    }

    @Step("Open login page")
    public void openLoginPage() {

        // открыть браузер
        loginPage = open(LoginPage.LOGIN_URL, LoginPage.class);

        mainPage = page(MainPage.class);
        accountProfilePage = page(AccountProfilePage.class);

        //Проверка перехода на страницу логина
        checkUrlPage(LoginPage.LOGIN_URL);
    }

    @Step("Login user")
    public void loginUser(User user) {
        // Логин пользователя
        loginPage.login(user);
    }


    @Test
    @Description("Test of transition to constructor from account profile page though \"Constructor\" button")
    public void testTransitionToConstructorPagePositive() {

        // Проверка перехода на главную страницу после успешного логина
        checkUrlPage(MainPage.BASE_URL);

        //Кликнуть "Личный кабинет"
        mainPage.clickPersonalAccountButton();

        // Проверить переход на страницу личного кабинета
        checkUrlPage(AccountProfilePage.ACCOUNT_PROFILE_URL);

        //Кликнуть "Конструктор"
        accountProfilePage.clickConstructorButton();

        // Проверить переход на главную страницу
        checkUrlPage(MainPage.BASE_URL);
    }

    @Test
    @Description("Test of transition to constructor from account profile page though Stellar Burgers logo")
    public void testTransitionToStellarBurgersLogoPositive() {

        // Проверка перехода на главную страницу после успешного логина
        checkUrlPage(MainPage.BASE_URL);

        //Кликнуть "Личный кабинет"
        mainPage.clickPersonalAccountButton();

        // Проверить переход на страницу личного кабинета
        checkUrlPage(AccountProfilePage.ACCOUNT_PROFILE_URL);

        //Кликнуть на логотип StellarBurgers
        accountProfilePage.clickStellarBurgersLogo();

        // Проверить переход на главную страницу
        checkUrlPage(MainPage.BASE_URL);
    }
}
