package tests.junit.booking;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BookingPragueRatingTest {
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
        System.out.println("=== ТЕСТ BOOKING PRAGUE (РЕЙТИНГ) ===\n");
    }

    @Test
    public void testPragueHotelRating() {
        driver.get("https://www.booking.com");
        System.out.println("✓ Открыл Booking.com");

        waitAndClickWithTimeout(By.id("onetrust-accept-btn-handler"), 10, "✓ Куки приняты");
        waitAndClickWithTimeout(By.cssSelector("button[aria-label='Dismiss sign-in info.']"), 5, "✓ Окно Genius закрыто");

        System.out.println("\n--- ПОИСК ОТЕЛЕЙ В ПРАГЕ ---\n");

        // Выбор города
        WebElement input = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("input[placeholder='Where are you going?']")
        ));
        input.click();
        input.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        input.sendKeys("Prague");

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class,'efbfd2b849')]//div[text()='Prague']/..")
        )).click();
        System.out.println("✓ Прага выбрана");

        // Выбор дат (сегодня и завтра)
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);

        String todayString = today.format(dateFormatter);
        String tomorrowString = tomorrow.format(dateFormatter);

        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("[data-date='" + todayString + "']")
        )).click();
        System.out.println("✓ Дата заезда (сегодня): " + todayString);

        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("[data-date='" + tomorrowString + "']")
        )).click();
        System.out.println("✓ Дата выезда (завтра): " + tomorrowString);

        System.out.println("✓ Выбираем 2 гостей, 1 номер...");

        // Конфигурация гостей
        WebElement occupancyField = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[data-testid='occupancy-config']")
        ));
        occupancyField.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='occupancy-popup']")));

        WebElement adultsIncreaseButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[text()='Adults']/ancestor::div[contains(@class,'e484bb5b7a')]/descendant::button[last()]")
        ));
        adultsIncreaseButton.click();
        System.out.println("✓ Взрослых: 2");
        System.out.println("✓ Номеров: 1");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button/span[text()='Done']"))).click();
        System.out.println("✓ Параметры установлены");

        // Поиск
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']"))).click();
        System.out.println("✓ Поиск выполнен\n");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='property-card']")));
        System.out.println("✓ Результаты загружены");

        System.out.println("\n--- ПРИМЕНЕНИЕ ФИЛЬТРА РЕЙТИНГА ---\n");

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("[data-testid='property-card']")));

        // Открытие меню сортировки
        By sortByLocator = By.xpath("//span[contains(text(), 'Sort by')]");
        WebElement sortByElement = wait.until(ExpectedConditions.presenceOfElementLocated(sortByLocator));

        js.executeScript("arguments[0].scrollIntoView(true);", sortByElement);

        WebElement sortByButton = sortByElement.findElement(By.xpath("./ancestor::button"));
        sortByButton.click();
        System.out.println("✓ Меню сортировки открыто");

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//button[contains(text(), 'rating') or contains(text(), 'Rating')]")));

        // Выбор сортировки по рейтингу
        By ratingHighToLowLocator = By.xpath("//button[contains(text(), 'rating') or contains(text(), 'Rating')]");
        java.util.List<WebElement> options = driver.findElements(ratingHighToLowLocator);

        if (options.isEmpty()) {
            By alternativeLocator = By.xpath("//*[contains(text(), 'Property rating')]");
            options = driver.findElements(alternativeLocator);
        }

        if (!options.isEmpty()) {
            WebElement ratingOption = options.get(0);
            js.executeScript("arguments[0].click();", ratingOption);
            System.out.println("✓ Сортировка по рейтингу (высокий к низкому) применена");
        } else {
            System.out.println("⚠ Опция рейтинга не найдена, пропускаем");
        }

        waitForLoaderDisappear();

        System.out.println("✓ Список обновлён\n");

        System.out.println("--- ПРОВЕРКА РЕЙТИНГА ПЕРВОГО ОТЕЛЯ ---\n");

        WebElement firstHotelCard = wait.until(ExpectedConditions.presenceOfElementLocated(
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
        firstHotelCard.click();
        System.out.println("✓ Открываю страницу отеля...");

        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        java.util.Set<String> windows = driver.getWindowHandles();
        for (String window : windows) {
            if (!window.equals(driver.getWindowHandle())) {
                driver.switchTo().window(window);
                break;
            }
        }

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'f63b14ab7a')]")));

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

        System.out.println("Отель: " + hotelName);
        System.out.println("Рейтинг: " + (pageRating > 0 ? pageRating : "Не найден"));

        Assert.assertTrue("Рейтинг должен быть >= 9.0", pageRating >= 9.0);

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

    private void waitForLoaderDisappear() {
        By loaderLocator = By.cssSelector("div.b2b4d455c1");

        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
        try {
            shortWait.until(ExpectedConditions.visibilityOfElementLocated(loaderLocator));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(loaderLocator));
        } catch (TimeoutException e) {
            // Loader может не быть видимым
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
