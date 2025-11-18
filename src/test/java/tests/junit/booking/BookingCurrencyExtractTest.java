package tests.junit.booking;

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
        opt.addArguments("--start-maximized");
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

        // Извлечение текущей валюты (метод 1)
        try {
            WebElement currencySpan = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("span.ca2ca5203b")
            ));
            String currencyCode = currencySpan.getText().trim();
            System.out.println("✓ Текущая валюта (код): " + currencyCode);
        } catch (TimeoutException e) {
            System.out.println("⚠ Span с классом ca2ca5203b не найден");
        }

        // Извлечение полной информации о валюте (метод 2)
        try {
            WebElement currencyPicker = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("span.Picker_selection-text")
            ));
            String fullCurrencyText = currencyPicker.getText().trim();
            System.out.println("✓ Полная информация о валюте: " + fullCurrencyText);
        } catch (TimeoutException e) {
            System.out.println("⚠ Span с классом Picker_selection-text не найден");
        }

        // Извлечение активной валюты (метод 3)
        try {
            WebElement activeCurrency = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("div.CurrencyPicker_currency--active")
            ));
            String activeCurrencyCode = activeCurrency.getText().trim();
            System.out.println("✓ Активная валюта (из скрытого меню): " + activeCurrencyCode);
        } catch (TimeoutException e) {
            System.out.println("⚠ Активная валюта не найдена");
        }

        System.out.println("\n--- ОТКРЫТИЕ МЕНЮ ВАЛЮТ ---\n");

        // Открытие меню валют
        By currencyButtonLocator = By.xpath("//button[contains(@aria-label, 'currency')]");
        WebElement currencyButton = wait.until(ExpectedConditions.elementToBeClickable(currencyButtonLocator));
        currencyButton.click();
        System.out.println("✓ Меню валют открыто");

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.CurrencyPicker_currency")));

        System.out.println("\n--- ВСЕ ДОСТУПНЫЕ ВАЛЮТЫ ---\n");

        java.util.List<WebElement> currencyOptions = driver.findElements(By.cssSelector("div.CurrencyPicker_currency"));

        if (currencyOptions.isEmpty()) {
            System.out.println("❌ Валюты не найдены в меню");
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

    private void waitAndClickWithTimeout(By locator, int timeoutSeconds, String message) {
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        try {
            shortWait.until(ExpectedConditions.elementToBeClickable(locator)).click();
            System.out.println(message);
        } catch (TimeoutException e) {
            // Элемент не найден - допустимо
        }
    }

    private WebElement getElementIfPresent(By locator) {
        try {
            return driver.findElement(locator);
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}
