package org.trackingTest;

import org.baseTest.BaseTest;
import org.pages.HomePage;
import org.testng.Assert;

import org.testng.annotations.Test;
import org.pages.elements.TrackingPage;



public class TrackingTest extends BaseTest {

    @Test
    public void testParcelTracking() {
        HomePage homePage = new HomePage(driver);

        TrackingPage trackingPage = homePage.goToTrackingPage("59001453097458");

        Assert.assertTrue(trackingPage.isRouteDetailsVisible(),
                "Елемент 'Деталі маршруту' не відображається!");
    }
}