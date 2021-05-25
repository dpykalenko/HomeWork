package ui.HomeWork23;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.testng.Assert.assertTrue;

public class Task2 {
    String initialUrl = "https://rozetka.com.ua/";
    WebDriver driver;
    WebDriverWait wait;
    By mobileDeviceButton = By.xpath("//a[@href='https://rozetka.com.ua/mobile-phones/c80003/producer=samsung/']");
    By uploadingElements = By.xpath("//app-goods-tile-default/div");

    @BeforeMethod
    public void navigateAction() {
        driver.get(initialUrl);
    }

    @BeforeClass
    public void setupBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void searchByBrand() {
        driver.findElement(By.xpath("//input[@name='search']")).sendKeys("samsung" + Keys.ENTER);
        wait.until(presenceOfElementLocated(uploadingElements));

        driver.findElement(mobileDeviceButton).click();
        wait.until(presenceOfElementLocated(uploadingElements));
        driver.findElement(By.xpath("//input[@class='custom-checkbox' and @type='checkbox' and @id='Apple']//following::label")).click();
        wait.until(presenceOfElementLocated(uploadingElements));
        driver.findElement(By.xpath("//input[@class='custom-checkbox' and @type='checkbox' and @id='Huawei']//following::label")).click();
        wait.until(presenceOfElementLocated(uploadingElements));
        List<String> names = driver.findElements(By.xpath("//span[@class='goods-tile__title']"))
                .stream().map(WebElement::getText).collect(Collectors.toList());
        for (String name : names) {
            Boolean isContainName = name.contains("Apple") || name.contains("Samsung") || name.contains("Huawei");
            assertTrue(isContainName);
        }
    }

    @Test
    public void searchByPrice() {
        driver.findElement(By.xpath("//input[@name='search']")).sendKeys("samsung" + Keys.ENTER);
        wait.until(presenceOfElementLocated(uploadingElements));

        driver.findElement(mobileDeviceButton).click();
        wait.until(presenceOfElementLocated(uploadingElements));

        scrollToElement(driver.findElement(By.xpath("//input[@class='custom-checkbox' and @type='checkbox' and @id='Apple']//following::label")));
        driver.findElement(By.xpath("//input[@formcontrolname='min']")).clear();
        driver.findElement(By.xpath("//input[@formcontrolname='min']")).sendKeys("1500");
        wait.until(presenceOfElementLocated(uploadingElements));
        driver.findElement(By.xpath("//input[@formcontrolname='max']")).clear();
        driver.findElement(By.xpath("//input[@formcontrolname='max']")).sendKeys("15000" + Keys.ENTER);
        wait.until(presenceOfElementLocated(uploadingElements));


        List<Integer> prices = driver.findElements(By.xpath("//span[@class='goods-tile__price-value']"))
                .stream()
                .map(webElement -> webElement.getText().replaceAll("\\s", ""))
                .map(Integer::valueOf).collect(Collectors.toList());
        for (int price : prices) {
            Boolean isConditionMet = price > 1500 & price < 15000;
            assertTrue(isConditionMet);
        }
    }
    @Test
    public void searchByRAM() {
        driver.findElement(By.xpath("//input[@name='search']")).sendKeys("samsung" + Keys.ENTER);
        wait.until(presenceOfElementLocated(uploadingElements));

        driver.findElement(mobileDeviceButton).click();
        wait.until(presenceOfElementLocated(uploadingElements));

        scrollToElement(driver.findElement(By.xpath("//*[text()=' Встроенная память ']")));


        driver.findElement(By.xpath("//label[@for='128 ГБ']//following::label")).click();
        wait.until(presenceOfElementLocated(uploadingElements));

        List<String> ram = driver.findElements(By.xpath("//span[@class='goods-tile__title']"))
                .stream().map(WebElement::getText).collect(Collectors.toList());
        for (String name : ram) {
            Boolean isContainRam = name.contains("256GB");
            assertTrue(isContainRam);
        }

    }
        private void scrollToElement (WebElement elem){
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elem);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }




