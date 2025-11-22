package com.example.tests.junit.booking;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class BookingCurrencyExtractTest {
    private static final Logger logger = LogManager.getLogger(BookingCurrencyExtractTest.class);
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        logger.info("=== Starting currency extraction test ===");
        ChromeOptions opt = new ChromeOptions();
        opt.addArguments("--start-maximized", "--headless");
        driver = new ChromeDriver(opt);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        logger.info("WebDriver initialized successfully");
    }

    @Test
    public void testExtractCurrency() {
        try {
            logger.info("Navigating to Booking.com");
            driver.get("https://www.booking.com");
            logger.info("Page loaded successfully");

            // Принять куки
            waitAndClickWithTimeout(By.id("onetrust-accept-btn-handler"), 10, "Cookies accepted");
            
            // Закрыть окно Genius
            waitAndClickWithTimeout(By.cssSelector("button[aria-label='Dismiss sign-in info.']"), 5, "Genius popup closed");

            logger.info("--- Extracting current currency ---");

            // Извлечение текущей валюты (метод 1 - по классу)
            try {
                WebElement currencySpan = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span.ca2ca5203b")));
                String currencyCode = currencySpan.getText().trim();
                logger.info("Current currency code: {}", currencyCode);
            } catch (TimeoutException e) {
                logger.error("Currency span with class ca2ca5203b not found", e);
            }

            // Извлечение активной валюты (метод 2 - по active классу)
            try {
                WebElement activeCurrency = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.CurrencyPicker_currency--active")));
                String activeCurrencyCode = activeCurrency.getText().trim();
                logger.info("Active currency from hidden menu: {}", activeCurrencyCode);
            } catch (TimeoutException e) {
                logger.error("Active currency element not found", e);
            }

            logger.info("--- Attempting to open currency menu ---");

            // Открытие меню валют - используем более гибкий поиск
            boolean currencyMenuOpened = false;
            By[] currencyButtonLocators = {
                By.xpath("//button[contains(@aria-label, 'currency')]"),
                By.xpath("//button[contains(@class, 'CurrencyPicker')]"),
                By.xpath("//button[@data-testid='header-currency']"),
                By.cssSelector("button[aria-label*='currency']"),
                By.xpath("//div[@class='Header__right']//button[1]"),
                By.xpath("//button[.//span[contains(@class, 'ca2ca5203b')]]")
            };

            for (By locator : currencyButtonLocators) {
                try {
                    java.util.List<WebElement> elements = driver.findElements(locator);
                    if (!elements.isEmpty() && elements.get(0).isDisplayed()) {
                        elements.get(0).click();
                        currencyMenuOpened = true;
                        logger.info("Currency menu opened successfully");
                        
                        // Ждём загрузки списка валют
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException ie) {
                            Thread.currentThread().interrupt();
                        }
                        break;
                    }
                } catch (Exception e) {
                    logger.debug("Failed to open menu with locator: {}", locator);
                }
            }

            if (!currencyMenuOpened) {
                logger.warn("Currency menu button not found - using already obtained currency");
            }

            logger.info("--- Extracting all available currencies ---");

            // Получение всех доступных валют
            java.util.List<WebElement> currencyOptions = driver.findElements(By.cssSelector("div.CurrencyPicker_currency"));

            if (currencyOptions.isEmpty()) {
                logger.warn("No currencies found in menu");
            } else {
                logger.info("Found {} currencies", currencyOptions.size());

                for (int i = 0; i < Math.min(currencyOptions.size(), 15); i++) {
                    String currencyText = currencyOptions.get(i).getText().trim();
                    boolean isActive = currencyOptions.get(i).getAttribute("class").contains("active");
                    String activeMarker = isActive ? " (ACTIVE)" : "";

                    if (!currencyText.isEmpty()) {
                        logger.info("{}. {}{}", (i + 1), currencyText, activeMarker);
                    }
                }

                if (currencyOptions.size() > 15) {
                    logger.info("... and {} more currencies", (currencyOptions.size() - 15));
                }
            }

            logger.info("--- Checking active currency in menu ---");

            WebElement activeInMenu = getElementIfPresent(By.cssSelector("div.CurrencyPicker_currency--active"));
            if (activeInMenu != null) {
                String activeText = activeInMenu.getText().trim();
                logger.info("Active currency in menu: {}", activeText);
            } else {
                logger.warn("Active currency not identified");
            }

            logger.info("Test completed successfully");
        } catch (Exception e) {
            logger.error("Test failed with error", e);
            throw e;
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            logger.info("Closing WebDriver");
            driver.quit();
        }
    }

    // Вспомогательные методы
    private void waitAndClickWithTimeout(By locator, int timeoutSeconds, String successMessage) {
        try {
            WebElement element = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                    .until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
            logger.info(successMessage);
        } catch (TimeoutException e) {
            logger.warn("Element {} not found within {} seconds", locator, timeoutSeconds);
        }
    }

    private WebElement getElementIfPresent(By locator) {
        try {
            java.util.List<WebElement> elements = driver.findElements(locator);
            return elements.isEmpty() ? null : elements.get(0);
        } catch (Exception e) {
            logger.error("Error getting element: {}", locator, e);
            return null;
        }
    }
}
