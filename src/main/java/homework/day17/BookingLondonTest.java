package classwork.day17;

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
    public static void main(String[] args) throws InterruptedException {

        ChromeOptions opt = new ChromeOptions();
        opt.addArguments("--start-maximized");
        WebDriver d = new ChromeDriver(opt);
        WebDriverWait w = new WebDriverWait(d, Duration.ofSeconds(30));
        JavascriptExecutor js = (JavascriptExecutor) d;

        System.out.println("=== ТЕСТ BOOKING LONDON ===\n");

        d.get("https://www.booking.com");
        System.out.println("✓ Открыл Booking.com");

        waitAndClickWithTimeout(d, w, By.id("onetrust-accept-btn-handler"), 10, "✓ Куки приняты");

        waitAndClickWithTimeout(d, w, By.cssSelector("button[aria-label='Dismiss sign-in info.']"), 5, "✓ Окно Genius закрыто");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate checkInDate = LocalDate.now().plusDays(3);
        LocalDate checkOutDate = checkInDate.plusDays(7);

        WebElement input = d.findElement(By.cssSelector("input[placeholder='Where are you going?']"));
        input.click();
        input.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        input.sendKeys("London");

        w.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class,'efbfd2b849')]//div[text()='London']/..")
        )).click();
        System.out.println("✓ Город LONDON выбран");

        String checkInDateString = checkInDate.format(formatter);
        String checkOutDateString = checkOutDate.format(formatter);

        WebElement dayIn = w.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("[data-date='" + checkInDateString + "']")
        ));
        dayIn.click();

        WebElement dayOut = w.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("[data-date='" + checkOutDateString + "']")
        ));
        dayOut.click();
        System.out.println("✓ Даты выбраны: " + checkInDateString + " – " + checkOutDateString);

        w.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']"))).click();
        System.out.println("✓ Нажата кнопка Search");

        System.out.println(" Ожидаем загрузку отелей...");
        w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='property-card']")));
        System.out.println("✓ Отели загружены");

        java.util.List<WebElement> hotelCards = d.findElements(By.cssSelector("[data-testid='property-card']"));

        if (hotelCards.size() < 10) {
            for (int i = 0; i < 5; i++) {
                js.executeScript("window.scrollBy(0, 500);");
                Thread.sleep(500);
                hotelCards = d.findElements(By.cssSelector("[data-testid='property-card']"));
                if (hotelCards.size() >= 10) break;
            }
        }

        if (hotelCards.size() < 10) {
            System.err.println("❌ Не удалось найти 10 отелей на странице");
            d.quit();
            return;
        }

        WebElement tenthHotel = hotelCards.get(9); // 10-й отель (0-индексация)
        js.executeScript("arguments[0].scrollIntoView(true);", tenthHotel);
        System.out.println("✓ Прокручен к 10-му отелю");

        Thread.sleep(500); // Небольшая пауза для стабильности

        js.executeScript("arguments[0].style.backgroundColor = 'green';", tenthHotel);
        System.out.println("✓ Фон 10-го отеля изменен на ЗЕЛЕНЫЙ");

        WebElement hotelTitle = tenthHotel.findElement(By.cssSelector("[data-testid='title']"));
        js.executeScript("arguments[0].style.color = 'red';", hotelTitle);
        System.out.println("✓ Цвет текста названия отеля изменен на КРАСНЫЙ");

        Thread.sleep(500);

        String screenshotPath = takeScreenshot(d);
        System.out.println("✓ Скриншот сохранён: " + screenshotPath);

        System.out.println("\n=== ТЕСТ ЗАВЕРШЕН УСПЕШНО ===");

        d.quit();
    }

    private static void waitAndClickWithTimeout(WebDriver d, WebDriverWait w, By locator, int timeoutSeconds, String message) {
        WebDriverWait shortWait = new WebDriverWait(d, Duration.ofSeconds(timeoutSeconds));
        try {
            shortWait.until(ExpectedConditions.elementToBeClickable(locator)).click();
            System.out.println(message);
        } catch (TimeoutException e) {
            // Элемент не найден - это допустимо
        }
    }

    private static String takeScreenshot(WebDriver d) {
        try {
            File screenshot = ((TakesScreenshot) d).getScreenshotAs(OutputType.FILE);
            String filePath = "target/screenshot_" + System.currentTimeMillis() + ".png";
            FileHandler.copy(screenshot, new File(filePath));
            return filePath;
        } catch (Exception e) {
            System.err.println("Ошибка при создании скриншота: " + e.getMessage());
            return "Ошибка";
        }
    }
}