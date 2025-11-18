package classwork.day17;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.openqa.selenium.TimeoutException;

public class BookingParisTest1 {
    public static void main(String[] args) throws InterruptedException {

        ChromeOptions opt = new ChromeOptions();
        opt.addArguments("--start-maximized");
        WebDriver d = new ChromeDriver(opt);
        WebDriverWait w = new WebDriverWait(d, Duration.ofSeconds(30));
        JavascriptExecutor js = (JavascriptExecutor) d;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate checkInDate = LocalDate.now().plusDays(3);
        String checkInDateString = checkInDate.format(formatter);

        LocalDate checkOutDate = checkInDate.plusDays(7);
        String checkOutDateString = checkOutDate.format(formatter);

        System.out.println("--- РАСЧЕТ ДАТ ---");
        System.out.println("  Заезд: " + checkInDateString);
        System.out.println("  Выезд: " + checkOutDateString);
        System.out.println("--------------------");

        d.get("https://www.booking.com");
        System.out.println("Открыл Booking.com");

        waitAndClickWithTimeout(d, w, By.id("onetrust-accept-btn-handler"), 10, "Куки приняты");

        waitAndClickWithTimeout(d, w, By.cssSelector("button[aria-label='Dismiss sign-in info.']"), 5, "Окно Genius закрыто");

        WebElement input = d.findElement(By.cssSelector("input[placeholder='Where are you going?']"));
        input.click();
        input.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        input.sendKeys("Paris");
        w.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class,'efbfd2b849')]//div[text()='Paris']/..")
        )).click();
        System.out.println("ПАРИЖ ВЫБРАН! Календарь открыт.");

        WebElement dynamicDayIn = w.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("[data-date='" + checkInDateString + "']")
        ));
        dynamicDayIn.click();
        System.out.println("Дата заезда (" + checkInDateString + ") — НАЖАТА!");

        WebElement dynamicDayOut = w.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("[data-date='" + checkOutDateString + "']")
        ));
        dynamicDayOut.click();
        System.out.println("Дата отъезда (" + checkOutDateString + ") — НАЖАТА!");

        System.out.println("\nДАТЫ ВЫБРАНЫ: " + checkInDateString + " – " + checkOutDateString);

        WebElement occupancyField = w.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[data-testid='occupancy-config']")
        ));
        occupancyField.click();
        System.out.println("Модальное окно гостей/номеров ОТКРЫТО!");

        By occupancyPopupLocator = By.cssSelector("[data-testid='occupancy-popup']");
        w.until(ExpectedConditions.visibilityOfElementLocated(occupancyPopupLocator));
        System.out.println("Контейнер гостей прогружен.");

        By adultsIncreaseLocator = By.xpath(
                "//label[text()='Adults']/ancestor::div[contains(@class,'e484bb5b7a')]/descendant::button[last()]"
        );

        By roomsIncreaseLocator = By.xpath(
                "//label[text()='Rooms']/ancestor::div[contains(@class,'e484bb5b7a')]/descendant::button[last()]"
        );

        WebElement adultsPlusButton = w.until(ExpectedConditions.elementToBeClickable(adultsIncreaseLocator));
        adultsPlusButton.click();
        adultsPlusButton.click();
        System.out.println("Взрослых: 4 выбрано.");

        WebElement roomsPlusButton = w.until(ExpectedConditions.elementToBeClickable(roomsIncreaseLocator));
        roomsPlusButton.click();
        System.out.println("Номеров: 2 выбрано.");

        w.until(ExpectedConditions.elementToBeClickable(By.xpath("//button/span[text()='Done']"))).click();
        System.out.println("Нажата кнопка 'Done'.");

        WebElement searchButton = w.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
        searchButton.click();
        System.out.println("\nНажата кнопка 'Search'!");

        System.out.println("Ожидание прогрузки первой карточки отеля...");
        w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='property-card']")));
        System.out.println("Карточки отелей прогружены.");

        By fiveStarsFilterLocator = By.xpath("//div[@data-filters-group='class']//div[text()='5 stars']");
        WebElement fiveStarsFilter = w.until(ExpectedConditions.presenceOfElementLocated(fiveStarsFilterLocator));

        js.executeScript("arguments[0].scrollIntoView(true);", fiveStarsFilter);
        System.out.println("Прокрутка к фильтру '5 stars' выполнена.");

        js.executeScript("arguments[0].click();", fiveStarsFilter);
        System.out.println("Выбран фильтр: 5 звезд (клик через JS)!");

        System.out.println("Ожидание завершения AJAX-загрузки после применения фильтра...");
        waitForLoaderDisappear(d, w);
        System.out.println("AJAX-загрузка завершена. Фильтр 5 звезд применен.");

        Thread.sleep(1000);

        By currentSortLocator = By.xpath("//span[contains(@class,'a9918d47bf') and contains(text(), 'Sort by:')]/..");
        WebElement currentSortDisplay = w.until(ExpectedConditions.elementToBeClickable(currentSortLocator));
        currentSortDisplay.click();
        System.out.println("Нажата текущая сортировка, открыто выпадающее меню.");

        WebElement ratingLowToHighOption = w.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[data-id='class_asc']")
        ));
        ratingLowToHighOption.click();
        System.out.println("Выбрана сортировка: Рейтинг объекта (от низкого к высокому).");

        System.out.println("Ожидание завершения AJAX-загрузки после сортировки...");
        waitForLoaderDisappear(d, w);
        w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='property-card']")));
        System.out.println("Сортировка завершена, результаты загружены.");

        Thread.sleep(1000);

        By assertionLocator = By.xpath(
                "(//div[@data-testid='property-card'])[1]//div[@aria-label='5 out of 5']"
        );

        boolean assertionPassed = isElementPresent(d, assertionLocator, 5);

        if (assertionPassed) {
            System.out.println("\n✅ ПРОВЕРКА УСПЕШНА: Первый отель в списке имеет рейтинг 5 звезд!");
        } else {
            WebElement firstHotelName = getElementIfPresent(d, By.xpath("(//div[@data-testid='property-card'])[1]//div[@data-testid='title']"));
            System.err.println("\n❌ ПРОВЕРКА НЕУДАЧНА: Первый отель не имеет ожидаемого рейтинга 5 звезд.");
            if (firstHotelName != null) {
                System.err.println("Имя первого отеля: " + firstHotelName.getText());
            }

            WebElement filterChecked = getElementIfPresent(d, By.xpath("//div[@data-filters-group='class']//div[text()='5 stars']/ancestor::*[@aria-checked='true']"));
            if (filterChecked != null) {
                System.err.println("(Но фильтр 5 звезд на боковой панели остается активным)");
            } else {
                System.err.println("(Фильтр 5 звезд на боковой панели, кажется, сбросился)");
            }
        }

        System.out.println("\n--- ФИНАЛ СЦЕНАРИЯ ТЕСТА ---");
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

    private static boolean isElementPresent(WebDriver d, By locator, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(timeoutSeconds));
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    private static WebElement getElementIfPresent(WebDriver d, By locator) {
        try {
            return d.findElement(locator);
        } catch (NoSuchElementException e) {
            return null;
        }
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
}