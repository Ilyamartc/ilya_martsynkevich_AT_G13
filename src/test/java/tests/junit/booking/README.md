# Booking Tests - JUnit4 Tests Suite

Все тесты находятся в пакете: `tests.junit.booking`

## Тестовые классы:

1. **BookingCurrencyExtractTest.java**

   - Тестирует извлечение информации о валютах с Booking.com
   - Метод теста: `testExtractCurrency()`

2. **BookingLondonTest.java**

   - Тестирует поиск отелей в Лондоне
   - Изменяет стиль 10-го отеля и сохраняет скриншот
   - Метод теста: `testBookingLondon()`

3. **BookingParisTest.java**

   - Тестирует поиск парижских отелей с фильтром 5 звезд
   - Проверяет рейтинг первого отеля
   - Метод теста: `testBookingParisRating()`

4. **BookingPragueRatingTest.java**

   - Тестирует поиск отелей в Праге
   - Проверяет рейтинг отеля на отдельной странице
   - Метод теста: `testPragueHotelRating()`

5. **DemoQASelectMenuTest.java**

   - Тестирует взаимодействие с различными типами выпадающих списков
   - Метод теста: `testSelectMenuInteractions()`

6. **W3SchoolsGoogleTest.java**
   - Тестирует навигацию между W3Schools и Google
   - Выполняет поиск и проверяет результаты
   - Метод теста: `testW3SchoolsToGoogleSearch()`

## Запуск тестов:

### Maven:

```bash
# Запустить все тесты
mvn test

# Запустить конкретный тест
mvn test -Dtest=BookingCurrencyExtractTest

# Запустить все тесты в пакете booking
mvn test -Dtest=tests.junit.booking.*
```

### IDE (IntelliJ IDEA, Eclipse):

- Клик правой кнопкой на класс тестa → Run 'TestClassName'
- Или Ctrl+Shift+F10 (IDEA) / Ctrl+F11 (Eclipse)

## Структура папок:

```
ProjectNew/
└── src/
    └── test/
        └── java/
            └── tests/
                └── junit/
                    └── booking/
                        ├── BookingCurrencyExtractTest.java
                        ├── BookingLondonTest.java
                        ├── BookingParisTest.java
                        ├── BookingPragueRatingTest.java
                        ├── DemoQASelectMenuTest.java
                        └── W3SchoolsGoogleTest.java
```

## Требования:

- Java 8+ (использован Java 23)
- JUnit4
- Selenium WebDriver 4.23.0+
- ChromeDriver (соответствующей версии к Chrome браузеру)

## Инструменты в тестах:

✅ @Before - инициализация WebDriver и параметров
✅ @Test - основная логика тестирования  
✅ @After - закрытие браузера и очистка ресурсов
✅ Явные ожидания (WebDriverWait) вместо Thread.sleep()
✅ Множественные локаторы для надежности
✅ Ассертивные проверки (Assert.assertTrue, Assert.fail)

## Примечания:

- Все тесты используют явное ожидание (ExplicitWait) с timeout 30 секунд
- Тесты полностью независимы друг от друга
- Каждый тест разворачивает и закрывает свой браузер
- Логирование в консоль для отладки
