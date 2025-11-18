package classwork.day17;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class BookingCurrencyExtractTest {
    public static void main(String[] args) throws InterruptedException {

        ChromeOptions opt = new ChromeOptions();
        opt.addArguments("--start-maximized");
        WebDriver d = new ChromeDriver(opt);
        WebDriverWait w = new WebDriverWait(d, Duration.ofSeconds(30));

        System.out.println("=== ИЗВЛЕЧЕНИЕ ВАЛЮТЫ ИЗ BOOKING ===\n");

        d.get("https://www.booking.com");
        System.out.println("✓ Открыл Booking.com");

        waitAndClickWithTimeout(d, w, By.id("onetrust-accept-btn-handler"), 10, "✓ Куки приняты");

        waitAndClickWithTimeout(d, w, By.cssSelector("button[aria-label='Dismiss sign-in info.']"), 5, "✓ Окно Genius закрыто");

        Thread.sleep(500);

        System.out.println("\n--- ТЕКУЩАЯ ВАЛЮТА ---\n");

        try {
            WebElement currencySpan = w.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("span.ca2ca5203b")
            ));
            String currencyCode = currencySpan.getText().trim();
            System.out.println("✓ Текущая валюта (код): " + currencyCode);
        } catch (TimeoutException e) {
            System.out.println("⚠ Span с классом ca2ca5203b не найден");
        }

        try {
            WebElement currencyPicker = w.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("span.Picker_selection-text")
            ));
            String fullCurrencyText = currencyPicker.getText().trim();
            System.out.println("✓ Полная информация о валюте: " + fullCurrencyText);
        } catch (TimeoutException e) {
            System.out.println("⚠ Span с классом Picker_selection-text не найден");
        }

        try {
            WebElement activeCurrency = w.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("div.CurrencyPicker_currency--active")
            ));
            String activeCurrencyCode = activeCurrency.getText().trim();
            System.out.println("✓ Активная валюта (из скрытого меню): " + activeCurrencyCode);
        } catch (TimeoutException e) {
            System.out.println("⚠ Активная валюта не найдена");
        }

        System.out.println("\n--- ОТКРЫТИЕ МЕНЮ ВАЛЮТ ---\n");

        By currencyButtonLocator = By.xpath("//button[contains(@aria-label, 'currency')]");
        WebElement currencyButton = w.until(ExpectedConditions.elementToBeClickable(currencyButtonLocator));
        currencyButton.click();
        System.out.println("✓ Меню валют открыто");

        Thread.sleep(800);

        System.out.println("\n--- ВСЕ ДОСТУПНЫЕ ВАЛЮТЫ ---\n");

        java.util.List<WebElement> currencyOptions = d.findElements(By.cssSelector("div.CurrencyPicker_currency"));

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

        WebElement activeInMenu = getElementIfPresent(d, By.cssSelector("div.CurrencyPicker_currency--active"));
        if (activeInMenu != null) {
            String activeText = activeInMenu.getText().trim();
            System.out.println("✓ Активная валюта: " + activeText);
        } else {
            System.out.println("⚠ Активная валюта не определена");
        }

        System.out.println("\n✅ ТЕСТ ЗАВЕРШЕН");

        d.quit();
    }

    private static void waitAndClickWithTimeout(WebDriver d, WebDriverWait w, By locator, int timeoutSeconds, String message) {
        WebDriverWait shortWait = new WebDriverWait(d, Duration.ofSeconds(timeoutSeconds));
        try {
            shortWait.until(ExpectedConditions.elementToBeClickable(locator)).click();
            System.out.println(message);
        } catch (TimeoutException e) {
        }
    }

    private static WebElement getElementIfPresent(WebDriver d, By locator) {
        try {
            return d.findElement(locator);
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}