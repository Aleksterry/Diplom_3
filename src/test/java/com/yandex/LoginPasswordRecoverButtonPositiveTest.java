package com.yandex;

import com.LoginPage;
import com.MainPage;
import com.PasswordRecoverPage;
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


public class LoginPasswordRecoverButtonPositiveTest extends BaseSettingsYandex {

    private MainPage mainPage;
    private PasswordRecoverPage passwordRecoverPage;
    private LoginPage loginPage;
    private User user;
    private UserMethods userMethods;
    private String accessToken;
    private TearDownUser tearDownUser;

    @Before
    public void setup() {
        // Создание пользователя
        createUser();
        // Открытие браузера, страницы восстановления пароля
        openPasswordRecoverPage();
    }


    @Step("Before test: open password recover page")
    public void openPasswordRecoverPage() {
        passwordRecoverPage = null;
        loginPage = page(LoginPage.class);
        mainPage = page(MainPage.class);

        // открыть браузер
        passwordRecoverPage = open(PasswordRecoverPage.PASSWORD_RECOVER_URL, PasswordRecoverPage.class);

        // Проверить переход на страницу восстановления пароля
        passwordRecoverPage.checkUrlPage();
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
    @Description("Test of login through \"Login\" button on password recover page")
    public void testLoginPasswordRecoverButtonPositive() {

        //Кликнуть "Войти" на форме восстановления пароля
        passwordRecoverPage.clickLoginButton();

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
