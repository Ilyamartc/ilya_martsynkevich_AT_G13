package com.example.tests.selenium.homework;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class W3SchoolsGoogleTest {
    private static JavascriptExecutor js;

    public static void main(String[] args) throws InterruptedException {

        ChromeOptions opt = new ChromeOptions();
        opt.addArguments("--start-maximized");
        opt.addArguments("--disable-blink-features=AutomationControlled");
        opt.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        opt.setExperimentalOption("useAutomationExtension", false);

        WebDriver d = new ChromeDriver(opt);
        WebDriverWait w = new WebDriverWait(d, Duration.ofSeconds(30));
        Actions actions = new Actions(d);
        js = (JavascriptExecutor) d;

        System.out.println("=== TEST W3SCHOOLS → GOOGLE SEARCH ===\n");

        try {
            System.out.println("--- STEP 1: Opening W3Schools ---\n");
            d.get("https:
            System.out.println("✓ Opened https:

            Thread.sleep(1000);

            closeCookieConsent(d, w);

            Thread.sleep(1000);

            System.out.println("\n--- STEP 2: Selecting word Tutorial ---\n");

            By headingLocator = By.xpath("
            WebElement heading = w.until(ExpectedConditions.presenceOfElementLocated(headingLocator));

            System.out.println("✓ Heading found: " + heading.getText());

            String copiedText = "Tutorial";
            System.out.println("✓ Selected text: \"" + copiedText + "\"");

            System.out.println("\n--- STEP 3: Going to Google ---\n");

            d.get("https:
            System.out.println("✓ Opened https:

            Thread.sleep(2000);

            closeGoogleCookieConsent(d, w);

            Thread.sleep(1000);

            System.out.println("\n--- STEP 4: Insert text into search ---\n");

            WebElement searchBox = findSearchBox(d, w);

            if (searchBox == null) {
                System.err.println("❌ Google search box not found");
                d.quit();
                return;
            }

            searchBox.click();
            System.out.println("✓ Clicked on search box");

            Thread.sleep(500);

            searchBox.sendKeys(copiedText);
            System.out.println("✓ Inserted into search: \"" + copiedText + "\"");

            Thread.sleep(500);

            System.out.println("\n--- STEP 5: Search ---\n");

            searchBox.sendKeys(Keys.ENTER);
            System.out.println("✓ Enter key pressed");

            Thread.sleep(2000);

            checkAndHandleRecaptcha(d, w);

            Thread.sleep(2000);

            try {
                w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[data-sokoban-container]")));
            } catch (TimeoutException e) {
                w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("
            }

            System.out.println("✓ Search results loaded");

            System.out.println("\n--- STEP 6: Check results ---\n");

            java.util.List<WebElement> searchResults = d.findElements(By.xpath("

            if (searchResults.isEmpty()) {
                searchResults = d.findElements(By.cssSelector("div[data-sokoban-container] a"));
            }

            System.out.println("Results found: " + searchResults.size());

            if (searchResults.isEmpty()) {
                System.err.println("❌ Search results not found");
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

                System.out.println("\n--- CHECK RESULTS ---\n");
                System.out.println("+ Valid results: " + validResults + "/" + Math.min(searchResults.size(), 10));
                System.out.println("- Invalid results: " + invalidResults + "/" + Math.min(searchResults.size(), 10));

                if (invalidResults == 0 && validResults > 0) {
                    System.out.println("\n+ TEST PASSED: All results contain the searched word \"" + copiedText + "\"");
                } else if (validResults > 0) {
                    System.out.println("\n!!! TEST PARTIALLY PASSED: " + validResults + " results contain the word \"" + copiedText + "\"");
                } else {
                    System.err.println("\n- TEST FAILED: Results do not contain the searched word");
                }
            }

            System.out.println("\n+ TEST COMPLETED");

        } catch (Exception e) {
            System.err.println("- ERROR: " + e.getMessage());
            e.printStackTrace();
        } finally {
            d.quit();
        }
    }

    private static WebElement findSearchBox(WebDriver d, WebDriverWait w) {
        By[] searchBoxLocators = {
                By.cssSelector("textarea[name='q']"),
                By.xpath("
                By.id("APjFqb"),
                By.cssSelector("textarea[role='combobox']"),
                By.cssSelector("input[name='q']"),
                By.xpath("
        };

        for (By locator : searchBoxLocators) {
            try {
                java.util.List<WebElement> elements = d.findElements(locator);
                if (!elements.isEmpty() && elements.get(0).isDisplayed()) {
                    System.out.println("✓ Search box found");
                    return elements.get(0);
                }
            } catch (TimeoutException e) {
            }
        }

        return null;
    }

    private static void checkAndHandleRecaptcha(WebDriver d, WebDriverWait w) throws InterruptedException {
        System.out.println(" Checking for reCAPTCHA...");

        By recaptchaLocator = By.xpath("

        try {
            java.util.List<WebElement> recaptchas = d.findElements(recaptchaLocator);

            if (!recaptchas.isEmpty() && recaptchas.get(0).isDisplayed()) {
                System.out.println("!!! reCAPTCHA detected!");
                System.out.println(" Waiting for reCAPTCHA solution (up to 15 seconds)...");

                for (int i = 0; i < 30; i++) {
                    try {
                        java.util.List<WebElement> currentRecaptchas = d.findElements(recaptchaLocator);
                        if (currentRecaptchas.isEmpty() || !currentRecaptchas.get(0).isDisplayed()) {
                            System.out.println("✓ reCAPTCHA solved or disappeared");
                            break;
                        }
                    } catch (Exception ex) {
                        break;
                    }
                    Thread.sleep(500);
                }
            } else {
                System.out.println("✓ reCAPTCHA not detected");
            }
        } catch (NoSuchElementException e) {
            System.out.println("✓ reCAPTCHA not detected");
        }
    }

    private static void closeCookieConsent(WebDriver d, WebDriverWait w) throws InterruptedException {
        By[] cookieLocators = {
                By.id("accept-choices"),
                By.xpath("
                By.xpath("
                By.xpath("
                By.xpath("
                By.xpath("
        };

        for (By locator : cookieLocators) {
            try {
                WebElement cookieButton = w.until(ExpectedConditions.elementToBeClickable(locator));
                cookieButton.click();
                System.out.println("✓ W3Schools cookie consent closed");
                return;
            } catch (TimeoutException e) {
            }
        }

        System.out.println("⚠ W3Schools cookie consent not found");
    }

    private static void closeGoogleCookieConsent(WebDriver d, WebDriverWait w) throws InterruptedException {
        System.out.println(" Looking for Google cookie consent...");

        By[] googleCookieLocators = {
                By.xpath("
                By.xpath("
                By.xpath("
                By.xpath("
                By.xpath("
                By.xpath("
                By.xpath("
                By.xpath("
        };

        for (int i = 0; i < googleCookieLocators.length; i++) {
            try {
                By locator = googleCookieLocators[i];
                java.util.List<WebElement> elements = d.findElements(locator);

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

                        System.out.println("✓ Google cookie consent closed");
                        Thread.sleep(500);
                        return;
                    }
                }
            } catch (Exception e) {
            }
        }

        System.out.println("⚠ Google cookie consent not found");
    }
}
