package com;

import com.model.BasePage;
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
public class LogoutPositiveTest extends BaseSettings {

    private MainPage mainPage;
    private LoginPage loginPage;
    private AccountProfilePage accountProfilePage;
    private User user;
    private UserMethods userMethods;
    private String accessToken;
    private BasePage basePage;
    private final String browser;

    public LogoutPositiveTest(String browser) {
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
        basePage = new BasePage();

        // Создание пользователя
        user = User.getRandom();
        accessToken = basePage.createUser(user, userMethods);

        // Открытие браузера, страницы логина
        openLoginPage();

        // Логин пользователя
        loginUser(user);
    }

    @After
    public void tearDownUser() {
        // Удаление пользователя
        basePage.deleteUser(accessToken, userMethods);
    }

    @Step("Open login page")
    public void openLoginPage() {
        // открыть браузер
        loginPage = open(LoginPage.LOGIN_URL, LoginPage.class);

        mainPage = page(MainPage.class);
        accountProfilePage = page(AccountProfilePage.class);

        //Проверка перехода на страницу логина
        basePage.checkUrlPage(LoginPage.LOGIN_URL);
    }

    @Step("Login user")
    public void loginUser(User user) {
        // Логин пользователя
        loginPage.login(user);
    }


    @Test
    @Description("Test of logout on account profile page")
    public void testTransitionToConstructorPagePositive() {

        // Проверка перехода на главную страницу после успешного логина
        basePage.checkUrlPage(MainPage.BASE_URL);

        //Кликнуть "Личный кабинет"
        mainPage.clickPersonalAccountButton();

        // Проверить переход на страницу личного кабинета
        basePage.checkUrlPage(AccountProfilePage.ACCOUNT_PROFILE_URL);

        //Кликнуть "Выход"
        accountProfilePage.clickLogoutButton();

        //Проверить переход в форму входа
        basePage.checkUrlPage(LoginPage.LOGIN_URL);
        loginPage.checkLoginPageElementsIsVisible();
    }
}
