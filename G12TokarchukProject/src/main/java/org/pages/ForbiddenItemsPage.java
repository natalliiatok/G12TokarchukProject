package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pages.elements.CommonActionsWithElements;

import java.time.Duration;

public class ForbiddenItemsPage extends CommonActionsWithElements {

    @FindBy(xpath = "//div[contains(@class,'flex-1') and text()='Продукти харчування']")
    private WebElement foodAccordionHeader;

    private final By foodAccordionBody = By.xpath("//div[contains(@class,'accordion-body') and contains(., 'Овочі, фрукти')]");

    public ForbiddenItemsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickFoodAccordion() {
        waitAndClick(foodAccordionHeader);
    }

    public boolean isFoodAccordionOpened() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(foodAccordionBody)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
}

