package com.example.tests.selenium.homework;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class TestSeleniumNoTry {
    public static void main(String[] args) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");

        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https:

        if (driver.findElements(By.xpath("
            driver.findElement(By.xpath("
        }

        WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
        search.sendKeys("pogoda Minsk", Keys.ENTER);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("wob_wc")));

        if (driver.findElements(By.xpath("
            driver.findElement(By.xpath("
        }

        WebElement temp = driver.findElement(By.xpath("
        System.out.println("Температура завтра в 12:00: " + temp.getAttribute("aria-label"));

        driver.quit();
    }
}

