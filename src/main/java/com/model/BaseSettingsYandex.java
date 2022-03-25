package com.model;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

abstract public class BaseSettingsYandex {

    WebDriver driver;
    String browser;

    public void setUpChrome() {
        WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.chrome.driver", "src/resources/chromedriver.exe");
        driver = new ChromeDriver();
        setWebDriver(driver);
        driver.manage().window().maximize();
    }

    public void setUpYandex() {
        //System.setProperty("webdriver.chrome.driver", "src/resources/yandexdriver.exe");
        //WebDriver driver = new ChromeDriver();
        //setWebDriver(driver);
    }

    public void setUpFireFox() {
        WebDriverManager.firefoxdriver().setup();
        System.setProperty("webdriver.chrome.driver", "src/resources/geckodriver.exe");
        driver = new FirefoxDriver();
        setWebDriver(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    /*
    @BeforeAll
    public void init(String browser) {
        if (browser == "chrome") {
            setUpChrome();
        } else {
            setUpFireFox();
        }
    }
    */


    @Before
    public void init() {
        //setUpChrome();
        setUpFireFox();
    }

    @After
    public void tearDown() {
        driver.close();
    }


}


