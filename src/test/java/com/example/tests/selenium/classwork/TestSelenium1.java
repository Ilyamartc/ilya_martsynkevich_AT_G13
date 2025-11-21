package com.example.tests.selenium.classwork;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class TestSelenium1 {
    public static void main(String[] args) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) "
                + "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/142.0.0.0 Safari/537.36");
        options.addArguments("--start-maximized");

        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.get("https://www.google.com/?hl=pl");

            try {
                WebElement accept = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(.,'Zaakceptuj wszystko')]")));
                accept.click();
            } catch (Exception e) {
                System.out.println("Cookies окно не появилось");
            }

            WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
            search.sendKeys("pogoda Minsk");
            search.sendKeys(Keys.ENTER);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("wob_wc")));

            try {
                WebElement tomorrow = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[contains(text(),'Jutro')]")));
                tomorrow.click();
            } catch (TimeoutException e) {
                System.out.println("Не найден элемент 'Jutro' — Google показывает погоду иначе");
            }

            WebElement tempAtNoon = null;
            try {
                tempAtNoon = driver.findElement(By.xpath("//*[contains(@aria-label,'12:00') or contains(text(),'12:00')]"));
                String label = tempAtNoon.getAttribute("aria-label");
                System.out.println("Температура завтра в 12:00: " + label);
            } catch (NoSuchElementException e) {
                System.out.println("Температура в 12:00 не найдена ни в aria-label, ни в тексте");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
