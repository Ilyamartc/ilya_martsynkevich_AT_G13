package com.example.tests.selenium.homework;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class DemoQASelectMenuTest {
    public static void main(String[] args) throws InterruptedException {

        ChromeOptions opt = new ChromeOptions();
        opt.addArguments("--start-maximized");

        WebDriver d = new ChromeDriver(opt);
        WebDriverWait w = new WebDriverWait(d, Duration.ofSeconds(30));
        JavascriptExecutor js = (JavascriptExecutor) d;

        System.out.println("=== TEST DEMOQA SELECT MENU ===\n");

        try {
            System.out.println("--- STEP 1: Opening DemoQA Select Menu ---\n");
            d.get("https:
            System.out.println("✓ Opened https:

            Thread.sleep(2000);

            js.executeScript("window.scrollTo(0, 0);");
            Thread.sleep(500);

            System.out.println("\n--- STEP 2: Select Value ---\n");
            selectFromReactSelect(d, w, js, "#withOptGroup", "Group 1, option 1");

            Thread.sleep(500);

            System.out.println("\n--- STEP 3: Select One ---\n");
            selectFromReactSelect(d, w, js, "#selectOne", "Mrs.");

            Thread.sleep(500);

            System.out.println("\n--- STEP 4: Old Select Menu ---\n");
            selectOldStyleDropdown(d, w, "oldSelectMenu", "Red");

            Thread.sleep(500);

            System.out.println("\n--- STEP 5: Multiselect Dropdown ---\n");
            selectMultiselectDropdown(d, w, js);

            Thread.sleep(500);

            System.out.println("\n--- STEP 6: Standard Multi Select ---\n");
            selectStandardMultiSelect(d, w, "cars", "Volvo");

            Thread.sleep(1000);

            System.out.println("\n+ ALL DROPDOWN LISTS SUCCESSFULLY FILLED!");
            System.out.println("\n+ TEST COMPLETED");

        } catch (Exception e) {
            System.err.println("- ERROR: " + e.getMessage());
            e.printStackTrace();
        } finally {
            Thread.sleep(2000);
            d.quit();
        }
    }

    private static void selectFromReactSelect(WebDriver d, WebDriverWait w, JavascriptExecutor js, String selector, String value) throws InterruptedException {
        System.out.println(" Opening dropdown list");

        WebElement selectElement = w.until(ExpectedConditions.elementToBeClickable(By.cssSelector(selector)));
        selectElement.click();
        System.out.println("✓ Dropdown list opened");

        Thread.sleep(500);

        By optionLocator = By.xpath("
        WebElement option = w.until(ExpectedConditions.elementToBeClickable(optionLocator));
        option.click();
        System.out.println("✓ Selected: " + value);

        Thread.sleep(300);
    }

    private static void selectOldStyleDropdown(WebDriver d, WebDriverWait w, String id, String value) throws InterruptedException {
        System.out.println(" Selecting value from Old Select Menu");

        WebElement selectElement = w.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));

        Select select = new Select(selectElement);

        select.selectByVisibleText(value);
        System.out.println("✓ Selected: " + value);

        Thread.sleep(300);
    }

    private static void selectMultiselectDropdown(WebDriver d, WebDriverWait w, JavascriptExecutor js) throws InterruptedException {
        System.out.println(" Selecting value from Multiselect Dropdown");

        js.executeScript("window.scrollBy(0, 500);");
        Thread.sleep(500);

        WebElement multiSelect = null;
        By[] multiSelectLocators = {
                By.cssSelector(".multiselect"),
                By.xpath("
                By.xpath("
                By.xpath("
        };

        for (By locator : multiSelectLocators) {
            try {
                java.util.List<WebElement> elements = d.findElements(locator);
                if (!elements.isEmpty() && elements.get(0).isDisplayed()) {
                    multiSelect = elements.get(0);
                    break;
                }
            } catch (Exception e) {
            }
        }

        if (multiSelect != null) {
            multiSelect.click();
            System.out.println("+ Multiselect dropdown opened");

            Thread.sleep(500);

            By[] optionLocators = {
                    By.xpath("
                    By.xpath("
                    By.xpath("
            };

            for (By locator : optionLocators) {
                try {
                    java.util.List<WebElement> options = d.findElements(locator);
                    if (!options.isEmpty()) {
                        options.get(0).click();
                        String optionText = options.get(0).getText();
                        System.out.println("+ Selected: " + optionText);
                        Thread.sleep(300);
                        return;
                    }
                } catch (Exception e) {
                }
            }

            System.out.println("!!! Failed to select option");
        } else {
            System.out.println("!!! Multiselect dropdown not found");
        }
    }

    private static void selectStandardMultiSelect(WebDriver d, WebDriverWait w, String id, String value) throws InterruptedException {
        System.out.println(" Selecting value from Standard Multi Select");

        JavascriptExecutor js = (JavascriptExecutor) d;
        js.executeScript("window.scrollBy(0, 300);");
        Thread.sleep(500);

        WebElement selectElement = w.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));

        Select select = new Select(selectElement);

        select.selectByVisibleText(value);
        System.out.println("✓ Selected: " + value);

        Thread.sleep(300);
    }
}
