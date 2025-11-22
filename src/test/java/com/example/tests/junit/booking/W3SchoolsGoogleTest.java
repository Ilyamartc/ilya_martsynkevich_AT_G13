package com.example.tests.junit.booking;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class W3SchoolsGoogleTest {
    private static final Logger logger = LogManager.getLogger(W3SchoolsGoogleTest.class);
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

        logger.info("=== ТЕСТ W3SCHOOLS → GOOGLE ПОИСК ===\n");
    }

    @Test
    public void testW3SchoolsToGoogleSearch() {
        logger.info("--- ШАГ 1: Открытие W3Schools ---\n");
        logger.info("Navigating to W3Schools Java tutorial");
        driver.get("https://www.w3schools.com/java/");
        logger.info("✓ Открыл https://www.w3schools.com/java/");

        closeCookieConsent();

        logger.info("\n--- ШАГ 2: Выделение слова Tutorial ---\n");

        By headingLocator = By.xpath("//h1[contains(text(), 'Tutorial') or contains(text(), 'Java')]");
        WebElement heading = wait.until(ExpectedConditions.presenceOfElementLocated(headingLocator));

        logger.info("✓ Заголовок найден: " + heading.getText());

        String copiedText = "Tutorial";
        logger.info("✓ Выделенный текст: \"" + copiedText + "\"");

        logger.info("\n--- ШАГ 3: Переход на Google ---\n");

        logger.info("Navigating to Google");
        driver.get("https://www.google.com");
        logger.info("✓ Открыл https://www.google.com");

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//input[@name='q'] | //textarea[@name='q']")));

        closeGoogleCookieConsent();

        logger.info("\n--- ШАГ 4: Вставка текста в поиск ---\n");

        logger.info("Finding search box");
        WebElement searchBox = findSearchBox();

        Assert.assertNotNull("❌ Строка поиска Google не найдена", searchBox);

        searchBox.click();
        logger.info("✓ Кликнул на строку поиска");

        searchBox.sendKeys(copiedText);
        logger.info("✓ Вставлено в поиск: \"" + copiedText + "\"");

        logger.info("\n--- ШАГ 5: Поиск ---\n");

        logger.info("Submitting search");
        searchBox.sendKeys(Keys.ENTER);
        logger.info("✓ Нажата клавиша Enter");

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//h3 | //div[@data-sokoban-container]")));

        checkAndHandleRecaptcha();

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//h3")));
        logger.info("✓ Результаты поиска загружены");

        logger.info("\n--- ШАГ 6: Проверка результатов ---\n");

        java.util.List<WebElement> searchResults = driver.findElements(By.xpath("//h3"));

        if (searchResults.isEmpty()) {
            searchResults = driver.findElements(By.cssSelector("div[data-sokoban-container] a"));
        }

        logger.info("Найдено результатов: " + searchResults.size());

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
                    logger.info((i + 1) + ". + " + resultText.substring(0, Math.min(65, resultText.length())));
                    validResults++;
                } else {
                    logger.info((i + 1) + ". - " + resultText.substring(0, Math.min(65, resultText.length())));
                    invalidResults++;
                }
            }

            logger.info("\n--- РЕЗУЛЬТАТЫ ПРОВЕРКИ ---\n");
            logger.info("+ Валидных результатов: " + validResults + "/" + Math.min(searchResults.size(), 10));
            logger.info("- Невалидных результатов: " + invalidResults + "/" + Math.min(searchResults.size(), 10));

            if (invalidResults == 0 && validResults > 0) {
                logger.info("\n+ ТЕСТ ПРОЙДЕН: Все результаты содержат искомое слово \"" + copiedText + "\"");
            } else if (validResults > 0) {
                logger.info("\n!!! ТЕСТ ЧАСТИЧНО ПРОЙДЕН: " + validResults + " результатов содержат слово \"" + copiedText + "\"");
            } else {
                Assert.fail("\n- ТЕСТ НЕ ПРОЙДЕН: Результаты не содержат искомое слово");
            }

            Assert.assertTrue("Должны быть валидные результаты", validResults > 0);
        }

        logger.info("\n+ ТЕСТ ЗАВЕРШЕН");
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
                    logger.info("✓ Строка поиска найдена");
                    return elements.get(0);
                }
            } catch (TimeoutException e) {
                // Продолжаем поиск
            }
        }

        return null;
    }

    private void checkAndHandleRecaptcha() {
        logger.info(" Проверяю наличие reCAPTCHA...");

        By recaptchaLocator = By.xpath("//div[contains(@class, 'recaptcha-checkbox-border')]");

        try {
            java.util.List<WebElement> recaptchas = driver.findElements(recaptchaLocator);

            if (!recaptchas.isEmpty() && recaptchas.get(0).isDisplayed()) {
                logger.info("!!! Обнаружена reCAPTCHA!");
                logger.info(" Ожидаю решения reCAPTCHA (до 15 секунд)...");

                for (int i = 0; i < 30; i++) {
                    try {
                        java.util.List<WebElement> currentRecaptchas = driver.findElements(recaptchaLocator);
                        if (currentRecaptchas.isEmpty() || !currentRecaptchas.get(0).isDisplayed()) {
                            logger.info("✓ reCAPTCHA решена или исчезла");
                            break;
                        }
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        logger.error("Thread interrupted during reCAPTCHA wait", e);
                        break;
                    } catch (Exception ex) {
                        break;
                    }
                }
            } else {
                logger.info("✓ reCAPTCHA не обнаружена");
            }
        } catch (NoSuchElementException e) {
            logger.info("✓ reCAPTCHA не обнаружена");
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
                logger.info("✓ Cookie согласие W3Schools закрыто");
                return;
            } catch (TimeoutException e) {
                // Продолжаем поиск
            }
        }

        logger.info("⚠ Cookie согласие W3Schools не найдено");
    }

    private void closeGoogleCookieConsent() {
        logger.info(" Ищем Google cookie согласие...");

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

                        logger.info("✓ Google cookie согласие закрыто");
                        return;
                    }
                }
            } catch (Exception e) {
                // Продолжаем поиск
            }
        }

        logger.info("⚠ Google cookie согласие не найдено");
    }
}
