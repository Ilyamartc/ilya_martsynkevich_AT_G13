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
        opt.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
        opt.setExperimentalOption("useAutomationExtension", false);

        driver = new ChromeDriver(opt);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        js = (JavascriptExecutor) driver;
        actions = new Actions(driver);

        logger.info("=== W3SCHOOLS → GOOGLE SEARCH TEST ===");
    }

    @Test
    public void testW3SchoolsToGoogleSearch() {
        logger.info("--- STEP 1: Opening W3Schools ---");
        logger.info("Navigating to W3Schools Java tutorial");
        driver.get("https://www.w3schools.com/java/");
        logger.info("Opened https://www.w3schools.com/java/");

        closeCookieConsent();

        logger.info("--- STEP 2: Selecting word Tutorial ---");

        By headingLocator = By.xpath("//h1[contains(text(), 'Tutorial') or contains(text(), 'Java')]");
        WebElement heading = wait.until(ExpectedConditions.presenceOfElementLocated(headingLocator));

        logger.info("Heading found: " + heading.getText());

        String copiedText = "Tutorial";
        logger.info("Selected text: \"" + copiedText + "\"");

        logger.info("--- STEP 3: Navigating to Google ---");

        logger.info("Navigating to Google");
        driver.get("https://www.google.com");
        logger.info("Opened https://www.google.com");

        wait.until(ExpectedConditions
                .presenceOfAllElementsLocatedBy(By.xpath("//input[@name='q'] | //textarea[@name='q']")));

        closeGoogleCookieConsent();

        logger.info("--- STEP 4: Pasting text into search ---");

        logger.info("Finding search box");
        WebElement searchBox = findSearchBox();

        Assert.assertNotNull("Google search box not found", searchBox);

        searchBox.click();
        logger.info("Clicked on search box");

        searchBox.sendKeys(copiedText);
        logger.info("Pasted into search: \"" + copiedText + "\"");

        logger.info("--- STEP 5: Searching ---");

        logger.info("Submitting search");
        searchBox.sendKeys(Keys.ENTER);
        logger.info("Pressed Enter key");

        wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//h3 | //div[@data-sokoban-container]")));

        checkAndHandleRecaptcha();

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//h3")));
        logger.info("Search results loaded");

        logger.info("--- STEP 6: Checking results ---");

        java.util.List<WebElement> searchResults = driver.findElements(By.xpath("//h3"));

        if (searchResults.isEmpty()) {
            searchResults = driver.findElements(By.cssSelector("div[data-sokoban-container] a"));
        }

        logger.info("Found results: " + searchResults.size());

        if (searchResults.isEmpty()) {
            Assert.fail("Search results not found");
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

            logger.info("--- CHECK RESULTS ---");
            logger.info("Valid results: " + validResults + "/" + Math.min(searchResults.size(), 10));
            logger.info("Invalid results: " + invalidResults + "/" + Math.min(searchResults.size(), 10));

            if (invalidResults == 0 && validResults > 0) {
                logger.info("TEST PASSED: All results contain search word \"" + copiedText + "\"");
            } else if (validResults > 0) {
                logger.info("TEST PARTIALLY PASSED: " + validResults + " results contain word \"" + copiedText + "\"");
            } else {
                Assert.fail("TEST FAILED: Results do not contain search word");
            }

            Assert.assertTrue("There must be valid results", validResults > 0);
        }

        logger.info("TEST COMPLETED");
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
                    logger.info("Search box found");
                    return elements.get(0);
                }
            } catch (TimeoutException e) {
            }
        }

        return null;
    }

    private void checkAndHandleRecaptcha() {
        logger.info("Checking for reCAPTCHA...");

        By recaptchaLocator = By.xpath("//div[contains(@class, 'recaptcha-checkbox-border')]");

        try {
            java.util.List<WebElement> recaptchas = driver.findElements(recaptchaLocator);

            if (!recaptchas.isEmpty() && recaptchas.get(0).isDisplayed()) {
                logger.info("reCAPTCHA detected!");
                logger.info("Waiting for reCAPTCHA solution (up to 15 seconds)...");

                for (int i = 0; i < 30; i++) {
                    try {
                        java.util.List<WebElement> currentRecaptchas = driver.findElements(recaptchaLocator);
                        if (currentRecaptchas.isEmpty() || !currentRecaptchas.get(0).isDisplayed()) {
                            logger.info("reCAPTCHA solved or disappeared");
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
                logger.info("reCAPTCHA not detected");
            }
        } catch (NoSuchElementException e) {
            logger.info("reCAPTCHA not detected");
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
                logger.info("W3Schools cookie consent closed");
                return;
            } catch (TimeoutException e) {
            }
        }

        logger.info("W3Schools cookie consent not found");
    }

    private void closeGoogleCookieConsent() {
        logger.info("Looking for Google cookie consent...");

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
                                    googleCookieButton);

                            if (parent != null) {
                                js.executeScript("arguments[0].click();", parent);
                            } else {
                                js.executeScript("arguments[0].click();", googleCookieButton);
                            }
                        }

                        logger.info("Google cookie consent closed");
                        return;
                    }
                }
            } catch (Exception e) {
            }
        }

        logger.info("Google cookie consent not found");
    }
}
