package com.chrome;

import com.MainPage;
import com.model.BaseSettingsChrome;
import io.qameta.allure.Description;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;


public class ConstructorTabsSwitchPositiveTest extends BaseSettingsChrome {

    private MainPage mainPage;

    @Before
    public void setup() {
        // открыть браузер
        mainPage = null;
        mainPage = open(MainPage.BASE_URL, MainPage.class);

        //Проверка перехода на главную страницу и её загрузки
        mainPage.checkUrlPage();
        mainPage.checkTextOnMainPage();
    }


    @Test
    @Description("Test of switch in constructor tab Sauce")
    public void testConstructorTabsSwitchSaucePositive() {

        //Проверка перехода на главную страницу
        mainPage.checkUrlPage();

        //Клик по вкладке конструктора "Соусы"
        mainPage.clickSauceButton();

        //Проверка, что первыми отображаются соусы
        mainPage.checkSauceIngredientText();
    }

    @Test
    @Description("Test of switch in constructor tab Staff")
    public void testConstructorTabsSwitchStaffPositive() {

        //Проверка перехода на главную страницу
        mainPage.checkUrlPage();

        //Клик по вкладке конструктора "Начинки"
        mainPage.clickStaffButton();

        //Проверка, что первыми отображаются начинки
        mainPage.checkStuffIngredientText();
    }

    @Test
    @Description("Test of switch in constructor tab Bun")
    public void testConstructorTabsSwitchBunPositive() {

        //Проверка перехода на главную страницу
        mainPage.checkUrlPage();

        //Клик по вкладке конструктора "Соусы"
        mainPage.clickSauceButton();

        //Клик по вкладке конструктора "Булки"
        mainPage.clickBunButton();

        //Проверка, что первыми отображаются булки
        mainPage.checkBunIngredientText();
    }
}
