package ui.HomeWork24;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.awt.*;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class Task1 {
    String initialUrl = "https://login.yahoo.com/";
    WebDriver driver;
    WebDriverWait wait;

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
    public void test() throws AWTException {
        By usernameField = By.id("login-username");
        By passwordField = By.id("login-passwd");
        By toField = By.id("message-to-field");
        String email = "diankavanka@yahoo.com";
        String password = "0441324428";
        By mailButton = By.id("ybar-navigation-item-mail");
        By createButton = By.cssSelector("a[data-test-id='compose-button']");
        By subjectField = By.cssSelector("input[data-test-id='compose-subject']");
        By textField = By.cssSelector("div[data-test-id='rte']");
        By sendButton = By.cssSelector("button[data-test-id='compose-send-button']");
        By emailComes = By.cssSelector("span[data-test-id='displayed-count']");
        String subject = "Home Work";
        String text = "Some Text";
        String nameFile = "2020-11-18 12.11.27";

        wait.until(presenceOfElementLocated(usernameField));
        driver.findElement(usernameField).click();
        driver.findElement(usernameField).sendKeys(email + Keys.ENTER);

        wait.until(presenceOfElementLocated(passwordField));
        driver.findElement(passwordField).click();
        driver.findElement(passwordField).sendKeys(password + Keys.ENTER);

        wait.until(presenceOfElementLocated(mailButton));
        driver.findElement(mailButton).click();

        wait.until(presenceOfElementLocated(createButton));
        driver.findElement(createButton).click();

        wait.until(presenceOfElementLocated(toField));
        driver.findElement(toField).click();
        driver.findElement(toField).sendKeys(email + Keys.ENTER);
        driver.findElement(subjectField).click();
        driver.findElement(subjectField).sendKeys(subject + Keys.ENTER);
        driver.findElement(textField).click();
        driver.findElement(textField).sendKeys(text + Keys.ENTER);

        WebElement addFile = driver.findElement(By.xpath(".//input[@type='file']"));
        addFile.sendKeys("/Users/Diana/Documents/animals/2020-11-18 12.11.27.jpg");
        wait.until(presenceOfElementLocated(By.cssSelector("div[data-test-id='attachment-container']")));
        driver.findElement(sendButton).click();

        wait.until(presenceOfElementLocated(emailComes));

        WebElement explicitWait = (new WebDriverWait(driver, 50))
                .until(ExpectedConditions.presenceOfElementLocated(emailComes));

        try {
            Thread.sleep(180000);
        }catch (InterruptedException e){
                e.printStackTrace();
            }

        driver.navigate().refresh();
        assertEquals(driver.findElement(By.cssSelector("span[title='Home Work']")).getText(), subject);
        driver.navigate().refresh();

        driver.findElement(By.xpath("//li[@role='list-item'][2]")).click();
        wait.until(presenceOfElementLocated(By.cssSelector("img[data-test-id='attachment-thumbnail']")));

        assertEquals(driver.findElement(By.cssSelector("span[data-test-id='message-group-subject-text']")).getText(),subject);
        assertEquals(driver.findElement(By.cssSelector("div[data-test-id='message-view-body-content']")).getText(),text);
        assertEquals(driver.findElement(By.cssSelector("span[title='2020-11-18 12.11.27.jpg']>div>span>span")).getText(),nameFile);

    }
    }

