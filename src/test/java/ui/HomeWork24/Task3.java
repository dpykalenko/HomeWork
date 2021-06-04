package ui.HomeWork24;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;
import static org.testng.Assert.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class Task3 {
    String initialUrl = "http://demo.guru99.com/test/guru99home/";
    WebDriver driver;
    WebDriverWait wait;
    WebElement element;

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
    public void checkIframes() {
        By iframe1 = By.xpath("(//iframe)[1]");
        By playButton = By.cssSelector("button[class='ytp-large-play-button ytp-button']");
        By pauseButton = By.cssSelector("button[title='Pause (k)']");

        element = driver.findElement(iframe1);
        driver.switchTo().frame(element);
        wait.until(presenceOfElementLocated(playButton));
        WebElement playButtonElement = driver.findElement(playButton);
        playButtonElement.click();

        wait.until(presenceOfElementLocated(pauseButton));
        element = driver.findElement(pauseButton);
        mouseMove(driver, element);

        driver.switchTo().parentFrame();
        element = driver.findElement(By.cssSelector("img[src='images/logo_1.png']"));
        mouseMove(driver, element);

        element = driver.findElement(iframe1);
        driver.switchTo().frame(element);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        assertTrue(driver.findElement(By.xpath("//button[@aria-label='Pause (k)']")).isEnabled());
    }

    private void mouseMove(WebDriver driver, WebElement element) {
        Actions action1 = new Actions((driver));
        action1.moveToElement(element).build().perform();
    }
}
