package com.model;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.WebDriverRunner.setWebDriver;


public class BaseSettings extends BasePage{

    WebDriver driver;

    public void setUpChrome() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        setWebDriver(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    public void setUpYandex() {
        System.setProperty("webdriver.chrome.driver", "src/resources/yandexdriver.exe");
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver();
        setWebDriver(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }


    public void init(String browser) {
        if (browser == "chrome") {
            setUpChrome();
        } else {
            setUpYandex();
        }
    }

    @After
    public void tearDown() {
        driver.close();
    }
}


