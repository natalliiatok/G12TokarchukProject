package org.searchTests;

import org.baseTest.BaseTest;
import org.pages.SearchResultsPage;
import org.testng.annotations.Test;
import org.testng.Assert;



public class UnsuccessfulSearchTest extends BaseTest {

    @Test
    public void testUnsuccessfulSearch() {
        // Крок 1. Відкрити головну сторінку
        homePage.openHomePage();
        Assert.assertTrue(homePage.isSearchFieldVisible(),
                "Search field is not visible on HomePage!");

        // Крок 2. Ввести текст
        homePage.enterSearchQuery("Стопщкоу");

        // Крок 3. Натиснути кнопку пошуку
        SearchResultsPage resultsPage = homePage.clickSearchButton();

        // Крок 4. Перевірити відсутність результатів
        String expectedMessage = "На жаль, за вашим запитом немає результатів";
        Assert.assertTrue(resultsPage.isUnsuccessfulSearchDisplayed(expectedMessage),
                "Expected message about no results not displayed!");
    }
}