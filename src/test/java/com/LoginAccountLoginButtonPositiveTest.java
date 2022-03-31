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
public class LoginAccountLoginButtonPositiveTest extends BaseSettings {

    private final String browser;
    private MainPage mainPage;
    private LoginPage loginPage;
    private User user;
    private UserMethods userMethods;
    private String accessToken;

    public LoginAccountLoginButtonPositiveTest(String browser) {
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
    public void setUp() {
        init(browser);

        userMethods = new UserMethods();

        // Создание пользователя
        user = User.getRandom();
        accessToken = createUser(user, userMethods);

        // Открытие браузера
        openMainPage();
    }

    @After
    public void tearDownUser() {
        // Удаление пользователя
        deleteUser(accessToken, userMethods);
    }


    @Step("Open main page")
    public void openMainPage() {
        loginPage = page(LoginPage.class);

        // открыть браузер
        mainPage = open(MainPage.BASE_URL, MainPage.class);

        //Проверка перехода на главную страницу
        checkUrlPage(MainPage.BASE_URL);

        //Проверка загрузки главной страницы
        mainPage.checkTextOnMainPage();
    }


    @Test
    @Description("Test of login through \"Login account\" button on main page")
    public void testLoginAccountLoginButtonPositive() {

        //Кликнуть "Войти в аккаунт"
        mainPage.clickLoginInAccountButton();

        // Проверить переход на страницу логина
        checkUrlPage(LoginPage.LOGIN_URL);

        // Проверить наличия элементов на странице входа
        loginPage.checkLoginPageElementsIsVisible();

        // Заполнение полей логина и пароля
        loginPage.fillLoginFields(user);

        // Кликнуть "Войти"
        loginPage.clickLoginButton();

        // Проверка, что вход совершен
        checkUrlPage(MainPage.BASE_URL);
        mainPage.checkTextOnMainPage();
        mainPage.checkPlaceOrderButtonText();
    }
}
