package org.searchTests;

import org.baseTest.BaseTest;
import org.pages.SearchResultsPage;
import org.testng.Assert;
import org.testng.annotations.Test;



public class SuccessfulSearchTest extends BaseTest {

    @Test
    public void testSuccessfulSearch() {
        // Крок 1. Відкрити головну сторінку
        homePage.openHomePage();
        Assert.assertTrue(homePage.isSearchFieldVisible(),
                "Search field is not visible on HomePage!");

        // Крок 2. Ввести валідний текст
        homePage.enterSearchQuery("Ореста Левицького");

        // Крок 3. Натиснути кнопку пошуку
        SearchResultsPage resultsPage = homePage.clickSearchButton();

        // Крок 4. Перевірити наявність результатів
        Assert.assertTrue(resultsPage.isSuccessfulSearchDisplayed("Ореста Левицького"),
                "Expected search results not displayed!");
    }
}