package com;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

public class PasswordRecoverPage {

    public final static String PASSWORD_RECOVER_URL = "https://stellarburgers.nomoreparties.site/forgot-password";


    // ---------- Локаторы

    //локатор кнопки "Войти"
    @FindBy(how = How.CLASS_NAME, using = "Auth_link__1fOlj")
    private SelenideElement loginButton;


    // ---------- Методы

    /**
     * Клик по кнопке "Войти на странице входа"
     */
    public void clickLoginButton() {
        loginButton.click();
    }

    /**
     * Проверка урла страницы
     */
    public void checkUrlPage() {
        webdriver().shouldHave(url(PASSWORD_RECOVER_URL));
    }
}
