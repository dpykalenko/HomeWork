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

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class Task4 {
    String initialUrl = "http://demo.guru99.com/test/drag_drop.html";
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
    public void checkDragAndDrop() {
        wait.until(presenceOfElementLocated(By.id("amt7")));
        WebElement From1=driver.findElement(By.id("fourth"));
        WebElement To1=driver.findElement(By.id("amt7"));
        WebElement From2=driver.findElement(By.id("fourth"));
        WebElement To2=driver.findElement(By.id("amt8"));
        WebElement From3=driver.findElement(By.id("credit2"));
        WebElement To3=driver.findElement(By.cssSelector("ol[class='field14 ui-droppable ui-sortable']"));
        WebElement From4=driver.findElement(By.id("credit1"));
        WebElement To4=driver.findElement(By.id("loan"));

        Actions act=new Actions(driver);

        act.dragAndDrop(From1, To1).build().perform();
        act.dragAndDrop(From2, To2).build().perform();
        act.dragAndDrop(From3, To3).build().perform();
        act.dragAndDrop(From4, To4).build().perform();

        if (driver.findElement(By.xpath("//a[contains(text(), 'Perfect')]")).isDisplayed()) {
            System.out.println("Done!");
        } else{
                System.out.println("Not Done!");
            }
        }
    }


