package com.yandex;

import com.RegisterPage;
import com.model.BaseSettingsYandex;
import com.model.User;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;


public class RegisterNegativeTest extends BaseSettingsYandex {

    RegisterPage registerPage;

    @Before
    @Step("Before test: open register page")
    public void openRegisterPage() {

        // открыть браузер
        registerPage = null;
        registerPage = open(RegisterPage.REGISTER_URL, RegisterPage.class);

        //Проверка перехода на страницу регистрации
        registerPage.checkUrlPage();
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
        registerPage.checkUrlPage();
        registerPage.checkTextOnRegisterPage();
    }

}
