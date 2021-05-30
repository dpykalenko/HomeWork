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
import static org.testng.AssertJUnit.assertEquals;

public class Task1 {
    String initialUrl = "https://rozetka.com.ua/";
    By uploadedElements = By.xpath("//div[@class='goods-tile__inner']");
    WebDriver driver;
    WebDriverWait wait;
    final int maxPrice = 4000;

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

        FilterMonitorsAndSelect(maxPrice);
        wait.until(presenceOfElementLocated(By.xpath("//div[@class='product-about__right']")));
        driver.findElement(By.xpath("//*[@class='compare-button ng-star-inserted']")).click();
        wait.until(presenceOfElementLocated(By.xpath("//rz-icon-counter//span")));
        assertEquals(driver.findElement(By.xpath("//rz-icon-counter//span")).getText(), "1");

        int priceOfFirstMonitor1 = getMonitorPriceOnSelectionPage();
        String nameOfFirstMonitor1 = getMonitorName();

        driver.navigate().back();

        FilterMonitorsAndSelect(priceOfFirstMonitor1);
        driver.findElement(By.xpath("//*[@class='compare-button ng-star-inserted']")).click();
        wait.until(presenceOfElementLocated(By.xpath("//rz-icon-counter//span")));
        assertEquals(driver.findElement(By.xpath("//rz-icon-counter//span")).getText(), "2");

        int priceOfFirstMonitor2 = getMonitorPriceOnSelectionPage();
        String nameOfFirstMonitor2 = getMonitorName();

        driver.findElement(By.xpath("//rz-comparison")).click();
        wait.until(presenceOfElementLocated(By.xpath("//a[@class='comparison-modal__link']")));
        driver.findElement(By.xpath("//a[@class='comparison-modal__link']")).click();

        List<WebElement> compareBlocks = driver.findElements(By.xpath("//li[@class='products-grid__cell ng-star-inserted']"));
        wait.until(presenceOfElementLocated(By.xpath("//ul[@class='products-grid']")));
        assertEquals(compareBlocks.size(), 2);

        WebElement monitorElement1 = driver.findElement(By.xpath("(//a[@class='product__heading'])[2]"));
        String actualNameOfMonitor1 = monitorElement1.getText();
        assertEquals(actualNameOfMonitor1, nameOfFirstMonitor1);

        WebElement monitorElement2 = driver.findElement(By.xpath("(//a[@class='product__heading'])[1]"));
        String actualNameOfMonitor2 = monitorElement2.getText();
        assertEquals(actualNameOfMonitor2, nameOfFirstMonitor2);

        int actualpriceOfMonitor1 = getMonitorPriceOnComparePage(2);
        assertEquals(actualpriceOfMonitor1, priceOfFirstMonitor1);

        int actualpriceOfMonitor2 = getMonitorPriceOnComparePage(1);
        assertEquals(actualpriceOfMonitor2, priceOfFirstMonitor2);

    }

    public int getMonitorPriceOnSelectionPage(){
        WebElement priceElement = driver.findElement(By.xpath("//*[@class='product-prices__big product-prices__big_color_red']"));
        String priceText = priceElement.getText();
        String priceTextClear = priceText
                .replaceAll("\\s", "")
                .replace("₴","");
        return Integer.parseInt(priceTextClear);
    }

    public int getMonitorPriceOnComparePage(int monitorOrder){
        String priceText = driver.findElement(By.xpath("(//div[@class='product__price product__price--red ng-star-inserted'])[" + monitorOrder + "]")).getText();
        boolean hasNewPrice = priceText.contains("\n");
        if (hasNewPrice){
            priceText = priceText.split("\n")[1];
        }
        String priceTextClear = priceText
                .replaceAll("\\s", "")
                .replace("₴","");
        return Integer.parseInt(priceTextClear);
    }

    public String getMonitorName(){
        return driver.findElement(By.xpath("//*[@class='product__title']")).getText();
    }

    public void FilterMonitorsAndSelect(int priceLimit){
        wait.until(presenceOfElementLocated(uploadedElements));
        List<WebElement> resultTiles = driver.findElements(By.xpath("//div[@class='goods-tile__inner']"));
        int i = 1;
        for (WebElement tile : resultTiles) {
            WebElement span = tile.findElement(By.xpath("(//span[@class='goods-tile__price-value'])[" + i + "]"));
            String priceText = span.getText().replaceAll("\\s", "");
            int price = Integer.parseInt(priceText);
            if (price < priceLimit) {
                WebElement image = tile.findElement(By.xpath("//li[@class='catalog-grid__cell catalog-grid__cell_type_slim ng-star-inserted'][" + i + "]//span[@class='goods-tile__title']"));
                image.click();
                break;
            }
            i++;
        }
    }
}
