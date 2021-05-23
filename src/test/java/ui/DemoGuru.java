package ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.testng.Assert.assertEquals;


public class DemoGuru {
    String initialUrl = "http://demo.guru99.com/Agile_Project/Agi_V1/index.php";
    WebDriver driver;
    WebDriverWait wait;
    String validUserName = "1303";
    String validPassword = "Guru99";
    String incorrectCredsMessage = "User or Password is not valid";

    @BeforeMethod
    public void navigateAction(){
        driver.get(initialUrl);
    }

    @BeforeClass
    public void setupBrowser(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }

    @Test
    public void testPositiveLogin() {
        driver.findElement(By.name("uid")).sendKeys(validUserName);
        driver.findElement(By.name("password")).sendKeys(validPassword);
        driver.findElement(By.name("btnLogin")).click();

        WebElement logoutLink = wait.until(presenceOfElementLocated(By.linkText("Log out")));
        logoutLink.click();

        driver.switchTo().alert().accept();
        String resultUrl = driver.getCurrentUrl();
        assertEquals(resultUrl, initialUrl);
    }
    @Test
    public void testInvalidLogin() {
        driver.findElement(By.name("uid")).sendKeys("invalid");
        driver.findElement(By.name("password")).sendKeys(validPassword);
        driver.findElement(By.name("btnLogin")).click();

        assertEquals(driver.switchTo().alert().getText(), incorrectCredsMessage);
        driver.switchTo().alert().accept();

        String resultUrl = driver.getCurrentUrl();
        assertEquals(resultUrl, initialUrl);
    }
    @Test
    public void testInvalidPassword() {
        driver.findElement(By.name("uid")).sendKeys(validUserName);
        driver.findElement(By.name("password")).sendKeys("invalid");
        driver.findElement(By.name("btnLogin")).click();

        assertEquals(driver.switchTo().alert().getText(), incorrectCredsMessage);
        driver.switchTo().alert().accept();

        String resultUrl = driver.getCurrentUrl();
        assertEquals(resultUrl, initialUrl);
    }
    @Test
    public void testWithoutPassword() {
        driver.findElement(By.name("uid")).sendKeys(validUserName);
        driver.findElement(By.name("btnLogin")).click();

        assertEquals(driver.switchTo().alert().getText(), incorrectCredsMessage);
        driver.switchTo().alert().accept();

        String resultUrl = driver.getCurrentUrl();
        assertEquals(resultUrl, initialUrl);
    }
    @Test
    public void testWithoutUserName() {
        driver.findElement(By.name("password")).sendKeys(validPassword);
        driver.findElement(By.name("btnLogin")).click();

        assertEquals(driver.switchTo().alert().getText(), incorrectCredsMessage);
        driver.switchTo().alert().accept();

        String resultUrl = driver.getCurrentUrl();
        assertEquals(resultUrl, initialUrl);
    }
    @Test
    public void testWithoutPasswordAndUserName() {
        driver.findElement(By.name("btnLogin")).click();

        assertEquals(driver.switchTo().alert().getText(), incorrectCredsMessage);
        driver.switchTo().alert().accept();

        String resultUrl = driver.getCurrentUrl();
        assertEquals(resultUrl, initialUrl);
    }
    @Test
    public void testLoginAfterClickOnResetButton() {
        driver.findElement(By.name("uid")).sendKeys(validUserName);
        driver.findElement(By.name("password")).sendKeys(validPassword);
        driver.findElement(By.name("btnReset")).click();
        driver.findElement(By.name("btnLogin")).click();

        assertEquals(driver.switchTo().alert().getText(), incorrectCredsMessage);
        driver.switchTo().alert().accept();

        String resultUrl = driver.getCurrentUrl();
        assertEquals(resultUrl, initialUrl);
    }
}
