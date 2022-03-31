package com;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.model.User;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static org.hamcrest.CoreMatchers.containsString;


public class AccountProfilePage {

    public final static String ACCOUNT_PROFILE_URL = "https://stellarburgers.nomoreparties.site/account/profile";

    // ---------- Локаторы

    //локатор кнопки "Профиль"
    @FindBy(how = How.CSS, using = "a.Account_link__2ETsJ.text.text_type_main-medium.text_color_inactive.Account_link_active__2opc9")
    private SelenideElement profileButton;

    //локатор кнопки "История заказов"
    @FindBy(how = How.CSS, using = "a.Account_link__2ETsJ.text.text_type_main-medium.text_color_inactive")
    private SelenideElement orderHistoryButton;

    //локатор кнопки "Выход"
    @FindBy(how = How.CSS, using = "button.Account_button__14Yp3.text.text_type_main-medium.text_color_inactive")
    private SelenideElement logoutButton;

    //локатор кнопки "Отмена"
    @FindBy(how = How.CSS, using = "button.button_button__33qZ0.button_button_type_secondary__3-tsA.button_button_size_medium__3zxIa")
    private SelenideElement cancelButton;

    //локатор кнопки "Сохранить"
    @FindBy(how = How.CSS, using = "button.button_button__33qZ0.button_button_type_primary__1O7Bx.button_button_size_medium__3zxIa")
    private SelenideElement saveButton;

    //локатор кнопки "Конструктор"
    @FindBy(how = How.CSS, using = "p.AppHeader_header__linkText__3q_va.ml-2")
    private SelenideElement constructorButton;

    //локатор логотипа StellarBurgers
    @FindBy(how = How.XPATH, using = ".//div[@class='AppHeader_header__logo__2D0X2']/a")
    private SelenideElement stellarBurgersLogo;


    //локатор поля "Имя"
    @FindBy(how = How.XPATH, using = ".//li[1]/div/div/input")
    private SelenideElement nameField;

    //локатор лейбла поля "Email"
    @FindBy(how = How.XPATH, using = ".//li[2]/div/div/input")
    private SelenideElement emailField;

    //локатор поля "Пароль"
    @FindBy(how = How.XPATH, using = ".//li[3]/div/div/input")
    private SelenideElement passwordField;


    // ---------- Методы

    /**
     * Клик по кнопке "Выход" в личном кабинете
     */
    public void clickLogoutButton() {
        logoutButton.shouldBe(Condition.visible);
        logoutButton.click();
    }

    /**
     * Клик по кнопке "Конструктор" в личном кабинете
     */
    public void clickConstructorButton() {
        constructorButton.shouldBe(Condition.visible);
        constructorButton.click();
    }

    /**
     * Клик по логотипа StellarBurgers
     */
    public void clickStellarBurgersLogo() {
        stellarBurgersLogo.shouldBe(Condition.visible);
        stellarBurgersLogo.click();
    }

    /**
     * Проверка отображение элементов на странице личного кабинета
     */
    public void checkAccountProfilePageElementsIsVisible() {
        nameField.is(Condition.visible);
        emailField.is(Condition.visible);
        passwordField.is(Condition.visible);
        profileButton.is(Condition.visible);
        orderHistoryButton.is(Condition.visible);
        logoutButton.is(Condition.visible);
        cancelButton.is(Condition.visible);
        saveButton.is(Condition.visible);
    }

    /**
     * Проверка значений в полях "Имя", "Email" в личном кабинете.
     */
    public void checkAccountCredentialsFieldsValue(User user) {
        MatcherAssert.assertThat("User name is correct", nameField.getValue(), containsString(user.name));
        MatcherAssert.assertThat("User email is correct", emailField.getValue(), containsString((user.email).toLowerCase()));
    }
}
