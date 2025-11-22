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
        logger.info("=== ТЕСТ BOOKING LONDON ===\n");
    }

    @Test
    public void testBookingLondon() {
        logger.info("Navigating to Booking.com");
        driver.get("https://www.booking.com");
        logger.info("✓ Открыл Booking.com");

        waitAndClickWithTimeout(By.id("onetrust-accept-btn-handler"), 10, "✓ Куки приняты");
        waitAndClickWithTimeout(By.cssSelector("button[aria-label='Dismiss sign-in info.']"), 5, "✓ Окно Genius закрыто");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate checkInDate = LocalDate.now().plusDays(3);
        LocalDate checkOutDate = checkInDate.plusDays(7);

        // Выбор города
        WebElement input = driver.findElement(By.cssSelector("input[placeholder='Where are you going?']"));
        input.click();
        input.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        input.sendKeys("London");

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class,'efbfd2b849')]//div[text()='London']/..")
        )).click();
        logger.info("✓ Город LONDON выбран");

        String checkInDateString = checkInDate.format(formatter);
        String checkOutDateString = checkOutDate.format(formatter);

        // Выбор дат
        WebElement dayIn = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("[data-date='" + checkInDateString + "']")
        ));
        dayIn.click();

        WebElement dayOut = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("[data-date='" + checkOutDateString + "']")
        ));
        dayOut.click();
        logger.info("✓ Даты выбраны: " + checkInDateString + " – " + checkOutDateString);

        // Поиск
        logger.info("Clicking search button");
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']"))).click();
        logger.info("✓ Нажата кнопка Search");

        logger.info(" Ожидаем загрузку отелей...");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='property-card']")));
        logger.info("✓ Отели загружены");

        // Поиск 10-го отеля с прокруткой
        java.util.List<WebElement> hotelCards = driver.findElements(By.cssSelector("[data-testid='property-card']"));

        while (hotelCards.size() < 10 && hotelCards.size() > 0) {
            js.executeScript("window.scrollBy(0, 500);");
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("[data-testid='property-card']")));
            hotelCards = driver.findElements(By.cssSelector("[data-testid='property-card']"));
        }

        if (hotelCards.size() < 10) {
            Assert.fail("❌ Не удалось найти 10 отелей на странице");
        }

        // Работа с 10-м отелем
        logger.info("Scrolling to 10th hotel");
        WebElement tenthHotel = hotelCards.get(9);
        js.executeScript("arguments[0].scrollIntoView(true);", tenthHotel);
        logger.info("✓ Прокручен к 10-му отелю");

        logger.info("Changing hotel background to green");
        js.executeScript("arguments[0].style.backgroundColor = 'green';", tenthHotel);
        logger.info("✓ Фон 10-го отеля изменен на ЗЕЛЕНЫЙ");

        logger.info("Changing hotel title color to red");
        WebElement hotelTitle = tenthHotel.findElement(By.cssSelector("[data-testid='title']"));
        js.executeScript("arguments[0].style.color = 'red';", hotelTitle);
        logger.info("✓ Цвет текста названия отеля изменен на КРАСНЫЙ");

        logger.info("Taking screenshot");
        String screenshotPath = takeScreenshot();
        logger.info("✓ Скриншот сохранён: " + screenshotPath);

        logger.info("\n=== ТЕСТ ЗАВЕРШЕН УСПЕШНО ===");
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

    private String takeScreenshot() {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String filePath = "target/screenshot_" + System.currentTimeMillis() + ".png";
            FileHandler.copy(screenshot, new File(filePath));
            return filePath;
        } catch (Exception e) {
            logger.error("Ошибка при создании скриншота: " + e.getMessage(), e);
            return "Ошибка";
        }
    }
}
