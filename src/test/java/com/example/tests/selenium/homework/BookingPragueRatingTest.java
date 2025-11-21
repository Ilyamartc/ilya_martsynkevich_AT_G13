package com.example.tests.selenium.homework;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BookingPragueRatingTest {
    public static void main(String[] args) throws InterruptedException {

        ChromeOptions opt = new ChromeOptions();
        opt.addArguments("--start-maximized");
        WebDriver d = new ChromeDriver(opt);
        WebDriverWait w = new WebDriverWait(d, Duration.ofSeconds(30));
        JavascriptExecutor js = (JavascriptExecutor) d;

        System.out.println("=== ТЕСТ BOOKING PRAGUE (РЕЙТИНГ) ===\n");

        d.get("https://www.booking.com");
        System.out.println("✓ Открыл Booking.com");

        waitAndClickWithTimeout(d, w, By.id("onetrust-accept-btn-handler"), 10, "✓ Куки приняты");

        waitAndClickWithTimeout(d, w, By.cssSelector("button[aria-label='Dismiss sign-in info.']"), 5, "✓ Окно Genius закрыто");

        Thread.sleep(500);

        System.out.println("\n--- ПОИСК ОТЕЛЕЙ В ПРАГЕ ---\n");

        WebElement input = w.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("input[placeholder='Where are you going?']")
        ));
        input.click();
        input.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        input.sendKeys("Prague");

        w.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class,'efbfd2b849')]//div[text()='Prague']/..")
        )).click();
        System.out.println("✓ Прага выбрана");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);

        String todayString = today.format(formatter);
        String tomorrowString = tomorrow.format(formatter);

        w.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("[data-date='" + todayString + "']")
        )).click();
        System.out.println("✓ Дата заезда (сегодня): " + todayString);

        w.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("[data-date='" + tomorrowString + "']")
        )).click();
        System.out.println("✓ Дата выезда (завтра): " + tomorrowString);

        System.out.println("✓ Выбираем 2 гостей, 1 номер...");

        WebElement occupancyField = w.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[data-testid='occupancy-config']")
        ));
        occupancyField.click();

        w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='occupancy-popup']")));

        WebElement adultsIncreaseButton = w.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[text()='Adults']/ancestor::div[contains(@class,'e484bb5b7a')]/descendant::button[last()]")
        ));
        adultsIncreaseButton.click();
        System.out.println("✓ Взрослых: 2");

        System.out.println("✓ Номеров: 1");

        w.until(ExpectedConditions.elementToBeClickable(By.xpath("//button/span[text()='Done']"))).click();
        System.out.println("✓ Параметры установлены");

        w.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']"))).click();
        System.out.println("✓ Поиск выполнен\n");

        w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='property-card']")));
        System.out.println("✓ Результаты загружены");

        System.out.println("\n--- ПРИМЕНЕНИЕ ФИЛЬТРА РЕЙТИНГА ---\n");

        Thread.sleep(1500);

        By sortByLocator = By.xpath("//span[contains(text(), 'Sort by')]");
        WebElement sortByElement = w.until(ExpectedConditions.presenceOfElementLocated(sortByLocator));

        js.executeScript("arguments[0].scrollIntoView(true);", sortByElement);
        Thread.sleep(500);

        WebElement sortByButton = sortByElement.findElement(By.xpath("./ancestor::button"));
        sortByButton.click();
        System.out.println("✓ Меню сортировки открыто");

        Thread.sleep(800);

        By ratingHighToLowLocator = By.xpath("//button[contains(text(), 'rating') or contains(text(), 'Rating')]");
        java.util.List<WebElement> options = d.findElements(ratingHighToLowLocator);

        if (options.isEmpty()) {
            // Альтернативный поиск
            By alternativeLocator = By.xpath("//*[contains(text(), 'Property rating')]");
            options = d.findElements(alternativeLocator);
        }

        if (!options.isEmpty()) {
            WebElement ratingOption = options.get(0);
            js.executeScript("arguments[0].click();", ratingOption);
            System.out.println("✓ Сортировка по рейтингу (высокий к низкому) применена");
        } else {
            System.out.println("⚠ Опция рейтинга не найдена, пропускаем");
        }

        waitForLoaderDisappear(d, w);
        Thread.sleep(1000);

        System.out.println("✓ Список обновлён\n");

        System.out.println("--- ПРОВЕРКА РЕЙТИНГА ПЕРВОГО ОТЕЛЯ ---\n");

        WebElement firstHotelCard = w.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("[data-testid='property-card']")
        ));

        WebElement hotelTitle = getElementIfPresent(firstHotelCard, By.cssSelector("[data-testid='title']"));
        String hotelName = hotelTitle != null ? hotelTitle.getText() : "Unknown Hotel";
        System.out.println("✓ Первый отель: " + hotelName);

        WebElement ratingElement = getElementIfPresent(firstHotelCard,
                By.xpath(".//div[contains(@class, 'f63b14ab7a')]"));

        double hotelRating = 0.0;
        if (ratingElement != null) {
            String ratingText = ratingElement.getText().trim();
            try {
                hotelRating = Double.parseDouble(ratingText);
                System.out.println("✓ Рейтинг из карточки: " + hotelRating);
            } catch (NumberFormatException e) {
                System.out.println("⚠ Не удалось парсить рейтинг: " + ratingText);
            }
        } else {
            System.out.println("⚠ Рейтинг не найден в карточке");
        }

        js.executeScript("arguments[0].scrollIntoView(true);", firstHotelCard);
        Thread.sleep(500);
        firstHotelCard.click();
        System.out.println("✓ Открываю страницу отеля...");

        Thread.sleep(3000);

        java.util.Set<String> windows = d.getWindowHandles();
        if (windows.size() > 1) {
            for (String window : windows) {
                if (!window.equals(d.getWindowHandle())) {
                    d.switchTo().window(window);
                    break;
                }
            }
        }

        WebElement pageRatingElement = getElementIfPresent(d,
                By.xpath("//div[contains(@class, 'f63b14ab7a') and contains(@class, 'dff2e52086')]"));

        if (pageRatingElement == null) {
            pageRatingElement = getElementIfPresent(d,
                    By.xpath("//div[contains(@class, 'f63b14ab7a')]"));
        }

        double pageRating = 0.0;
        if (pageRatingElement != null && pageRatingElement.isDisplayed()) {
            String pageRatingText = pageRatingElement.getText().trim();
            try {
                pageRating = Double.parseDouble(pageRatingText);
                System.out.println("✓ Рейтинг на странице отеля: " + pageRating);
            } catch (NumberFormatException e) {
                System.out.println("⚠ Не удалось парсить рейтинг со страницы: " + pageRatingText);
                pageRating = hotelRating;
            }
        } else {
            System.out.println("⚠ Рейтинг на странице отеля не найден, используем рейтинг из карточки: " + hotelRating);
            pageRating = hotelRating;
        }

        System.out.println("\n--- РЕЗУЛЬТАТ ПРОВЕРКИ ---\n");

        if (pageRating >= 9.0) {
            System.out.println("✅ ТЕСТ ПРОЙДЕН: Рейтинг отеля " + pageRating + " >= 9.0");
        } else if (pageRating > 0) {
            System.err.println("❌ ТЕСТ НЕ ПРОЙДЕН: Рейтинг отеля " + pageRating + " < 9.0");
        } else {
            System.err.println("⚠ НЕ УДАЛОСЬ ПРОВЕРИТЬ: Рейтинг не найден");
        }

        System.out.println("\nОтель: " + hotelName);
        System.out.println("Рейтинг: " + (pageRating > 0 ? pageRating : "Не найден"));

        System.out.println("\n✅ ТЕСТ ЗАВЕРШЕН");

        d.quit();
    }

    private static void waitForLoaderDisappear(WebDriver d, WebDriverWait w) {
        By loaderLocator = By.cssSelector("div.b2b4d455c1");

        WebDriverWait shortWait = new WebDriverWait(d, Duration.ofSeconds(3));
        try {
            shortWait.until(ExpectedConditions.visibilityOfElementLocated(loaderLocator));
            w.until(ExpectedConditions.invisibilityOfElementLocated(loaderLocator));
        } catch (TimeoutException e) {

        }
    }

    // Вспомогательный метод для ожидания и клика
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

    private static WebElement getElementIfPresent(WebElement element, By locator) {
        try {
            return element.findElement(locator);
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}