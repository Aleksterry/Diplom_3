package com;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.enabled;
import static org.hamcrest.CoreMatchers.containsString;


public class MainPage {

    public static final String BASE_URL = "https://stellarburgers.nomoreparties.site/";

    // ---------- Локаторы


    //локатор кнопки "Войти в аккаунт"
    @FindBy(how = How.CSS, using = "button.button_button_size_large__G21Vg,.button_button_size_medium__3zxIa")
    private SelenideElement loginInAccountButton;

    //локатор кнопки "Личный кабинет"
    @FindBy(how = How.XPATH, using = ".//p [contains(text(),'Личный Кабинет')]")
    private SelenideElement personalAccountButton;

    //локатор текста "Соберите бургер"
    @FindBy(how = How.XPATH, using = ".//h1 [contains(text(),'Соберите бургер')]")
    private SelenideElement textGatherBurger;

    //локатор кнопки выбора списка булок
    @FindBy(how = How.XPATH, using = ".//span [contains(text(),'Булки')]")
    private SelenideElement bunButton;

    //локатор кнопки выбора списка соусов
    @FindBy(how = How.XPATH, using = ".//span [contains(text(),'Соусы')]")
    private SelenideElement sauceButton;

    //локатор кнопки выбора списка начинок
    @FindBy(how = How.XPATH, using = ".//span [contains(text(),'Начинки')]")
    private SelenideElement staffButton;

    //локатор первого элемента в спсике ингридиентов
    @FindBy(how = How.CSS, using = "div.tab_tab__1SPyG.tab_tab_type_current__2BEPc.pt-4.pr-10.pb-4.pl-10.noselect")
    private SelenideElement currentTab;


    // ---------- Методы

    /**
     * Клик по кнопке "Войти в аккаунт" на основной странице
     */
    public void clickLoginInAccountButton() {
        loginInAccountButton.shouldBe(Condition.visible);
        loginInAccountButton.click();
    }

    /**
     * Клик по кнопке "Личный кабинет" на основной странице
     */
    public void clickPersonalAccountButton() {
        personalAccountButton.shouldBe(Condition.visible);
        personalAccountButton.click();
    }

    /**
     * Клик по табе "Булки" на основной странице
     */
    public void clickBunButton() {
        bunButton.shouldBe(Condition.visible);
        bunButton.shouldBe(enabled).click();
    }

    /**
     * Клик по табе "Соусы" на основной странице
     */
    public void clickSauceButton() {
        sauceButton.shouldBe(Condition.visible);
        sauceButton.shouldBe(enabled).click();
    }

    /**
     * Клик по табе "Начинки" на основной странице
     */
    public void clickStaffButton() {
        staffButton.shouldBe(Condition.visible);
        staffButton.click();
    }

    /**
     * Проверка перехода на основную страницу
     */
    public void checkTextOnMainPage() {
        String expected = "Соберите бургер";
        MatcherAssert.assertThat("Text on page is correct", textGatherBurger.getText(), containsString(expected));
    }

    /**
     * Проверка текста кнопки "Оформить заказ"
     */
    public void checkPlaceOrderButtonText() {
        MatcherAssert.assertThat("Text on button is correct", loginInAccountButton.getText(), containsString("Оформить заказ"));
    }

    /**
     * Проверка текста ингридиента "булка" в конструкторе
     */
    public void checkBunIngredientText() {
        MatcherAssert.assertThat("Text of tab is correct", currentTab.getText(), containsString("Булки"));
    }

    /**
     * Проверка текста ингридиента "соус" в конструкторе
     */
    public void checkSauceIngredientText() {
        MatcherAssert.assertThat("Text of tab is correct", currentTab.getText(), containsString("Соусы"));
    }

    /**
     * Проверка текста ингридиента "начинка" в конструкторе
     */
    public void checkStuffIngredientText() {
        MatcherAssert.assertThat("Text of tab is correct", currentTab.getText(), containsString("Начинки"));
    }


}
