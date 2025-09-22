package org.pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommonActionsWithElements {
    protected WebDriver driver;

    public CommonActionsWithElements(WebDriver driver) {
        this.driver = driver;
    }

    protected void enterText(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    protected void click(WebElement element) {
        element.click();
    }

    protected String getText(WebElement element) {
        return element.getText();
    }

    protected boolean isElementDisplayed(WebElement element) {
        return element.isDisplayed();
    }

    protected void waitAndSendKeys(WebElement element, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(element));
        el.clear();
        el.sendKeys(text);
    }

    protected void waitAndClick(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(element));
        el.click();
    }

    protected boolean waitForVisibility(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
