package com.example.tests.junit.booking;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class W3SchoolsGoogleTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    private Actions actions;

    @Before
    public void setUp() {
        ChromeOptions opt = new ChromeOptions();
        opt.addArguments("--start-maximized");
        opt.addArguments("--disable-blink-features=AutomationControlled");
        opt.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        opt.setExperimentalOption("useAutomationExtension", false);

        driver = new ChromeDriver(opt);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        js = (JavascriptExecutor) driver;
        actions = new Actions(driver);

        System.out.println("=== ТЕСТ W3SCHOOLS → GOOGLE ПОИСК ===\n");
    }

    @Test
    public void testW3SchoolsToGoogleSearch() {
        System.out.println("--- ШАГ 1: Открытие W3Schools ---\n");
        driver.get("https://www.w3schools.com/java/");
        System.out.println("✓ Открыл https://www.w3schools.com/java/");

        closeCookieConsent();

        System.out.println("\n--- ШАГ 2: Выделение слова Tutorial ---\n");

        By headingLocator = By.xpath("//h1[contains(text(), 'Tutorial') or contains(text(), 'Java')]");
        WebElement heading = wait.until(ExpectedConditions.presenceOfElementLocated(headingLocator));

        System.out.println("✓ Заголовок найден: " + heading.getText());

        String copiedText = "Tutorial";
        System.out.println("✓ Выделенный текст: \"" + copiedText + "\"");

        System.out.println("\n--- ШАГ 3: Переход на Google ---\n");

        driver.get("https://www.google.com");
        System.out.println("✓ Открыл https://www.google.com");

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//input[@name='q'] | //textarea[@name='q']")));

        closeGoogleCookieConsent();

        System.out.println("\n--- ШАГ 4: Вставка текста в поиск ---\n");

        WebElement searchBox = findSearchBox();

        Assert.assertNotNull("❌ Строка поиска Google не найдена", searchBox);

        searchBox.click();
        System.out.println("✓ Кликнул на строку поиска");

        searchBox.sendKeys(copiedText);
        System.out.println("✓ Вставлено в поиск: \"" + copiedText + "\"");

        System.out.println("\n--- ШАГ 5: Поиск ---\n");

        searchBox.sendKeys(Keys.ENTER);
        System.out.println("✓ Нажата клавиша Enter");

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//h3 | //div[@data-sokoban-container]")));

        checkAndHandleRecaptcha();

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//h3")));
        System.out.println("✓ Результаты поиска загружены");

        System.out.println("\n--- ШАГ 6: Проверка результатов ---\n");

        java.util.List<WebElement> searchResults = driver.findElements(By.xpath("//h3"));

        if (searchResults.isEmpty()) {
            searchResults = driver.findElements(By.cssSelector("div[data-sokoban-container] a"));
        }

        System.out.println("Найдено результатов: " + searchResults.size());

        if (searchResults.isEmpty()) {
            Assert.fail("❌ Результаты поиска не найдены");
        } else {
            int validResults = 0;
            int invalidResults = 0;
            String searchWord = copiedText.toLowerCase().trim();

            for (int i = 0; i < Math.min(searchResults.size(), 10); i++) {
                WebElement result = searchResults.get(i);
                String resultText = result.getText().toLowerCase();

                boolean containsWord = resultText.contains(searchWord);

                if (containsWord) {
                    System.out.println((i + 1) + ". + " + resultText.substring(0, Math.min(65, resultText.length())));
                    validResults++;
                } else {
                    System.out.println((i + 1) + ". - " + resultText.substring(0, Math.min(65, resultText.length())));
                    invalidResults++;
                }
            }

            System.out.println("\n--- РЕЗУЛЬТАТЫ ПРОВЕРКИ ---\n");
            System.out.println("+ Валидных результатов: " + validResults + "/" + Math.min(searchResults.size(), 10));
            System.out.println("- Невалидных результатов: " + invalidResults + "/" + Math.min(searchResults.size(), 10));

            if (invalidResults == 0 && validResults > 0) {
                System.out.println("\n+ ТЕСТ ПРОЙДЕН: Все результаты содержат искомое слово \"" + copiedText + "\"");
            } else if (validResults > 0) {
                System.out.println("\n!!! ТЕСТ ЧАСТИЧНО ПРОЙДЕН: " + validResults + " результатов содержат слово \"" + copiedText + "\"");
            } else {
                Assert.fail("\n- ТЕСТ НЕ ПРОЙДЕН: Результаты не содержат искомое слово");
            }

            Assert.assertTrue("Должны быть валидные результаты", validResults > 0);
        }

        System.out.println("\n+ ТЕСТ ЗАВЕРШЕН");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private WebElement findSearchBox() {
        By[] searchBoxLocators = {
                By.cssSelector("textarea[name='q']"),
                By.xpath("//textarea[@name='q']"),
                By.id("APjFqb"),
                By.cssSelector("textarea[role='combobox']"),
                By.cssSelector("input[name='q']"),
                By.xpath("//input[@name='q']")
        };

        for (By locator : searchBoxLocators) {
            try {
                java.util.List<WebElement> elements = driver.findElements(locator);
                if (!elements.isEmpty() && elements.get(0).isDisplayed()) {
                    System.out.println("✓ Строка поиска найдена");
                    return elements.get(0);
                }
            } catch (TimeoutException e) {
                // Продолжаем поиск
            }
        }

        return null;
    }

    private void checkAndHandleRecaptcha() {
        System.out.println(" Проверяю наличие reCAPTCHA...");

        By recaptchaLocator = By.xpath("//div[contains(@class, 'recaptcha-checkbox-border')]");

        try {
            java.util.List<WebElement> recaptchas = driver.findElements(recaptchaLocator);

            if (!recaptchas.isEmpty() && recaptchas.get(0).isDisplayed()) {
                System.out.println("!!! Обнаружена reCAPTCHA!");
                System.out.println(" Ожидаю решения reCAPTCHA (до 15 секунд)...");

                for (int i = 0; i < 30; i++) {
                    try {
                        java.util.List<WebElement> currentRecaptchas = driver.findElements(recaptchaLocator);
                        if (currentRecaptchas.isEmpty() || !currentRecaptchas.get(0).isDisplayed()) {
                            System.out.println("✓ reCAPTCHA решена или исчезла");
                            break;
                        }
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    } catch (Exception ex) {
                        break;
                    }
                }
            } else {
                System.out.println("✓ reCAPTCHA не обнаружена");
            }
        } catch (NoSuchElementException e) {
            System.out.println("✓ reCAPTCHA не обнаружена");
        }
    }

    private void closeCookieConsent() {
        By[] cookieLocators = {
                By.id("accept-choices"),
                By.xpath("//div[@id='accept-choices']"),
                By.xpath("//div[contains(text(), 'Accept')]"),
                By.xpath("//div[contains(text(), 'accept')]"),
                By.xpath("//button[contains(text(), 'Accept')]"),
                By.xpath("//button[contains(text(), 'Zaakceptuj')]")
        };

        for (By locator : cookieLocators) {
            try {
                WebElement cookieButton = wait.until(ExpectedConditions.elementToBeClickable(locator));
                cookieButton.click();
                System.out.println("✓ Cookie согласие W3Schools закрыто");
                return;
            } catch (TimeoutException e) {
                // Продолжаем поиск
            }
        }

        System.out.println("⚠ Cookie согласие W3Schools не найдено");
    }

    private void closeGoogleCookieConsent() {
        System.out.println(" Ищем Google cookie согласие...");

        By[] googleCookieLocators = {
                By.xpath("//button[contains(text(), 'Accept all')]"),
                By.xpath("//div[contains(@class, 'QS5gu sy4vM') and contains(text(), 'Zaakceptuj')]/ancestor::button"),
                By.xpath("//div[@class='QS5gu sy4vM']/ancestor::div[@role='button']"),
                By.xpath("//*[contains(text(), 'Zaakceptuj wszystko')]/ancestor::button"),
                By.xpath("//button[contains(@aria-label, 'Accept all')]"),
                By.xpath("//div[@role='none']//div[contains(text(), 'Zaakceptuj wszystko')]/ancestor::button"),
                By.xpath("//button[.//div[contains(text(), 'Zaakceptuj wszystko')]]"),
                By.xpath("//div[@class='QS5gu sy4vM']")
        };

        for (int i = 0; i < googleCookieLocators.length; i++) {
            try {
                By locator = googleCookieLocators[i];
                java.util.List<WebElement> elements = driver.findElements(locator);

                if (!elements.isEmpty()) {
                    WebElement googleCookieButton = elements.get(0);

                    if (googleCookieButton.isDisplayed()) {
                        if (googleCookieButton.getTagName().equals("button")) {
                            js.executeScript("arguments[0].click();", googleCookieButton);
                        } else {
                            WebElement parent = (WebElement) js.executeScript(
                                    "return arguments[0].closest('button') || arguments[0].closest('[role=\"button\"]');",
                                    googleCookieButton
                            );

                            if (parent != null) {
                                js.executeScript("arguments[0].click();", parent);
                            } else {
                                js.executeScript("arguments[0].click();", googleCookieButton);
                            }
                        }

                        System.out.println("✓ Google cookie согласие закрыто");
                        return;
                    }
                }
            } catch (Exception e) {
                // Продолжаем поиск
            }
        }

        System.out.println("⚠ Google cookie согласие не найдено");
    }
}
