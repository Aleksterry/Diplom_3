package com;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.model.User;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static org.hamcrest.CoreMatchers.startsWith;

public class RegisterPage {

    public final static String REGISTER_URL = "https://stellarburgers.nomoreparties.site/register";


    // ---------- Локаторы

    //локатор кнопки "Зарегистрироваться"
    @FindBy(how = How.CLASS_NAME, using = "button_button__33qZ0")
    private SelenideElement registerButton;

    //локатор кнопки "Войти"
    @FindBy(how = How.CLASS_NAME, using = "Auth_link__1fOlj")
    private SelenideElement loginButton;

    //локатор поля "Имя"
    @FindBy(how = How.XPATH, using = ".//fieldset[1]/div/div/input")
    private SelenideElement nameField;

    //локатор поля "Email"
    @FindBy(how = How.XPATH, using = ".//fieldset[2]/div/div/input")
    private SelenideElement emailField;

    //локатор поля "Пароль"
    @FindBy(how = How.XPATH, using = ".//fieldset[3]/div/div/input")
    private SelenideElement passwordField;

    //локатор текста "Регистриция"
    @FindBy(how = How.CLASS_NAME, using = "Auth_login__3hAey")
    private SelenideElement textOnRegisterPage;

    //локатор текста Ошибки в поле "Пароль"
    @FindBy(how = How.CLASS_NAME, using = "input__error")
    private SelenideElement passwordFieldErrorText;


    // ---------- Методы

    /**
     * Клик по кнопке "Зарегестрироваться" на странице регристрации
     */
    public void clickRegisterButton() {
        registerButton.click();
    }

    /**
     * Клик по кнопке "Войти" на странице регристрации
     */
    public void clickLoginButton() {
        loginButton.click();
    }

    /**
     * Заполнение данными поля "Имя"
     */
    public void fillNameField(User user) {
        nameField.click();
        nameField.clear();
        nameField.setValue(user.name);
    }

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
     * Заполнение данными формы регистрации
     */
    public void fillRegisterFields(User user) {
        fillNameField(user);
        fillEmailField(user);
        fillPasswordField(user);
    }

    /**
     * Заполнение данными формы регистрации
     */
    public void registerUser(User user) {
        fillNameField(user);
        fillEmailField(user);
        fillPasswordField(user);
        clickRegisterButton();
    }


    /**
     * Проверка наличия элементов на странице регистрации
     */
    public void checkRegisterPageElementsIsVisible() {
        emailField.is(Condition.visible);
        nameField.is(Condition.visible);
        passwordField.is(Condition.visible);
        registerButton.is(Condition.visible);
        checkTextOnRegisterPage();
    }

    /**
     * Проверка текста в шапке страницы
     */
    public void checkTextOnRegisterPage() {
        String expected = "Регистрация";
        MatcherAssert.assertThat("Text on page is correct", textOnRegisterPage.getText(), startsWith(expected));
    }

    /**
     * Проверка текста ошибки
     */
    public void checkPasswordFieldErrorText() {
        MatcherAssert.assertThat("Error text of password field is correct", passwordFieldErrorText.getText(), startsWith("Некорректный пароль"));
    }
}
