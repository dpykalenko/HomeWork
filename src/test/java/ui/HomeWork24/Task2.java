package ui.HomeWork24;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.testng.Assert.*;

public class Task2 {
    String initialUrl = "http://demo.guru99.com/Agile_Project/Agi_V1/index.php";
    WebDriver driver;
    WebDriverWait wait;
    String login = "1303";
    String password = "Guru99";


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
    public void testHandleCookies() {
        driver.findElement(By.name("uid")).sendKeys(login);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("btnLogin")).click();
        wait.until(presenceOfElementLocated(By.cssSelector("a[href='Logout.php']")));

        Set<Cookie> cookieSet = driver.manage().getCookies();
        cookieSet.forEach(System.out::println);

        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        String actualText = driver.findElement(By.cssSelector("a[href='Logout.php']")).getText();
        assertEquals(actualText, "Log out");
    }
}
