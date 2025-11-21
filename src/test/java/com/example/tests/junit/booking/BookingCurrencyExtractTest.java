package com.example.tests.junit.booking;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class BookingCurrencyExtractTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        ChromeOptions opt = new ChromeOptions();
        opt.addArguments("--start-maximized", "--headless");
        driver = new ChromeDriver(opt);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        System.out.println("=== ИЗВЛЕЧЕНИЕ ВАЛЮТЫ ИЗ BOOKING ===\n");
    }

    @Test
    public void testExtractCurrency() {
        driver.get("https://www.booking.com");
        System.out.println("✓ Открыл Booking.com");

        // Принять куки
        waitAndClickWithTimeout(By.id("onetrust-accept-btn-handler"), 10, "✓ Куки приняты");
        
        // Закрыть окно Genius
        waitAndClickWithTimeout(By.cssSelector("button[aria-label='Dismiss sign-in info.']"), 5, "✓ Окно Genius закрыто");

        System.out.println("\n--- ТЕКУЩАЯ ВАЛЮТА ---\n");

        // Извлечение текущей валюты (метод 1 - по классу)
        try {
            WebElement currencySpan = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span.ca2ca5203b")));
            String currencyCode = currencySpan.getText().trim();
            System.out.println("✓ Текущая валюта (код): " + currencyCode);
        } catch (TimeoutException e) {
            System.out.println("⚠ Span с классом ca2ca5203b не найден");
        }

        // Извлечение активной валюты (метод 2 - по active классу)
        try {
            WebElement activeCurrency = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.CurrencyPicker_currency--active")));
            String activeCurrencyCode = activeCurrency.getText().trim();
            System.out.println("✓ Активная валюта (из скрытого меню): " + activeCurrencyCode);
        } catch (TimeoutException e) {
            System.out.println("⚠ Активная валюта не найдена");
        }

        System.out.println("\n--- ПОПЫТКА ОТКРЫТЬ МЕНЮ ВАЛЮТ ---\n");

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
                    System.out.println("✓ Меню валют открыто");
                    
                    // Ждём загрузки списка валют
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                    break;
                }
            } catch (Exception e) {
                // Пробуем следующий локатор
            }
        }

        if (!currencyMenuOpened) {
            System.out.println("⚠ Кнопка меню валют не найдена - используем уже полученную валюту");
        }

        System.out.println("\n--- ВСЕ ДОСТУПНЫЕ ВАЛЮТЫ ---\n");

        // Получение всех доступных валют
        java.util.List<WebElement> currencyOptions = driver.findElements(By.cssSelector("div.CurrencyPicker_currency"));

        if (currencyOptions.isEmpty()) {
            System.out.println("⚠ Валюты не найдены в меню");
        } else {
            System.out.println("Найдено " + currencyOptions.size() + " валют(ы):\n");

            for (int i = 0; i < Math.min(currencyOptions.size(), 15); i++) {
                String currencyText = currencyOptions.get(i).getText().trim();
                boolean isActive = currencyOptions.get(i).getAttribute("class").contains("active");
                String activeMarker = isActive ? " ⭐ АКТИВНАЯ" : "";

                if (!currencyText.isEmpty()) {
                    System.out.println((i + 1) + ". " + currencyText + activeMarker);
                }
            }

            if (currencyOptions.size() > 15) {
                System.out.println("... и ещё " + (currencyOptions.size() - 15) + " валют(ы)");
            }
        }

        System.out.println("\n--- АКТИВНАЯ ВАЛЮТА В МЕНЮ ---\n");

        WebElement activeInMenu = getElementIfPresent(By.cssSelector("div.CurrencyPicker_currency--active"));
        if (activeInMenu != null) {
            String activeText = activeInMenu.getText().trim();
            System.out.println("✓ Активная валюта: " + activeText);
        } else {
            System.out.println("⚠ Активная валюта не определена");
        }

        System.out.println("\n✅ ТЕСТ ЗАВЕРШЕН");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Вспомогательные методы
    private void waitAndClickWithTimeout(By locator, int timeoutSeconds, String successMessage) {
        try {
            WebElement element = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                    .until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
            System.out.println(successMessage);
        } catch (TimeoutException e) {
            System.out.println("⚠ Элемент " + locator + " не найден за " + timeoutSeconds + " сек");
        }
    }

    private WebElement getElementIfPresent(By locator) {
        try {
            java.util.List<WebElement> elements = driver.findElements(locator);
            return elements.isEmpty() ? null : elements.get(0);
        } catch (Exception e) {
            return null;
        }
    }
}
