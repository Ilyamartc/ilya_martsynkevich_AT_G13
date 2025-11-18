package classwork.day17;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.openqa.selenium.TimeoutException;

public class BookingParisTest {
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

        try {
            w.until(ExpectedConditions.elementToBeClickable(By.id("onetrust-accept-btn-handler"))).click();
        } catch (TimeoutException ignored) {}

        try {
            w.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[aria-label='Dismiss sign-in info.']"))).click();
        } catch (TimeoutException ignored) {
            System.out.println("Окно Genius не появилось или было закрыто.");
        }

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
        adultsPlusButton.click(); // 2 -> 3
        adultsPlusButton.click(); // 3 -> 4
        System.out.println("Взрослых: 4 выбрано.");

        WebElement roomsPlusButton = w.until(ExpectedConditions.elementToBeClickable(roomsIncreaseLocator));
        roomsPlusButton.click(); // 1 -> 2
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
        try {
            w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.b2b4d455c1")));
        } catch (TimeoutException ignored) {
            System.out.println("Лоадер может быть уже невидим.");
        }
        w.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.b2b4d455c1")));
        System.out.println("AJAX-загрузка завершена. Фильтр 5 звезд применен.");

        try {
            w.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//div[text()='5 stars']/ancestor::*[@aria-checked='true']")
            ));
            System.out.println("Фильтр 5 звезд активен (чекбокс отмечен).");
        } catch (TimeoutException e) {
            System.out.println("Предупреждение: не удалось подтвердить активность фильтра, но продолжаем...");
        }

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

        try {
            w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.b2b4d455c1")));
        } catch (TimeoutException ignored) {
            System.out.println("Лоадер может быть уже невидим после сортировки.");
        }

        w.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.b2b4d455c1")));
        w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='property-card']")));
        System.out.println("Сортировка завершена, результаты загружены.");

        Thread.sleep(1000);

        try {
            By assertionLocator = By.xpath(
                    "(//div[@data-testid='property-card'])[1]//div[@aria-label='5 out of 5']"
            );

            w.until(ExpectedConditions.presenceOfElementLocated(assertionLocator));

            System.out.println("\n✅ ПРОВЕРКА УСПЕШНА: Первый отель в списке имеет рейтинг 5 звезд!");

        } catch (TimeoutException | NoSuchElementException e) {

            WebElement firstHotelName = null;
            try {
                firstHotelName = d.findElement(By.xpath("(//div[@data-testid='property-card'])[1]//div[@data-testid='title']"));
            } catch (Exception ignored) {}

            String errorMessage = "\n❌ ПРОВЕРКА НЕУДАЧНА: Первый отель не имеет ожидаемого рейтинга 5 звезд.";
            if (firstHotelName != null) {
                errorMessage += "\nИмя первого отеля: " + firstHotelName.getText();
            }
            try {
                WebElement filterChecked = d.findElement(By.xpath("//div[@data-filters-group='class']//div[text()='5 stars']/ancestor::*[@aria-checked='true']"));
                if (filterChecked.isDisplayed()) {
                    errorMessage += "\n(Но фильтр 5 звезд на боковой панели остается активным)";
                }
            } catch (Exception ignored) {
                errorMessage += "\n(Фильтр 5 звезд на боковой панели, кажется, сбросился)";
            }

            System.err.println(errorMessage);
        }

        System.out.println("\n--- ФИНАЛ СЦЕНАРИЯ ТЕСТА ---");

        d.quit();
    }
}