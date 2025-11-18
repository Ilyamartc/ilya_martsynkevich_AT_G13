package tests.junit.booking;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BookingParisTest {
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
        System.out.println("=== ТЕСТ BOOKING PARIS (5 ЗВЕЗД) ===\n");
    }

    @Test
    public void testBookingParisRating() {
        LocalDate checkInDate = LocalDate.now().plusDays(3);
        LocalDate checkOutDate = checkInDate.plusDays(7);

        String checkInDateString = checkInDate.format(dateFormatter);
        String checkOutDateString = checkOutDate.format(dateFormatter);

        System.out.println("--- РАСЧЕТ ДАТ ---");
        System.out.println("  Заезд: " + checkInDateString);
        System.out.println("  Выезд: " + checkOutDateString);
        System.out.println("--------------------\n");

        driver.get("https://www.booking.com");
        System.out.println("Открыл Booking.com");

        waitAndClickWithTimeout(By.id("onetrust-accept-btn-handler"), 10, "Куки приняты");
        waitAndClickWithTimeout(By.cssSelector("button[aria-label='Dismiss sign-in info.']"), 5, "Окно Genius закрыто");

        // Выбор города
        WebElement input = driver.findElement(By.cssSelector("input[placeholder='Where are you going?']"));
        input.click();
        input.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        input.sendKeys("Paris");
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class,'efbfd2b849')]//div[text()='Paris']/..")
        )).click();
        System.out.println("ПАРИЖ ВЫБРАН!");

        // Выбор дат
        WebElement dayIn = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("[data-date='" + checkInDateString + "']")
        ));
        dayIn.click();
        System.out.println("Дата заезда (" + checkInDateString + ") — НАЖАТА!");

        WebElement dayOut = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("[data-date='" + checkOutDateString + "']")
        ));
        dayOut.click();
        System.out.println("Дата отъезда (" + checkOutDateString + ") — НАЖАТА!");
        System.out.println("\nДАТЫ ВЫБРАНЫ: " + checkInDateString + " – " + checkOutDateString);

        // Конфигурация гостей и номеров
        WebElement occupancyField = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[data-testid='occupancy-config']")
        ));
        occupancyField.click();
        System.out.println("Модальное окно гостей/номеров ОТКРЫТО!");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='occupancy-popup']")));
        System.out.println("Контейнер гостей прогружен.");

        // Увеличение количества взрослых
        By adultsIncreaseLocator = By.xpath(
                "//label[text()='Adults']/ancestor::div[contains(@class,'e484bb5b7a')]/descendant::button[last()]"
        );
        WebElement adultsPlusButton = wait.until(ExpectedConditions.elementToBeClickable(adultsIncreaseLocator));
        adultsPlusButton.click();
        adultsPlusButton.click();
        System.out.println("Взрослых: 4 выбрано.");

        // Увеличение количества номеров
        By roomsIncreaseLocator = By.xpath(
                "//label[text()='Rooms']/ancestor::div[contains(@class,'e484bb5b7a')]/descendant::button[last()]"
        );
        WebElement roomsPlusButton = wait.until(ExpectedConditions.elementToBeClickable(roomsIncreaseLocator));
        roomsPlusButton.click();
        System.out.println("Номеров: 2 выбрано.");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button/span[text()='Done']"))).click();
        System.out.println("Нажата кнопка 'Done'.");

        // Поиск
        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
        searchButton.click();
        System.out.println("\nНажата кнопка 'Search'!");

        System.out.println("Ожидание прогрузки первой карточки отеля...");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='property-card']")));
        System.out.println("Карточки отелей прогружены.");

        // Применение фильтра 5 звезд
        By fiveStarsFilterLocator = By.xpath("//div[@data-filters-group='class']//div[text()='5 stars']");
        WebElement fiveStarsFilter = wait.until(ExpectedConditions.presenceOfElementLocated(fiveStarsFilterLocator));

        js.executeScript("arguments[0].scrollIntoView(true);", fiveStarsFilter);
        System.out.println("Прокрутка к фильтру '5 stars' выполнена.");

        js.executeScript("arguments[0].click();", fiveStarsFilter);
        System.out.println("Выбран фильтр: 5 звезд!");

        System.out.println("Ожидание завершения AJAX-загрузки после применения фильтра...");
        waitForLoaderDisappear();
        System.out.println("AJAX-загрузка завершена. Фильтр 5 звезд применен.");

        // Применение сортировки по рейтингу
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("[data-testid='property-card']")));

        By currentSortLocator = By.xpath("//span[contains(@class,'a9918d47bf') and contains(text(), 'Sort by:')]/..");
        WebElement currentSortDisplay = wait.until(ExpectedConditions.elementToBeClickable(currentSortLocator));
        currentSortDisplay.click();
        System.out.println("Нажата текущая сортировка, открыто выпадающее меню.");

        WebElement ratingLowToHighOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[data-id='class_asc']")
        ));
        ratingLowToHighOption.click();
        System.out.println("Выбрана сортировка: Рейтинг объекта (от низкого к высокому).");

        System.out.println("Ожидание завершения AJAX-загрузки после сортировки...");
        waitForLoaderDisappear();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='property-card']")));
        System.out.println("Сортировка завершена, результаты загружены.");

        // Проверка
        By assertionLocator = By.xpath(
                "(//div[@data-testid='property-card'])[1]//div[@aria-label='5 out of 5']"
        );

        boolean assertionPassed = isElementPresent(assertionLocator, 5);

        if (assertionPassed) {
            System.out.println("\n✅ ПРОВЕРКА УСПЕШНА: Первый отель в списке имеет рейтинг 5 звезд!");
        } else {
            WebElement firstHotelName = getElementIfPresent(By.xpath("(//div[@data-testid='property-card'])[1]//div[@data-testid='title']"));
            System.err.println("\n❌ ПРОВЕРКА НЕУДАЧНА: Первый отель не имеет ожидаемого рейтинга 5 звезд.");
            if (firstHotelName != null) {
                System.err.println("Имя первого отеля: " + firstHotelName.getText());
            }

            WebElement filterChecked = getElementIfPresent(By.xpath("//div[@data-filters-group='class']//div[text()='5 stars']/ancestor::*[@aria-checked='true']"));
            if (filterChecked != null) {
                System.err.println("(Но фильтр 5 звезд на боковой панели остается активным)");
            } else {
                System.err.println("(Фильтр 5 звезд на боковой панели, кажется, сбросился)");
            }
        }

        Assert.assertTrue("Первый отель должен иметь рейтинг 5 звезд", assertionPassed);

        System.out.println("\n--- ФИНАЛ СЦЕНАРИЯ ТЕСТА ---");
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
