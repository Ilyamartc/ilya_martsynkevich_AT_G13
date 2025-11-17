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

        // --- ДИНАМИЧЕСКИЙ РАСЧЕТ ДАТ ---
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

        // 1. КУКИ
        try {
            w.until(ExpectedConditions.elementToBeClickable(By.id("onetrust-accept-btn-handler"))).click();
        } catch (TimeoutException ignored) {}

        // 2. GENIUS / ЗАКРЫТИЕ МОДАЛЬНОГО ОКНА
        try {
            w.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[aria-label='Dismiss sign-in info.']"))).click();
        } catch (TimeoutException ignored) {
            System.out.println("Окно Genius не появилось или было закрыто.");
        }

        // 3. ВВОД ПАРИЖА
        WebElement input = d.findElement(By.cssSelector("input[placeholder='Where are you going?']"));
        input.click();
        input.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        input.sendKeys("Paris");
        w.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class,'efbfd2b849')]//div[text()='Paris']/..")
        )).click();
        System.out.println("ПАРИЖ ВЫБРАН! Календарь открыт.");


        // 4. ДИНАМИЧЕСКИЙ ВЫБОР ДАТ
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

        // 5. ВЫБОР КОЛИЧЕСТВА ГОСТЕЙ И НОМЕРОВ: 4 ВЗРОСЛЫХ, 2 НОМЕРА

        // Клик на кнопку выбора гостей, чтобы открыть модальное окно.
        WebElement occupancyField = w.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[data-testid='occupancy-config']")
        ));
        occupancyField.click();
        System.out.println("Модальное окно гостей/номеров ОТКРЫТО!");

        // Ожидаем видимости модального окна (контейнера)
        By occupancyPopupLocator = By.cssSelector("[data-testid='occupancy-popup']");
        w.until(ExpectedConditions.visibilityOfElementLocated(occupancyPopupLocator));
        System.out.println("Контейнер гостей прогружен.");

        // Локатор для кнопки "+" (Взрослые)
        By adultsIncreaseLocator = By.xpath(
                "//label[text()='Adults']/ancestor::div[contains(@class,'e484bb5b7a')]/descendant::button[last()]"
        );

        // Локатор для кнопки "+" (Номера)
        By roomsIncreaseLocator = By.xpath(
                "//label[text()='Rooms']/ancestor::div[contains(@class,'e484bb5b7a')]/descendant::button[last()]"
        );

        // Увеличиваем ВЗРОСЛЫХ до 4 (2 клика)
        WebElement adultsPlusButton = w.until(ExpectedConditions.elementToBeClickable(adultsIncreaseLocator));
        adultsPlusButton.click(); // 2 -> 3
        adultsPlusButton.click(); // 3 -> 4
        System.out.println("Взрослых: 4 выбрано.");

        // Увеличиваем НОМЕРА до 2 (1 клик)
        WebElement roomsPlusButton = w.until(ExpectedConditions.elementToBeClickable(roomsIncreaseLocator));
        roomsPlusButton.click(); // 1 -> 2
        System.out.println("Номеров: 2 выбрано.");

        // 6. КЛИК НА КНОПКУ "Done"
        w.until(ExpectedConditions.elementToBeClickable(By.xpath("//button/span[text()='Done']"))).click();
        System.out.println("Нажата кнопка 'Done'.");

        // 7. НАЖАТИЕ КНОПКИ ПОИСКА (Search)
        WebElement searchButton = w.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
        searchButton.click();
        System.out.println("\nНажата кнопка 'Search'!");

        // 8. ФИЛЬТРАЦИЯ: ВЫБОР 5 ЗВЕЗД
        By fiveStarsFilterLocator = By.xpath("//div[@data-filters-group='class']//div[text()='5 stars']");

        WebElement fiveStarsFilter = w.until(ExpectedConditions.elementToBeClickable(fiveStarsFilterLocator));
        fiveStarsFilter.click();
        System.out.println("Выбран фильтр: 5 звезд!");

        // 9. СОРТИРОВКА ПО РЕЙТИНГУ (ОТ НИЗКОГО К ВЫСОКОМУ)

        // Ожидание загрузки результатов после фильтрации 5 звезд (ждем кликабельности кнопки сортировки)
        System.out.println("Ожидание загрузки результатов после фильтрации 5 звезд...");

        // Локатор для текущего состояния сортировки, на которую нужно кликнуть
        By currentSortLocator = By.xpath("//span[contains(@class,'a9918d47bf') and contains(text(), 'Sort by:')]/..");

        // 9.1. Клик по текущему состоянию сортировки, чтобы открыть выпадающее меню
        WebElement currentSortDisplay = w.until(ExpectedConditions.elementToBeClickable(currentSortLocator));
        currentSortDisplay.click();
        System.out.println("Нажата текущая сортировка, открыто выпадающее меню.");

        // 9.2. ВЫБОР ОПЦИИ СОРТИРОВКИ ПО РЕЙТИНГУ (Используем data-id="class_asc")
        WebElement ratingLowToHighOption = w.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[data-id='class_asc']")
        ));
        ratingLowToHighOption.click();
        System.out.println("Выбрана сортировка: Рейтинг объекта (от низкого к высокому).");

        // --- 10. ОЖИДАНИЕ И ЗАВЕРШЕНИЕ СОРТИРОВКИ ---

        // Ждем, пока сортировка применится и страница обновится.
        w.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.b2b4d455c1"))); // Ждем исчезновения лоадера, если он есть
        w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='property-card']"))); // Ждем появления карточки отеля
        System.out.println("\nСортировка завершена, результаты загружены.");

        // --- 11. ПРОВЕРКА (ASSERTION): У ПЕРВОГО ОТЕЛЯ 5 ЗВЕЗД ---
        try {
            // Локатор, который ищет в первой карточке отеля (индекс 1) элемент, содержащий метку "5-star".
            By assertionLocator = By.xpath(
                    "(//div[@data-testid='property-card'])[1]//div[@data-testid='rating-stars']/span[text()='5-star']"
            );

            w.until(ExpectedConditions.presenceOfElementLocated(assertionLocator));

            System.out.println("\n✅ ПРОВЕРКА УСПЕШНА: Первый отель в списке имеет рейтинг 5 звезд!");

        } catch (TimeoutException | NoSuchElementException e) {
            System.err.println("\n❌ ПРОВЕРКА НЕУДАЧНА: Первый отель не имеет ожидаемого рейтинга 5 звезд.");
            // Дополнительная информация для отладки
            try {
                WebElement firstHotelName = d.findElement(By.xpath("(//div[@data-testid='property-card'])[1]//div[@data-testid='title']"));
                System.err.println("Имя первого отеля: " + firstHotelName.getText());
            } catch (Exception ignored) {
                System.err.println("Не удалось получить имя первого отеля.");
            }
            // Здесь можно добавить throw new AssertionError("Проверка рейтинга не пройдена"); если это критичный тест
        }

        System.out.println("\n--- ФИНАЛ СЦЕНАРИЯ ТЕСТА ---");

        Thread.sleep(30000);
        d.quit();
    }
}