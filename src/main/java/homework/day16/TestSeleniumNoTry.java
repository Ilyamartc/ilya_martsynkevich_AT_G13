package homework.day16;

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

        driver.get("https://www.google.com/");

        if (driver.findElements(By.xpath("//button[contains(.,'Zaakceptuj wszystko')]")).size() > 0) {
            driver.findElement(By.xpath("//button[contains(.,'Zaakceptuj wszystko')]")).click();
        }

        WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
        search.sendKeys("pogoda Minsk", Keys.ENTER);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("wob_wc")));

        if (driver.findElements(By.xpath("//div[contains(text(),'Jutro')]")).size() > 0) {
            driver.findElement(By.xpath("//div[contains(text(),'Jutro')]")).click();
        }

        WebElement temp = driver.findElement(By.xpath("//*[contains(@aria-label,'12:00') or contains(text(),'12:00')]"));
        System.out.println("Температура завтра в 12:00: " + temp.getAttribute("aria-label"));

        driver.quit();
    }
}
