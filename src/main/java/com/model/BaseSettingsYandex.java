package com.model;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

abstract public class BaseSettingsYandex {

    WebDriver driver;

    public void setUpYandex() {
        System.setProperty("webdriver.chrome.driver", "src/resources/yandexdriver.exe");
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver();
        setWebDriver(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Before
    public void init() {
        setUpYandex();
    }

    @After
    public void tearDown() {
        driver.close();
    }


}


