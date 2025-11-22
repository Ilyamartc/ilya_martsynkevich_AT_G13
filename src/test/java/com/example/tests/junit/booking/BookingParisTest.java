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

public class BookingParisTest {
    private static final Logger logger = LogManager.getLogger(BookingParisTest.class);
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
        logger.info("=== ТЕСТ BOOKING PARIS (5 ЗВЕЗД) ===\n");
    }

    @Test
    public void testBookingParisRating() {
        LocalDate checkInDate = LocalDate.now().plusDays(3);
        LocalDate checkOutDate = checkInDate.plusDays(7);

        String checkInDateString = checkInDate.format(dateFormatter);
        String checkOutDateString = checkOutDate.format(dateFormatter);

        logger.info("--- РАСЧЕТ ДАТ ---");
        logger.info("  Заезд: " + checkInDateString);
        logger.info("  Выезд: " + checkOutDateString);
        logger.info("--------------------\n");

        logger.info("Navigating to Booking.com");
        driver.get("https://www.booking.com");
        logger.info("Открыл Booking.com");

        waitAndClickWithTimeout(By.id("onetrust-accept-btn-handler"), 10, "Куки приняты");
        waitAndClickWithTimeout(By.cssSelector("button[aria-label='Dismiss sign-in info.']"), 5, "Окно Genius закрыто");

        // Выбор города
        logger.info("Selecting city: Paris");
        WebElement input = driver.findElement(By.cssSelector("input[placeholder='Where are you going?']"));
        input.click();
        input.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        input.sendKeys("Paris");
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class,'efbfd2b849')]//div[text()='Paris']/.."))
        ).click();
        logger.info("ПАРИЖ ВЫБРАН!");

        // Выбор дат
        logger.info("Selecting check-in date");
        WebElement dayIn = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("[data-date='" + checkInDateString + "']")
        ));
        dayIn.click();
        logger.info("Дата заезда (" + checkInDateString + ") — НАЖАТА!");

        logger.info("Selecting check-out date");
        WebElement dayOut = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("[data-date='" + checkOutDateString + "']")
        ));
        dayOut.click();
        logger.info("Дата отъезда (" + checkOutDateString + ") — НАЖАТА!");
        logger.info("\nДАТЫ ВЫБРАНЫ: " + checkInDateString + " – " + checkOutDateString);

        // Конфигурация гостей и номеров
        logger.info("Opening occupancy configuration");
        WebElement occupancyField = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[data-testid='occupancy-config']")
        ));
        occupancyField.click();
        logger.info("Модальное окно гостей/номеров ОТКРЫТО!");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='occupancy-popup']")));
        logger.info("Контейнер гостей прогружен.");

        // Увеличение количества взрослых
        logger.info("Selecting 4 adults");
        By adultsIncreaseLocator = By.xpath(
                "//label[text()='Adults']/ancestor::div[contains(@class,'e484bb5b7a')]/descendant::button[last()]"
        );
        WebElement adultsPlusButton = wait.until(ExpectedConditions.elementToBeClickable(adultsIncreaseLocator));
        adultsPlusButton.click();
        adultsPlusButton.click();
        logger.info("Взрослых: 4 выбрано.");

        // Увеличение количества номеров
        logger.info("Selecting 2 rooms");
        By roomsIncreaseLocator = By.xpath(
                "//label[text()='Rooms']/ancestor::div[contains(@class,'e484bb5b7a')]/descendant::button[last()]"
        );
        WebElement roomsPlusButton = wait.until(ExpectedConditions.elementToBeClickable(roomsIncreaseLocator));
        roomsPlusButton.click();
        logger.info("Номеров: 2 выбрано.");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button/span[text()='Done']"))).click();
        logger.info("Нажата кнопка 'Done'.");

        // Поиск
        logger.info("Submitting search");
        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
        searchButton.click();
        logger.info("\nНажата кнопка 'Search'!");

        logger.info("Ожидание прогрузки первой карточки отеля...");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='property-card']")));
        logger.info("Карточки отелей прогружены.");

        // Применение фильтра 5 звезд
        logger.info("Applying 5 stars filter");
        By fiveStarsFilterLocator = By.xpath("//div[@data-filters-group='class']//div[text()='5 stars']");
        WebElement fiveStarsFilter = wait.until(ExpectedConditions.presenceOfElementLocated(fiveStarsFilterLocator));

        js.executeScript("arguments[0].scrollIntoView(true);", fiveStarsFilter);
        logger.info("Прокрутка к фильтру '5 stars' выполнена.");

        js.executeScript("arguments[0].click();", fiveStarsFilter);
        logger.info("Выбран фильтр: 5 звезд!");

        logger.info("Ожидание завершения AJAX-загрузки после применения фильтра...");
        waitForLoaderDisappear();
        logger.info("AJAX-загрузка завершена. Фильтр 5 звезд применен.");

        // Применение сортировки по рейтингу
        logger.info("Applying sorting by rating");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("[data-testid='property-card']")));

        By currentSortLocator = By.xpath("//span[contains(@class,'a9918d47bf') and contains(text(), 'Sort by:')]/..");
        WebElement currentSortDisplay = wait.until(ExpectedConditions.presenceOfElementLocated(currentSortLocator));
        
        // Прокрутка к элементу и JavaScript клик
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", currentSortDisplay);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            logger.error("Thread interrupted during sleep", e);
        }
        js.executeScript("arguments[0].click();", currentSortDisplay);
        logger.info("Нажата текущая сортировка, открыто выпадающее меню.");

        WebElement ratingLowToHighOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[data-id='class_asc']")
        ));
        ratingLowToHighOption.click();
        logger.info("Выбрана сортировка: Рейтинг объекта (от низкого к высокому).");

        logger.info("Ожидание завершения AJAX-загрузки после сортировки...");
        waitForLoaderDisappear();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='property-card']")));
        logger.info("Сортировка завершена, результаты загружены.");

        // Проверка
        By assertionLocator = By.xpath(
                "(//div[@data-testid='property-card'])[1]//div[@aria-label='5 out of 5']"
        );

        boolean assertionPassed = isElementPresent(assertionLocator, 5);

        if (assertionPassed) {
            logger.info("\n✅ ПРОВЕРКА УСПЕШНА: Первый отель в списке имеет рейтинг 5 звезд!");
        } else {
            WebElement firstHotelName = getElementIfPresent(By.xpath("(//div[@data-testid='property-card'])[1]//div[@data-testid='title']"));
            logger.error("\n❌ ПРОВЕРКА НЕУДАЧНА: Первый отель не имеет ожидаемого рейтинга 5 звезд.");
            if (firstHotelName != null) {
                logger.error("Имя первого отеля: " + firstHotelName.getText());
            }

            WebElement filterChecked = getElementIfPresent(By.xpath("//div[@data-filters-group='class']//div[text()='5 stars']/ancestor::*[@aria-checked='true']"));
            if (filterChecked != null) {
                logger.error("(Но фильтр 5 звезд на боковой панели остается активным)");
            } else {
                logger.error("(Фильтр 5 звезд на боковой панели, кажется, сбросился)");
            }
        }

        Assert.assertTrue("Первый отель должен иметь рейтинг 5 звезд", assertionPassed);

        logger.info("\n--- ФИНАЛ СЦЕНАРИЯ ТЕСТА ---");
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

    private boolean isElementPresent(By locator, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    private WebElement getElementIfPresent(By locator) {
        try {
            return driver.findElement(locator);
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    private void waitForLoaderDisappear() {
        By loaderLocator = By.cssSelector("div.b2b4d455c1");

        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
        try {
            shortWait.until(ExpectedConditions.visibilityOfElementLocated(loaderLocator));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(loaderLocator));
        } catch (TimeoutException e) {
            // Loader может не быть видимым всегда
        }
    }
}
