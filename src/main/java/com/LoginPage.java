package com;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.model.User;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static org.hamcrest.CoreMatchers.startsWith;

public class LoginPage {

    public final static String LOGIN_URL = "https://stellarburgers.nomoreparties.site/login";


    // ---------- Локаторы

    //локатор кнопки "Войти"
    @FindBy(how = How.CLASS_NAME, using = "button_button__33qZ0")
    private SelenideElement loginButton;

    //локатор кнопки "Восстановить пароль"
    @FindBy(how = How.CLASS_NAME, using = "Auth_link__1fOlj")
    private SelenideElement passwordRecoverButton;

    //локатор кнопки "Зарегестрироваться"
    @FindBy(how = How.CLASS_NAME, using = "Auth_link__1fOlj")
    private SelenideElement registerButton;

    //локатор поля "Email"
    @FindBy(how = How.XPATH, using = ".//fieldset[1]/div/div/input")
    private SelenideElement emailField;

    //локатор поля "Пароль"
    @FindBy(how = How.XPATH, using = ".//fieldset[2]/div/div/input")
    private SelenideElement passwordField;

    //локатор текста "Вход"
    @FindBy(how = How.CLASS_NAME, using = "Auth_login__3hAey")
    private SelenideElement textOnLoginPage;


    // ---------- Методы

    /**
     * Заполнение данными поля "Email"
     */
    public void fillEmailField(User user) {
        emailField.click();
        emailField.clear();
        emailField.setValue(user.email);
    }

    /**
     * Заполнение данными поля "Пароль"
     */
    public void fillPasswordField(User user) {
        passwordField.click();
        passwordField.clear();
        passwordField.setValue(user.password);
    }

    /**
     * Заполнение данными формы входа
     */
    public void fillLoginFields(User user) {
        fillEmailField(user);
        fillPasswordField(user);
    }

    /**
     * Вход
     */
    public void login(User user) {
        fillEmailField(user);
        fillPasswordField(user);
        loginButton.click();
    }

    /**
     * Клик по кнопке "Войти на странице входа"
     */
    public void clickLoginButton() {
        loginButton.click();
    }

    /**
     * Клик по кнопке "Зарегестрироваться" на странице входа
     */
    public void clickRegisterButton() {
        registerButton.click();
    }

    /**
     * Клик по кнопке "Восстановить пароль" на странице входа
     */
    public void clickPasswordRecoverButton() {
        passwordRecoverButton.click();
    }

    /**
     * Проверка перехода на страницу входа
     */
    public void checkTextOnLoginPage() {
        String expected = "Вход";
        MatcherAssert.assertThat("Text on page is correct", textOnLoginPage.getText(), startsWith(expected));
    }

    /**
     * Проверка загрузки элементов страницы входа
     */
    public void checkLoginPageElementsIsVisible() {
        emailField.is(Condition.visible);
        passwordField.is(Condition.visible);
        checkTextOnLoginPage();
    }
}
