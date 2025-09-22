package org.baseTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pages.HomePage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.utils.ConfigProvider;

import java.time.Duration;

public class BaseTest { protected WebDriver driver;
    protected HomePage homePage;   // ✅ Додаємо HomePage як protected змінну
    protected WebDriverWait wait;


    @BeforeMethod
    public void setUp() {
        String browser = ConfigProvider.getBrowser();

        switch (browser.toLowerCase()) {
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "chrome":
            default:
                driver = new ChromeDriver();
                break;
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigProvider.getImplicitWait()));
        driver.manage().window().maximize();

        // ✅ створюємо wait, доступний усюди
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // ініціалізація driver + HomePage
        driver.get(ConfigProvider.getBaseUrl());
        homePage = new HomePage(driver);   // ✅ Створюємо HomePage тут
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
