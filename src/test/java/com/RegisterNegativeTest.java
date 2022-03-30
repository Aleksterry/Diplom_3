package com;

import com.model.BasePage;
import com.model.BaseSettings;
import com.model.User;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static com.codeborne.selenide.Selenide.open;

@RunWith(Parameterized.class)
public class RegisterNegativeTest extends BaseSettings {

    private RegisterPage registerPage;
    private BasePage basePage;
    private final String browser;

    public RegisterNegativeTest(String browser) {
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
    @Step("Open register page")
    public void openRegisterPage() {
        init(browser);

        basePage = new BasePage();

        // открыть браузер
        registerPage = open(RegisterPage.REGISTER_URL, RegisterPage.class);

        //Проверка перехода на страницу регистрации
        basePage.checkUrlPage(RegisterPage.REGISTER_URL);
        // Проверить наличие элементов на странице регистрации
        registerPage.checkRegisterPageElementsIsVisible();
    }

    @Test
    @Description("Test of login with error in password - negative")
    public void testRegisterNegative() {

        // Заполнить форму регистрации данными
        User user = User.getRandomWithoutPass("12345");
        registerPage.fillRegisterFields(user);

        // Клик по кнопке "Зарегистрировать"
        registerPage.clickRegisterButton();

        //Проверка вывода сообщения об ошибке
        registerPage.checkPasswordFieldErrorText();

        //Проверка, что страница не изменилась
        basePage.checkUrlPage(RegisterPage.REGISTER_URL);
        registerPage.checkTextOnRegisterPage();
    }
}
