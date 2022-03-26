package com.model;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

abstract public class BaseSettingsChrome {

    WebDriver driver;

    public void setUpChrome() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        setWebDriver(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Before
    public void init() {
        setUpChrome();
    }

    @After
    public void tearDown() {
        driver.close();
    }
}


