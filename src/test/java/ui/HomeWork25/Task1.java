package ui.HomeWork25;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pagesTask1.HomePage;
import pagesTask1.ProductListPage;
import pagesTask1.ProductPage;
import pagesTask1.СomparisonPage;

import static org.testng.AssertJUnit.assertEquals;

public class Task1 {
    String initialUrl = "https://rozetka.com.ua/";
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
        ProductListPage productListPage = new ProductListPage(driver, wait);
        HomePage homePage = new HomePage(driver, wait);
        ProductPage productPage = new ProductPage(driver, wait);
        СomparisonPage comparisonPage = new СomparisonPage(driver, wait);

        homePage.openMonitorsListPage();
        productListPage.filterMonitorsAndSelect(maxPrice);
        productPage.addToCompare();
        assertEquals(productPage.getCounterIconText(), "1");

        int priceOfFirstMonitor1 = productPage.getMonitorPriceOnSelectionPage();
        String nameOfFirstMonitor1 = productPage.getMonitorName();

        productPage.navigateBack();

        productListPage.filterMonitorsAndSelect(priceOfFirstMonitor1);
        productPage.addToCompare();
        assertEquals(productPage.getCounterIconText(), "2");

        int priceOfFirstMonitor2 = productPage.getMonitorPriceOnSelectionPage();
        String nameOfFirstMonitor2 = productPage.getMonitorName();

        productPage.openComparePage();

        assertEquals(comparisonPage.getBlocksCount(), 2);

        String actualNameOfMonitor1 = comparisonPage.getMonitorName(2);
        assertEquals(actualNameOfMonitor1, nameOfFirstMonitor1);

        String actualNameOfMonitor2 = comparisonPage.getMonitorName(1);
        assertEquals(actualNameOfMonitor2, nameOfFirstMonitor2);

        int actualpriceOfMonitor1 = comparisonPage.getMonitorPriceOnComparePage(2);
        assertEquals(actualpriceOfMonitor1, priceOfFirstMonitor1);

        int actualpriceOfMonitor2 = comparisonPage.getMonitorPriceOnComparePage(1);
        assertEquals(actualpriceOfMonitor2, priceOfFirstMonitor2);

    }
}


