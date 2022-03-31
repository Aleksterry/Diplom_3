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
public class LoginRegisterButtonPositiveTest extends BaseSettings {


    private final String browser;
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private User user;
    private UserMethods userMethods;
    private String accessToken;

    public LoginRegisterButtonPositiveTest(String browser) {
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

        // Открытие браузера, страницы регистрацмм
        openRegisterPage();
    }

    @After
    public void tearDownUser() {
        // Удаление пользователя
        deleteUser(accessToken, userMethods);
    }


    @Step("Open register page")
    public void openRegisterPage() {
        loginPage = page(LoginPage.class);
        mainPage = page(MainPage.class);

        // открыть браузер
        registerPage = open(RegisterPage.REGISTER_URL, RegisterPage.class);

        //Проверка перехода на страницу регистрации
        checkUrlPage(RegisterPage.REGISTER_URL);
    }


    @Test
    @Description("Test of login through \"Login\" button on register page")
    public void testLoginRegisterButtonPositive() {

        //Кликнуть "Войти" на форме регистрации
        registerPage.clickLoginButton();

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
