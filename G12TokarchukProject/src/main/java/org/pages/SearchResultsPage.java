package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pages.elements.CommonActionsWithElements;

import java.time.Duration;

public class SearchResultsPage extends CommonActionsWithElements {

    @FindBy(xpath = "//div[contains(@class,'text-hd-36') and contains(text(),'Ореста')]")
    private WebElement successfulSearchResult;

    @FindBy(xpath = "//div[text()='На жаль, за вашим запитом немає результатів']")
    private WebElement unsuccessfulSearchMessage;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isSuccessfulSearchDisplayed(String expectedText) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(successfulSearchResult));
            String actualText = successfulSearchResult.getText().trim().toLowerCase();
            System.out.println("DEBUG: actualText = " + actualText); // 👈 Додай для дебагу
            return actualText.contains(expectedText.toLowerCase());
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isUnsuccessfulSearchDisplayed(String expectedText) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            // Чекати, поки елемент видимий і текст з'явиться
            wait.until(ExpectedConditions.visibilityOf(unsuccessfulSearchMessage));
            wait.until(ExpectedConditions.textToBePresentInElement(unsuccessfulSearchMessage, expectedText));

            String actualText = unsuccessfulSearchMessage.getText().trim().toLowerCase();
            System.out.println("DEBUG: actualText = " + actualText); // 👈 для дебагу
            return actualText.contains(expectedText.toLowerCase());
        } catch (Exception e) {
            System.out.println("DEBUG: Message not displayed or text mismatch");
            return false;
        }
    }
}