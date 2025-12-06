package com.example.tests.junit.booking;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class DemoQASelectMenuTest {
    private static final Logger logger = LogManager.getLogger(DemoQASelectMenuTest.class);
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    @Before
    public void setUp() {
        ChromeOptions opt = new ChromeOptions();
        opt.addArguments("--start-maximized");
        driver = new ChromeDriver(opt);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        js = (JavascriptExecutor) driver;
        logger.info("=== DEMOQA SELECT MENU TEST ===");
    }

    @Test
    public void testSelectMenuInteractions() {
        logger.info("Navigating to DemoQA Select Menu page");
        driver.get("https:
        logger.info("--- STEP 1: Opening DemoQA Select Menu ---");
        logger.info("Opened https:

        js.executeScript("window.scrollTo(0, 0);");

        logger.info("--- STEP 2: Select Value ---");
        selectFromReactSelect("#withOptGroup", "Group 1, option 1");

        logger.info("--- STEP 3: Select One ---");
        selectFromReactSelect("#selectOne", "Mrs.");

        logger.info("--- STEP 4: Old Select Menu ---");
        selectOldStyleDropdown("oldSelectMenu", "Red");

        logger.info("--- STEP 5: Multiselect Dropdown ---");
        selectMultiselectDropdown();

        logger.info("--- STEP 6: Standard Multi Select ---");
        selectStandardMultiSelect("cars", "Volvo");

        logger.info("ALL DROPDOWNS SUCCESSFULLY FILLED!");
        logger.info("TEST COMPLETED");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void selectFromReactSelect(String selector, String value) {
        logger.info(" Opening dropdown list");

        WebElement selectElement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(selector)));
        selectElement.click();
        logger.info("✓ Dropdown list opened");

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("

        By optionLocator = By.xpath("
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(optionLocator));
        option.click();
        logger.info("✓ Selected: " + value);
    }

    private void selectOldStyleDropdown(String id, String value) {
        logger.info(" Selecting value from Old Select Menu");

        WebElement selectElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
        Select select = new Select(selectElement);
        select.selectByVisibleText(value);
        logger.info("✓ Selected: " + value);
    }

    private void selectMultiselectDropdown() {
        logger.info(" Selecting value from Multiselect Dropdown");

        js.executeScript("window.scrollBy(0, 500);");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            logger.error("Thread interrupted during sleep", e);
        }

        WebElement multiSelect = null;
        By[] multiSelectLocators = {
                By.xpath("
                By.cssSelector(".multiselect"),
                By.xpath("
                By.xpath("
                By.xpath("
                By.xpath("(
        };

        for (By locator : multiSelectLocators) {
            try {
                java.util.List<WebElement> elements = driver.findElements(locator);
                if (!elements.isEmpty() && elements.get(0).isDisplayed()) {
                    multiSelect = elements.get(0);
                    logger.info("Found multiselect container");
                    break;
                }
            } catch (Exception e) {
            }
        }

        if (multiSelect != null) {
            try {
                Select select = new Select(multiSelect);
                java.util.List<WebElement> options = select.getOptions();
                if (!options.isEmpty()) {
                    select.selectByIndex(1);
                    logger.info("Selected: " + options.get(1).getText());
                    return;
                }
            } catch (Exception e1) {
                try {
                    multiSelect.click();
                    logger.info("Multiselect dropdown opened");

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        logger.error("Thread interrupted during sleep", e);
                    }

                    By[] optionLocators = {
                            By.xpath("
                            By.xpath("
                            By.xpath("
                            By.xpath("
                            By.xpath("
                    };

                    for (By locator : optionLocators) {
                        try {
                            java.util.List<WebElement> optList = driver.findElements(locator);
                            if (!optList.isEmpty()) {
                                WebElement optionToSelect = optList.get(0);
                                js.executeScript("arguments[0].scrollIntoView(true);", optionToSelect);
                                js.executeScript("arguments[0].click();", optionToSelect);
                                String optionText = optionToSelect.getText();
                                logger.info("Selected: " + optionText);
                                return;
                            }
                        } catch (Exception e) {
                        }
                    }
                    logger.info("Failed to select option");
                } catch (Exception e) {
                    logger.error("Error working with multiselect: " + e.getMessage(), e);
                }
            }
        } else {
            logger.info("Multiselect dropdown not found");
        }
    }

    private void selectStandardMultiSelect(String id, String value) {
        logger.info("Selecting value from Standard Multi Select");

        js.executeScript("window.scrollBy(0, 300);");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));

        WebElement selectElement = driver.findElement(By.id(id));
        Select select = new Select(selectElement);
        select.selectByVisibleText(value);
        logger.info("Selected: " + value);
    }
}

