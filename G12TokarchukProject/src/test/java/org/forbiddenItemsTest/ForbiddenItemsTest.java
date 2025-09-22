package org.forbiddenItemsTest;

import org.baseTest.BaseTest;
import org.pages.ForbiddenItemsPage;
import org.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ForbiddenItemsTest extends BaseTest {
    @Test
    public void testFoodAccordionOpens() {
        HomePage homePage = new HomePage(driver);
        homePage.openHomePage();

        homePage.clickSendMenu();
        ForbiddenItemsPage forbiddenItemsPage = homePage.clickAllowedItems();

        forbiddenItemsPage.clickFoodAccordion();

        Assert.assertTrue(forbiddenItemsPage.isFoodAccordionOpened(),
                "Акордеон 'Продукти харчування' не відкрився!");
    }
}
