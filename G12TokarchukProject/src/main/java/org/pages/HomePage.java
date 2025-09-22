package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pages.elements.CommonActionsWithElements;
import org.pages.elements.TrackingPage;

import java.time.Duration;

public class HomePage extends CommonActionsWithElements {

    @FindBy(xpath = "//form[contains(@class, 'header-desktop-search-input')]//input[@name='query']")
    private WebElement searchInput;

    @FindBy(xpath = "//button[contains(@class, 'find-button') and text()='Пошук']")
    private WebElement searchButton;

    @FindBy(xpath = "//input[@placeholder='Звідки']")
    private WebElement fromCityInput;

    @FindBy(xpath = "//input[@placeholder='Куди']")
    private WebElement toCityInput;

    @FindBy(xpath = "//button[@plerdy-tracking-id='23213623101']")
    private WebElement continueButton;

    // Модальне вікно (iframe)
    @FindBy(xpath = "//input[@aria-label='Оголошена цінність, ₴']")
    private WebElement declaredValueInput;

    @FindBy(xpath = "//span[text()='Розрахувати']")
    private WebElement calculateButton;

    @FindBy(css = "input[placeholder='Введіть номер посилки']")
    private WebElement trackingInput;

    @FindBy(xpath = "//button[.//span[text()='Відстежити']]")
    private WebElement trackButton;

    @FindBy(xpath = "//li[contains(@class,'nav-item')]//div[text()='Відправити']")
    private WebElement sendMenu;

    @FindBy(xpath = "//div[contains(text(),'Дозволені товари')]")
    private WebElement allowedItemsMenu;

//    @FindBy(xpath = "//div[contains(@class,'sidebar-cost')]//p[contains(@class,'total-cost-text') and text()='Орієнтовна вартість']")
//    private WebElement totalCost;
//
    // --- Блок з орієнтовною вартістю ---
    private final By totalCost = By.xpath("//div[contains(@class,'sidebar-cost')]//p[contains(@class,'total-cost-text') and text()='Орієнтовна вартість']");

    // --- Список міст ---
    private String cityOptionXpath = "//div[contains(@class,'font-medium') and text()='%s']";


    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void openHomePage() {
        driver.get("https://novaposhta.ua/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(searchInput));
    }

    // Метод для введення тексту у поле пошуку
    public void enterSearchQuery(String query) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(searchInput));
        input.sendKeys(query);
    }

    // Метод для натискання кнопки пошуку
    public SearchResultsPage clickSearchButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        button.click();
        return new SearchResultsPage(driver);
    }

    // Метод для перевірки, що поле пошуку видно
    public boolean isSearchFieldVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(searchInput));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void fillMainForm(String fromCity, String toCity) {
        // Клік по полю "Звідки" і вибір міста
        click(fromCityInput);  // відкриваємо список міст
        selectCity(fromCity);  // обираємо місто зі списку

        // Клік по полю "Куди" і вибір міста
        click(toCityInput);    // відкриваємо список міст
        selectCity(toCity);    // обираємо місто зі списку
    }


    public void clickContinue() {
        waitAndClick(continueButton);
    }

    // --- Методи для модального вікна ---
    public void switchToModalIframe() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("iframe")));
        driver.switchTo().frame(iframe);
    }

    public void enterDeclaredValue(String value) {
        waitAndSendKeys(declaredValueInput, value);
    }

    public void clickCalculate() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Чекаємо, поки кнопка стане enabled
        wait.until(driver -> calculateButton.isEnabled());

        // Тепер клікаємо
        calculateButton.click();

        // !!! УВАГА: НЕ виходимо з iframe одразу, поки не впевнимось де блок !!!
    }

    public void switchBackToDefault() {
        driver.switchTo().defaultContent();
    }

    // Локатор для списку міст не обовʼязково зберігати у @FindBy, краще метод з параметром
    public void selectCity(String cityName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement cityOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class,'mt-6')]//li//div[.//div[contains(text(),'" + cityName + "')]]")
        ));
        cityOption.click();
    }

    public boolean isCostBlockDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement element = wait.until(driver -> {
                WebElement el = driver.findElement(totalCost);
                return (el.isDisplayed() && !el.getText().isEmpty()) ? el : null;
            });
            System.out.println("Блок орієнтовної вартості відображено: " + element.getText());
            return true;
        } catch (Exception e) {
            System.out.println("Блок орієнтовної вартості не з'явився: " + e.getMessage());
            return false;
        }
    }

    public boolean isCostBlockDisplayedAnywhere() {
        boolean inside = false;
        try {
            WebElement iframe = driver.findElement(By.xpath("//iframe[contains(@src, 'forms.novapost.world/calculate')]"));
            driver.switchTo().frame(iframe); // твій локатор
            inside = isCostBlockDisplayed();
        } catch (Exception ignored) {
        } finally {
            driver.switchTo().defaultContent();
        }

        boolean outside = isCostBlockDisplayed();
        return inside || outside;
    }

    public void enterTrackingNumber(String number) {
        waitAndSendKeys(trackingInput, number);
    }

    public void clickTrackButton() {
        waitAndClick(trackButton);
    }

    public TrackingPage goToTrackingPage(String number) {
        enterTrackingNumber(number);
        clickTrackButton();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("/tracking/" + number));
        return new TrackingPage(driver);
    }

    public void clickSendMenu() {
        waitAndClick(sendMenu);
    }

    public ForbiddenItemsPage clickAllowedItems() {
        waitAndClick(allowedItemsMenu);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("/forbidden-items"));
        return new ForbiddenItemsPage(driver);
    }
}


