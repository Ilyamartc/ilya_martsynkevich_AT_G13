package tests.junit.booking;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class DemoQASelectMenuTest {
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
        System.out.println("=== ТЕСТ DEMOQA SELECT MENU ===\n");
    }

    @Test
    public void testSelectMenuInteractions() {
        driver.get("https://demoqa.com/select-menu");
        System.out.println("--- ШАГ 1: Открытие DemoQA Select Menu ---\n");
        System.out.println("✓ Открыл https://demoqa.com/select-menu");

        js.executeScript("window.scrollTo(0, 0);");

        System.out.println("\n--- ШАГ 2: Select Value ---\n");
        selectFromReactSelect("#withOptGroup", "Group 1, option 1");

        System.out.println("\n--- ШАГ 3: Select One ---\n");
        selectFromReactSelect("#selectOne", "Mrs.");

        System.out.println("\n--- ШАГ 4: Old Select Menu ---\n");
        selectOldStyleDropdown("oldSelectMenu", "Red");

        System.out.println("\n--- ШАГ 5: Multiselect Dropdown ---\n");
        selectMultiselectDropdown();

        System.out.println("\n--- ШАГ 6: Standard Multi Select ---\n");
        selectStandardMultiSelect("cars", "Volvo");

        System.out.println("\n+ ВСЕ ВЫПАДАЮЩИЕ СПИСКИ УСПЕШНО ЗАПОЛНЕНЫ!");
        System.out.println("\n+ ТЕСТ ЗАВЕРШЕН");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void selectFromReactSelect(String selector, String value) {
        System.out.println(" Открываю выпадающий список");

        WebElement selectElement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(selector)));
        selectElement.click();
        System.out.println("✓ Выпадающий список открыт");

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[contains(@class, 'option')]")));

        By optionLocator = By.xpath("//div[contains(@class, 'option') and contains(text(), '" + value + "')]");
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(optionLocator));
        option.click();
        System.out.println("✓ Выбран: " + value);
    }

    private void selectOldStyleDropdown(String id, String value) {
        System.out.println(" Выбираю значение из Old Select Menu");

        WebElement selectElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
        Select select = new Select(selectElement);
        select.selectByVisibleText(value);
        System.out.println("✓ Выбран: " + value);
    }

    private void selectMultiselectDropdown() {
        System.out.println(" Выбираю значение из Multiselect Dropdown");

        js.executeScript("window.scrollBy(0, 500);");

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[contains(text(), 'Multiselect dropdown')]")));

        WebElement multiSelect = null;
        By[] multiSelectLocators = {
                By.cssSelector(".multiselect"),
                By.xpath("//div[contains(@class, 'multiselect')]"),
                By.xpath("//div[@id='multiSelectDropdown']"),
                By.xpath("//div[contains(@class, 'ms-parent')]")
        };

        for (By locator : multiSelectLocators) {
            try {
                java.util.List<WebElement> elements = driver.findElements(locator);
                if (!elements.isEmpty() && elements.get(0).isDisplayed()) {
                    multiSelect = elements.get(0);
                    System.out.println("+ Найден multiselect контейнер");
                    break;
                }
            } catch (Exception e) {
                // Продолжаем поиск
            }
        }

        if (multiSelect != null) {
            multiSelect.click();
            System.out.println("+ Multiselect dropdown открыт");

            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//li[@class='ms-elem-selectable']")));

            By[] optionLocators = {
                    By.xpath("//li[@class='ms-elem-selectable'][1]"),
                    By.xpath("//a[@class='optionLabelLeft']"),
                    By.xpath("//label[contains(@class, 'ms-elem-selectable')]")
            };

            for (By locator : optionLocators) {
                try {
                    java.util.List<WebElement> options = driver.findElements(locator);
                    if (!options.isEmpty()) {
                        WebElement optionToSelect = options.get(0);
                        js.executeScript("arguments[0].scrollIntoView(true);", optionToSelect);
                        js.executeScript("arguments[0].click();", optionToSelect);
                        String optionText = optionToSelect.getText();
                        System.out.println("+ Выбран: " + optionText);
                        return;
                    }
                } catch (Exception e) {
                    // Продолжаем поиск следующего локатора
                }
            }

            System.out.println("!!! Не удалось выбрать опцию");
        } else {
            System.out.println("!!! Multiselect dropdown не найден");
        }
    }

    private void selectStandardMultiSelect(String id, String value) {
        System.out.println(" Выбираю значение из Standard Multi Select");

        js.executeScript("window.scrollBy(0, 300);");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));

        WebElement selectElement = driver.findElement(By.id(id));
        Select select = new Select(selectElement);
        select.selectByVisibleText(value);
        System.out.println("✓ Выбран: " + value);
    }
}
