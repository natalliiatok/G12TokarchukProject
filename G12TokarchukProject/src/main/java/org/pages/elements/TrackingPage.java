package org.pages.elements;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class TrackingPage extends CommonActionsWithElements {

    private final By routeDetailsHeader = By.xpath("//h2[contains(normalize-space(), 'Деталі маршруту')]");


    public TrackingPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isRouteDetailsVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(routeDetailsHeader));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}