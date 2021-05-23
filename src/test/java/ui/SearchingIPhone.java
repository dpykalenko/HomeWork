package ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sun.lwawt.macosx.CSystemTray;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.testng.Assert.assertTrue;

public class SearchingIPhone {
    WebDriver driver;
    WebDriverWait wait;

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

    @BeforeMethod
    public void navigateAction() {
        driver.get("https://google.com/ncr");
    }

    @Test
    public void testSearchIphoneByEnterClick() {
        driver.findElement(By.name("q")).sendKeys("iphone kyiv buy" + Keys.ENTER);

        boolean isFound = false;
        Integer page = 1;
        while (page <= 5){
            wait.until(presenceOfElementLocated(By.cssSelector("#result-stats")));
            List<WebElement> links = driver.findElements(By.tagName("cite"));
            if (links.stream().anyMatch(webElement -> webElement.getText().contains("stylus.ua"))) {
                isFound = true;
                break;
            }
            else  {
                driver.findElement(By.xpath("//span[@style='display:block;margin-left:53px']")).click();
            }

            page++;
        }

        if (isFound) {
            System.out.println("STYLUS.UA found on "+ page + " page");
        }
        else {
            System.out.println("STYLUS.UA not found on first 5 pages");
        }

        assertTrue(isFound);
    }
}
