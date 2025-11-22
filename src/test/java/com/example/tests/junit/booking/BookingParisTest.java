package com.example.tests.junit.booking;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BookingParisTest {
    private static final Logger logger = LogManager.getLogger(BookingParisTest.class);
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    private DateTimeFormatter dateFormatter;

    @Before
    public void setUp() {
        ChromeOptions opt = new ChromeOptions();
        opt.addArguments("--start-maximized");
        driver = new ChromeDriver(opt);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        js = (JavascriptExecutor) driver;
        dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        logger.info("=== BOOKING PARIS (5 STARS) TEST ===");
    }

    @Test
    public void testBookingParisRating() {
        LocalDate checkInDate = LocalDate.now().plusDays(3);
        LocalDate checkOutDate = checkInDate.plusDays(7);

        String checkInDateString = checkInDate.format(dateFormatter);
        String checkOutDateString = checkOutDate.format(dateFormatter);

        logger.info("--- DATE CALCULATION ---");
        logger.info("  Check-in: " + checkInDateString);
        logger.info("  Check-out: " + checkOutDateString);
        logger.info("--------------------");

        logger.info("Navigating to Booking.com");
        driver.get("https://www.booking.com");
        logger.info("Opened Booking.com");

        waitAndClickWithTimeout(By.id("onetrust-accept-btn-handler"), 10, "Cookies accepted");
        waitAndClickWithTimeout(By.cssSelector("button[aria-label='Dismiss sign-in info.']"), 5, "Genius popup closed");

        logger.info("Selecting city: Paris");
        WebElement input = driver.findElement(By.cssSelector("input[placeholder='Where are you going?']"));
        input.click();
        input.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        input.sendKeys("Paris");
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class,'efbfd2b849')]//div[text()='Paris']/..")
        )).click();
        logger.info("PARIS SELECTED!");

        logger.info("Selecting check-in date");
        WebElement dayIn = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("[data-date='" + checkInDateString + "']")
        ));
        dayIn.click();
        logger.info("Check-in date (" + checkInDateString + ") clicked!");

        logger.info("Selecting check-out date");
        WebElement dayOut = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("[data-date='" + checkOutDateString + "']")
        ));
        dayOut.click();
        logger.info("Check-out date (" + checkOutDateString + ") clicked!");
        logger.info("DATES SELECTED: " + checkInDateString + " – " + checkOutDateString);

        logger.info("Opening occupancy configuration");
        WebElement occupancyField = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[data-testid='occupancy-config']")
        ));
        occupancyField.click();
        logger.info("Guests/rooms modal OPENED!");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='occupancy-popup']")));
        logger.info("Guests container loaded");

        logger.info("Selecting 4 adults");
        By adultsIncreaseLocator = By.xpath(
                "//label[text()='Adults']/ancestor::div[contains(@class,'e484bb5b7a')]/descendant::button[last()]"
        );
        WebElement adultsPlusButton = wait.until(ExpectedConditions.elementToBeClickable(adultsIncreaseLocator));
        adultsPlusButton.click();
        adultsPlusButton.click();
        logger.info("Adults: 4 selected");

        logger.info("Selecting 2 rooms");
        By roomsIncreaseLocator = By.xpath(
                "//label[text()='Rooms']/ancestor::div[contains(@class,'e484bb5b7a')]/descendant::button[last()]"
        );
        WebElement roomsPlusButton = wait.until(ExpectedConditions.elementToBeClickable(roomsIncreaseLocator));
        roomsPlusButton.click();
        logger.info("Rooms: 2 selected");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button/span[text()='Done']"))).click();
        logger.info("Done button clicked");

        logger.info("Submitting search");
        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
        searchButton.click();
        logger.info("Search button clicked!");

        logger.info("Waiting for first hotel card to load...");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='property-card']")));
        logger.info("Hotel cards loaded");

        logger.info("Applying 5 stars filter");
        By fiveStarsFilterLocator = By.xpath("//div[@data-filters-group='class']//div[text()='5 stars']");
        WebElement fiveStarsFilter = wait.until(ExpectedConditions.presenceOfElementLocated(fiveStarsFilterLocator));

        js.executeScript("arguments[0].scrollIntoView(true);", fiveStarsFilter);
        logger.info("Scrolled to '5 stars' filter");

        js.executeScript("arguments[0].click();", fiveStarsFilter);
        logger.info("Selected filter: 5 stars!");

        logger.info("Waiting for AJAX loading to complete after applying filter...");
        waitForLoaderDisappear();
        logger.info("AJAX loading complete. 5 stars filter applied");

        logger.info("Applying sorting by rating");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("[data-testid='property-card']")));

        By currentSortLocator = By.xpath("//span[contains(@class,'a9918d47bf') and contains(text(), 'Sort by:')]/..");
        WebElement currentSortDisplay = wait.until(ExpectedConditions.presenceOfElementLocated(currentSortLocator));
        
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", currentSortDisplay);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            logger.error("Thread interrupted during sleep", e);
        }
        js.executeScript("arguments[0].click();", currentSortDisplay);
        logger.info("Clicked current sort, dropdown menu opened");

        WebElement ratingLowToHighOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[data-id='class_asc']")
        ));
        ratingLowToHighOption.click();
        logger.info("Selected sorting: Property rating (low to high)");

        logger.info("Waiting for AJAX loading to complete after sorting...");
        waitForLoaderDisappear();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='property-card']")));
        logger.info("Sorting complete, results loaded");

        By assertionLocator = By.xpath(
                "(//div[@data-testid='property-card'])[1]//div[@aria-label='5 out of 5']"
        );

        boolean assertionPassed = isElementPresent(assertionLocator, 5);

        if (assertionPassed) {
            logger.info("CHECK SUCCESSFUL: First hotel in list has 5 stars rating!");
        } else {
            WebElement firstHotelName = getElementIfPresent(By.xpath("(//div[@data-testid='property-card'])[1]//div[@data-testid='title']"));
            logger.error("CHECK FAILED: First hotel does not have expected 5 stars rating");
            if (firstHotelName != null) {
                logger.error("First hotel name: " + firstHotelName.getText());
            }

            WebElement filterChecked = getElementIfPresent(By.xpath("//div[@data-filters-group='class']//div[text()='5 stars']/ancestor::*[@aria-checked='true']"));
            if (filterChecked != null) {
                logger.error("(But 5 stars filter on sidebar remains active)");
            } else {
                logger.error("(5 stars filter on sidebar appears to have been reset)");
            }
        }

        Assert.assertTrue("First hotel must have 5 stars rating", assertionPassed);

        logger.info("--- END OF TEST SCENARIO ---");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void waitAndClickWithTimeout(By locator, int timeoutSeconds, String message) {
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        try {
            shortWait.until(ExpectedConditions.elementToBeClickable(locator)).click();
            logger.info(message);
        } catch (TimeoutException e) {
            // Элемент не найден - допустимо
        }
    }

    private boolean isElementPresent(By locator, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    private WebElement getElementIfPresent(By locator) {
        try {
            return driver.findElement(locator);
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    private void waitForLoaderDisappear() {
        By loaderLocator = By.cssSelector("div.b2b4d455c1");

        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
        try {
            shortWait.until(ExpectedConditions.visibilityOfElementLocated(loaderLocator));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(loaderLocator));
        } catch (TimeoutException e) {
        }
    }
}
