package org.pages;

import org.openqa.selenium.WebDriver;

public class PageProvider {
    private WebDriver driver;
    private HomePage homePage;
    private SearchResultsPage searchResultsPage;

    public PageProvider(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage(driver);
        }
        return homePage;
    }

    public SearchResultsPage getSearchResultsPage() {
        if (searchResultsPage == null) {
            searchResultsPage = new SearchResultsPage(driver);
        }
        return searchResultsPage;
    }
}

