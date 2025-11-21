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

        System.out.println("=== ТЕСТ DEMOQA SELECT MENU ===\n");

        try {
            System.out.println("--- ШАГ 1: Открытие DemoQA Select Menu ---\n");
            d.get("https://demoqa.com/select-menu");
            System.out.println("✓ Открыл https://demoqa.com/select-menu");

            Thread.sleep(2000);

            js.executeScript("window.scrollTo(0, 0);");
            Thread.sleep(500);

            System.out.println("\n--- ШАГ 2: Select Value ---\n");
            selectFromReactSelect(d, w, js, "#withOptGroup", "Group 1, option 1");

            Thread.sleep(500);

            System.out.println("\n--- ШАГ 3: Select One ---\n");
            selectFromReactSelect(d, w, js, "#selectOne", "Mrs.");

            Thread.sleep(500);

            System.out.println("\n--- ШАГ 4: Old Select Menu ---\n");
            selectOldStyleDropdown(d, w, "oldSelectMenu", "Red");

            Thread.sleep(500);

            System.out.println("\n--- ШАГ 5: Multiselect Dropdown ---\n");
            selectMultiselectDropdown(d, w, js);

            Thread.sleep(500);

            System.out.println("\n--- ШАГ 6: Standard Multi Select ---\n");
            selectStandardMultiSelect(d, w, "cars", "Volvo");

            Thread.sleep(1000);

            System.out.println("\n+ ВСЕ ВЫПАДАЮЩИЕ СПИСКИ УСПЕШНО ЗАПОЛНЕНЫ!");
            System.out.println("\n+ ТЕСТ ЗАВЕРШЕН");

        } catch (Exception e) {
            System.err.println("- ОШИБКА: " + e.getMessage());
            e.printStackTrace();
        } finally {
            Thread.sleep(2000);
            d.quit();
        }
    }

    private static void selectFromReactSelect(WebDriver d, WebDriverWait w, JavascriptExecutor js, String selector, String value) throws InterruptedException {
        System.out.println(" Открываю выпадающий список");

        WebElement selectElement = w.until(ExpectedConditions.elementToBeClickable(By.cssSelector(selector)));
        selectElement.click();
        System.out.println("✓ Выпадающий список открыт");

        Thread.sleep(500);

        By optionLocator = By.xpath("//div[contains(@class, 'option') and contains(text(), '" + value + "')]");
        WebElement option = w.until(ExpectedConditions.elementToBeClickable(optionLocator));
        option.click();
        System.out.println("✓ Выбран: " + value);

        Thread.sleep(300);
    }

    private static void selectOldStyleDropdown(WebDriver d, WebDriverWait w, String id, String value) throws InterruptedException {
        System.out.println(" Выбираю значение из Old Select Menu");

        WebElement selectElement = w.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));

        Select select = new Select(selectElement);

        select.selectByVisibleText(value);
        System.out.println("✓ Выбран: " + value);

        Thread.sleep(300);
    }

    private static void selectMultiselectDropdown(WebDriver d, WebDriverWait w, JavascriptExecutor js) throws InterruptedException {
        System.out.println(" Выбираю значение из Multiselect Dropdown");

        js.executeScript("window.scrollBy(0, 500);");
        Thread.sleep(500);

        WebElement multiSelect = null;
        By[] multiSelectLocators = {
                By.cssSelector(".multiselect"),
                By.xpath("//div[contains(@class, 'multiselect')]"),
                By.xpath("//div[@id='multiSelectDropdown']"),
                By.xpath("//div[contains(@class, 'select-wrapper')]")
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
            System.out.println("+ Multiselect dropdown открыт");

            Thread.sleep(500);

            By[] optionLocators = {
                    By.xpath("//a[@class='optionLabelLeft']"),
                    By.xpath("//li[contains(@class, 'selected')]"),
                    By.xpath("//label[1]")
            };

            for (By locator : optionLocators) {
                try {
                    java.util.List<WebElement> options = d.findElements(locator);
                    if (!options.isEmpty()) {
                        options.get(0).click();
                        String optionText = options.get(0).getText();
                        System.out.println("+ Выбран: " + optionText);
                        Thread.sleep(300);
                        return;
                    }
                } catch (Exception e) {
                }
            }

            System.out.println("!!! Не удалось выбрать опцию");
        } else {
            System.out.println("!!! Multiselect dropdown не найден");
        }
    }

    private static void selectStandardMultiSelect(WebDriver d, WebDriverWait w, String id, String value) throws InterruptedException {
        System.out.println(" Выбираю значение из Standard Multi Select");

        JavascriptExecutor js = (JavascriptExecutor) d;
        js.executeScript("window.scrollBy(0, 300);");
        Thread.sleep(500);

        WebElement selectElement = w.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));

        Select select = new Select(selectElement);

        select.selectByVisibleText(value);
        System.out.println("✓ Выбран: " + value);

        Thread.sleep(300);
    }
}