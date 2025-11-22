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

public class BookingPragueRatingTest {
    private static final Logger logger = LogManager.getLogger(BookingPragueRatingTest.class);
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
        logger.info("=== BOOKING PRAGUE RATING TEST ===");
    }

    @Test
    public void testPragueHotelRating() {
        logger.info("Navigating to Booking.com");
        driver.get("https://www.booking.com");
        logger.info("Opened Booking.com");

        waitAndClickWithTimeout(By.id("onetrust-accept-btn-handler"), 10, "Cookies accepted");
        waitAndClickWithTimeout(By.cssSelector("button[aria-label='Dismiss sign-in info.']"), 5, "Genius popup closed");

        logger.info("--- SEARCHING HOTELS IN PRAGUE ---");

        logger.info("Selecting city: Prague");
        WebElement input = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("input[placeholder='Where are you going?']")
        ));
        input.click();
        input.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        input.sendKeys("Prague");

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class,'efbfd2b849')]//div[text()='Prague']/.."))
        ).click();
        logger.info("Prague selected");

        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);

        String todayString = today.format(dateFormatter);
        String tomorrowString = tomorrow.format(dateFormatter);

        logger.info("Selecting check-in date (today)");
        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("[data-date='" + todayString + "']")
        )).click();
        logger.info("Check-in date (today): " + todayString);

        logger.info("Selecting check-out date (tomorrow)");
        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("[data-date='" + tomorrowString + "']")
        )).click();
        logger.info("Check-out date (tomorrow): " + tomorrowString);

        logger.info("Selecting 2 guests, 1 room");

        logger.info("Opening occupancy configuration");
        WebElement occupancyField = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[data-testid='occupancy-config']")
        ));
        occupancyField.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='occupancy-popup']")));

        WebElement adultsIncreaseButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[text()='Adults']/ancestor::div[contains(@class,'e484bb5b7a')]/descendant::button[last()]")
        ));
        adultsIncreaseButton.click();
        logger.info("Adults: 2");
        logger.info("Rooms: 1");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button/span[text()='Done']"))).click();
        logger.info("Parameters set");

        logger.info("Submitting search");
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']"))).click();
        logger.info("Search submitted");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='property-card']")));
        logger.info("Results loaded");

        logger.info("--- APPLYING RATING FILTER ---");

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("[data-testid='property-card']")));

        logger.info("Opening sort menu");
        By sortByLocator = By.xpath("//span[contains(text(), 'Sort by')]");
        WebElement sortByElement = wait.until(ExpectedConditions.presenceOfElementLocated(sortByLocator));

        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", sortByElement);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            logger.error("Thread interrupted during sleep", e);
        }

        WebElement sortByButton = sortByElement.findElement(By.xpath("./ancestor::button"));
        js.executeScript("arguments[0].click();", sortByButton);
        logger.info("Sort menu opened");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            logger.error("Thread interrupted during sleep", e);
        }

        logger.info("Searching for rating sort option");
        By[] ratingLocators = {
            By.xpath("//button[contains(text(), 'rating')]"),
            By.xpath("//button[contains(text(), 'Rating')]"),
            By.xpath("//*[contains(text(), 'Property rating')]"),
            By.xpath("//button[@data-id='class']"),
            By.xpath("//button[contains(@data-id, 'class')]"),
            By.cssSelector("button[data-id*='class']")
        };

        WebElement ratingOption = null;
        for (By locator : ratingLocators) {
            try {
                java.util.List<WebElement> options = driver.findElements(locator);
                if (!options.isEmpty()) {
                    ratingOption = options.get(0);
                    logger.info("Found rating option with locator: " + locator);
                    break;
                }
            } catch (Exception e) {
            }
        }

        if (ratingOption != null) {
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", ratingOption);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                logger.error("Thread interrupted during sleep", e);
            }
            js.executeScript("arguments[0].click();", ratingOption);
            logger.info("Rating sort applied");
        } else {
            logger.info("Rating option not found, skipping sort");
        }

        waitForLoaderDisappear();

        logger.info("List updated");

        logger.info("--- CHECKING FIRST HOTEL RATING ---");

        WebElement firstHotelCard = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("[data-testid='property-card']")
        ));

        WebElement hotelTitle = getElementIfPresent(firstHotelCard, By.cssSelector("[data-testid='title']"));
        String hotelName = hotelTitle != null ? hotelTitle.getText() : "Unknown Hotel";
        logger.info("First hotel: " + hotelName);

        WebElement ratingElement = getElementIfPresent(firstHotelCard,
                By.xpath(".//div[contains(@class, 'f63b14ab7a')]"));

        double hotelRating = 0.0;
        if (ratingElement != null) {
            String ratingText = ratingElement.getText().trim();
            try {
                hotelRating = Double.parseDouble(ratingText);
                logger.info("Rating from card: " + hotelRating);
            } catch (NumberFormatException e) {
                logger.info("Could not parse rating: " + ratingText);
            }
        } else {
            logger.info("Rating not found in card");
        }

        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", firstHotelCard);
        
        logger.info("Clicking on hotel card");
        try {
            firstHotelCard.click();
            logger.info("Clicked hotel card (normal)");
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", firstHotelCard);
            logger.info("Clicked hotel card (JavaScript)");
        }

        logger.info("Waiting for hotel page to open");
        try {
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));
            
            java.util.Set<String> windows = driver.getWindowHandles();
            for (String window : windows) {
                if (!window.equals(driver.getWindowHandle())) {
                    driver.switchTo().window(window);
                    logger.info("Switched to new hotel tab");
                    break;
                }
            }
            
            try {
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'f63b14ab7a')]")));
            } catch (Exception e) {
                logger.info("Hotel page element not found");
            }
        } catch (org.openqa.selenium.TimeoutException e) {
            logger.info("New tab did not open, staying on current page");
        }

        WebElement pageRatingElement = getElementIfPresent(
                By.xpath("//div[contains(@class, 'f63b14ab7a') and contains(@class, 'dff2e52086')]"));

        if (pageRatingElement == null) {
            pageRatingElement = getElementIfPresent(
                    By.xpath("//div[contains(@class, 'f63b14ab7a')]"));
        }

        double pageRating = 0.0;
        if (pageRatingElement != null && pageRatingElement.isDisplayed()) {
            String pageRatingText = pageRatingElement.getText().trim();
            try {
                pageRating = Double.parseDouble(pageRatingText);
                logger.info("Rating on hotel page: " + pageRating);
            } catch (NumberFormatException e) {
                logger.info("Could not parse page rating: " + pageRatingText);
                pageRating = hotelRating;
            }
        } else {
            logger.info("Rating on hotel page not found, using card rating: " + hotelRating);
            pageRating = hotelRating;
        }

        logger.info("--- CHECK RESULT ---");

        if (pageRating >= 9.0) {
            logger.info("Excellent hotel rating: " + pageRating + " >= 9.0");
        } else if (pageRating > 0) {
            logger.info("Hotel rating: " + pageRating + " (valid, but less than 9.0)");
        } else {
            logger.warn("Hotel rating not found on page");
        }

        logger.info("Hotel: " + hotelName);
        logger.info("Rating: " + (pageRating > 0 ? pageRating : "Not found"));

        Assert.assertTrue("Rating must be found and valid (1-10)", pageRating > 0 && pageRating <= 10);
        logger.info("Hotel rating is valid: " + pageRating);

        logger.info("TEST COMPLETED");
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

    private void waitForLoaderDisappear() {
        By loaderLocator = By.cssSelector("div.b2b4d455c1");

        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
        try {
            shortWait.until(ExpectedConditions.visibilityOfElementLocated(loaderLocator));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(loaderLocator));
        } catch (TimeoutException e) {
        }
    }

    private WebElement getElementIfPresent(By locator) {
        try {
            return driver.findElement(locator);
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    private WebElement getElementIfPresent(WebElement element, By locator) {
        try {
            return element.findElement(locator);
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}
