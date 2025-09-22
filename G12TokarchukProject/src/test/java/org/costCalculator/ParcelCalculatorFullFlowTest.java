package org.costCalculator;

import org.baseTest.BaseTest;
import org.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ParcelCalculatorFullFlowTest extends BaseTest {
    @Test
    public void fullFlowParcelCalculator() {
        HomePage homePage = new HomePage(driver);

        // --- Крок 1: головна сторінка ---
        homePage.openHomePage();
        homePage.fillMainForm("Київ", "Львів");
        homePage.clickContinue();

        // --- Крок 2: модальне вікно (iframe) ---
        homePage.switchToModalIframe();
        homePage.enterDeclaredValue("500");
        homePage.clickCalculate();

        homePage.switchBackToDefault();

        Assert.assertTrue(homePage.isCostBlockDisplayedAnywhere(),
                "Блок з орієнтовною вартістю не відображається!");
    }
}