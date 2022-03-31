package com;

import com.model.BaseSettings;
import io.qameta.allure.Description;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static com.codeborne.selenide.Selenide.open;

@RunWith(Parameterized.class)
public class ConstructorTabsSwitchPositiveTest extends BaseSettings {

    private final String browser;
    private MainPage mainPage;

    public ConstructorTabsSwitchPositiveTest(String browser) {
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

        // открыть браузер
        mainPage = open(MainPage.BASE_URL, MainPage.class);

        //Проверка перехода на главную страницу и её загрузки
        checkUrlPage(MainPage.BASE_URL);
        mainPage.checkTextOnMainPage();
    }


    @Test
    @Description("Test of switch in constructor tab Sauce")
    public void testConstructorTabsSwitchSaucePositive() {

        //Проверка перехода на главную страницу
        checkUrlPage(MainPage.BASE_URL);

        //Клик по вкладке конструктора "Соусы"
        mainPage.clickSauceButton();

        //Проверка, что первыми отображаются соусы
        mainPage.checkSauceIngredientText();
    }

    @Test
    @Description("Test of switch in constructor tab Staff")
    public void testConstructorTabsSwitchStaffPositive() {

        //Проверка перехода на главную страницу
        checkUrlPage(MainPage.BASE_URL);

        //Клик по вкладке конструктора "Начинки"
        mainPage.clickStaffButton();

        //Проверка, что первыми отображаются начинки
        mainPage.checkStuffIngredientText();
    }

    @Test
    @Description("Test of switch in constructor tab Bun")
    public void testConstructorTabsSwitchBunPositive() {

        //Проверка перехода на главную страницу
        checkUrlPage(MainPage.BASE_URL);

        //Клик по вкладке конструктора "Соусы"
        mainPage.clickSauceButton();

        //Клик по вкладке конструктора "Булки"
        mainPage.clickBunButton();

        //Проверка, что первыми отображаются булки
        mainPage.checkBunIngredientText();
    }
}
