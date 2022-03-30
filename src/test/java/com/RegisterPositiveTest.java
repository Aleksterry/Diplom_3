package com;

import com.model.*;
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
public class RegisterPositiveTest extends BaseSettings {

    private MainPage mainPage;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private UserMethods userMethods;
    private String accessToken;
    private BasePage basePage;
    private final String browser;

    public RegisterPositiveTest(String browser) {
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
        basePage = new BasePage();

        // открыть браузер
        mainPage = open(MainPage.BASE_URL, MainPage.class);

        loginPage = page(LoginPage.class);
        registerPage = page(RegisterPage.class);

        //Проверка перехода на главную страницу и её загрузки
        basePage.checkUrlPage(MainPage.BASE_URL);
        mainPage.checkTextOnMainPage();
    }

    @After
    public void tearDownUser() {
        // Удаление пользователя
        basePage.deleteUser(accessToken, userMethods);
    }

    @Step("Get user accessToken")
    public void getUserAccessToken(User user) {
        userMethods = new UserMethods();

        // Запись accessToken пользователя для последующего удаления
        accessToken = userMethods.loginUser(new UserCredentials(user.password, user.email)).extract().path("accessToken");
    }

    @Test
    @Description("User has been successfully registered")
    public void testRegisterPositive() {

        //Кликнуть "Войти в аккаунт"
        mainPage.clickLoginInAccountButton();

        // Проверить переход  и загрузку страницы логина
        basePage.checkUrlPage(LoginPage.LOGIN_URL);
        loginPage.checkTextOnLoginPage();

        // Кликнуть "Зарегистироваться"
        loginPage.clickRegisterButton();

        //Проверка перехода на страницу регистрации
        basePage.checkUrlPage(RegisterPage.REGISTER_URL);

        // Проверить наличие элементов на странице регистрации
        registerPage.checkRegisterPageElementsIsVisible();

        // Заполнить форму регистрации данными
        User user = User.getRandom();
        registerPage.fillRegisterFields(user);

        // Клик по кнопке "Зарегистрироваться"
        registerPage.clickRegisterButton();

        // Проверка перехода на страницу входа
        basePage.checkUrlPage(LoginPage.LOGIN_URL);
        loginPage.checkTextOnLoginPage();

        //Вход под зарегистрированным пользователем
        loginPage.login(user);

        //Проверка перехода на главную страницу после входа
        basePage.checkUrlPage(MainPage.BASE_URL);
        mainPage.checkTextOnMainPage();

        // Проверка, что кнопка "Войти в аккаунт" изменилась на "Оформить заказ" - вход успешно совершен
        mainPage.checkPlaceOrderButtonText();

        //Получить токен зарегистрированного пользователя для последующего удаления
        getUserAccessToken(user);
    }
}
