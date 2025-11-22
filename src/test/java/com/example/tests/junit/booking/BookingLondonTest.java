package com.example.tests.junit.booking;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BookingLondonTest {
    private static final Logger logger = LogManager.getLogger(BookingLondonTest.class);
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    @Before
    public void setUp() {
        ChromeOptions opt = new ChromeOptions();
        opt.addArguments("--start-maximized");
        driver = new ChromeDriver(opt);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        js = (JavascriptExecutor) driver;
        logger.info("=== BOOKING LONDON TEST ===");
    }

    @Test
    public void testBookingLondon() {
        logger.info("Navigating to Booking.com");
        driver.get("https://www.booking.com");
        logger.info("Opened Booking.com");

        waitAndClickWithTimeout(By.id("onetrust-accept-btn-handler"), 10, "Cookies accepted");
        waitAndClickWithTimeout(By.cssSelector("button[aria-label='Dismiss sign-in info.']"), 5, "Genius popup closed");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate checkInDate = LocalDate.now().plusDays(3);
        LocalDate checkOutDate = checkInDate.plusDays(7);

        WebElement input = driver.findElement(By.cssSelector("input[placeholder='Where are you going?']"));
        input.click();
        input.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        input.sendKeys("London");

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class,'efbfd2b849')]//div[text()='London']/..")
        )).click();
        logger.info("City LONDON selected");

        String checkInDateString = checkInDate.format(formatter);
        String checkOutDateString = checkOutDate.format(formatter);

        WebElement dayIn = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("[data-date='" + checkInDateString + "']")
        ));
        dayIn.click();

        WebElement dayOut = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("[data-date='" + checkOutDateString + "']")
        ));
        dayOut.click();
        logger.info("Dates selected: " + checkInDateString + " – " + checkOutDateString);

        logger.info("Clicking search button");
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']"))).click();
        logger.info("Search button clicked");

        logger.info("Waiting for hotels to load...");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='property-card']")));
        logger.info("Hotels loaded");

        java.util.List<WebElement> hotelCards = driver.findElements(By.cssSelector("[data-testid='property-card']"));

        while (hotelCards.size() < 10 && hotelCards.size() > 0) {
            js.executeScript("window.scrollBy(0, 500);");
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("[data-testid='property-card']")));
            hotelCards = driver.findElements(By.cssSelector("[data-testid='property-card']"));
        }

        if (hotelCards.size() < 10) {
            Assert.fail("Failed to find 10 hotels on page");
        }

        logger.info("Scrolling to 10th hotel");
        WebElement tenthHotel = hotelCards.get(9);
        js.executeScript("arguments[0].scrollIntoView(true);", tenthHotel);
        logger.info("Scrolled to 10th hotel");

        logger.info("Changing hotel background to green");
        js.executeScript("arguments[0].style.backgroundColor = 'green';", tenthHotel);
        logger.info("10th hotel background changed to GREEN");

        logger.info("Changing hotel title color to red");
        WebElement hotelTitle = tenthHotel.findElement(By.cssSelector("[data-testid='title']"));
        js.executeScript("arguments[0].style.color = 'red';", hotelTitle);
        logger.info("Hotel title color changed to RED");

        logger.info("Taking screenshot");
        String screenshotPath = takeScreenshot();
        logger.info("Screenshot saved: " + screenshotPath);

        logger.info("=== TEST COMPLETED SUCCESSFULLY ===");
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
        }
    }

    private String takeScreenshot() {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String filePath = "target/screenshot_" + System.currentTimeMillis() + ".png";
            FileHandler.copy(screenshot, new File(filePath));
            return filePath;
        } catch (Exception e) {
            logger.error("Error creating screenshot: " + e.getMessage(), e);
            return "Error";
        }
    }
}
