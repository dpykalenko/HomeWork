package ui.HomeWork23;

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

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class Task1 {
    String initialUrl = "https://rozetka.com.ua/";
    WebDriver driver;
    WebDriverWait wait;

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
    public void test() {
        By monitorXpath = By.xpath("//a[@class='menu__link'][@href='https://hard.rozetka.com.ua/monitors/c80089/']");
        Actions builder = new Actions(driver);
        WebElement laptopsLink = driver.findElement(By.xpath("//ul[@class='menu-categories menu-categories_type_main']/li[1]"));
        builder.moveToElement(laptopsLink).perform();
        wait.until(presenceOfElementLocated(monitorXpath));
        builder.moveToElement(driver.findElement(monitorXpath)).click().perform();
        wait.until(presenceOfElementLocated(By.xpath("//div[@class='goods-tile__inner']")));
        List<WebElement> resultTiles = driver.findElements(By.xpath("//div[@class='goods-tile__inner']"));
        int i = 1;
        for (WebElement tile : resultTiles) {
            WebElement span = tile.findElement(By.xpath("(//span[@class='goods-tile__price-value'])[" + i + "]"));
            String priceText = span.getText().replaceAll("\\s", "");
            int price = Integer.parseInt(priceText);
            if (price < 4000) {
                WebElement image = tile.findElement(By.xpath("(//div[@class='goods-tile__inner'])[" + i + "]/a[@class='goods-tile__picture ng-star-inserted']/img[1]"));
                image.click();
                break;
            }
            i++;

            driver.findElement(By.xpath("//a[@href='https://rozetka.com.ua/mobile-phones/c80003/producer=samsung/']")).click();
            driver.findElement(By.xpath("//a[@href='#icon-compare']")).click();
        }
    }

}
