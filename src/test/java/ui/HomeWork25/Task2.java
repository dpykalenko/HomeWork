package ui.HomeWork25;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pagesTask2.HomeFactoryPage;
import pagesTask2.ProductFactoryPage;
import pagesTask2.ProductListFactoryPage;
import pagesTask2.СomparisonFactoryPage;

import static org.testng.AssertJUnit.assertEquals;

public class Task2 {
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
        ProductListFactoryPage productListFactoryPage = new ProductListFactoryPage(driver, wait);
        HomeFactoryPage homeFactoryPage = new HomeFactoryPage(driver, wait);
        ProductFactoryPage productFactoryPage = new ProductFactoryPage(driver, wait);
        СomparisonFactoryPage comparisonFactoryPage = new СomparisonFactoryPage(driver, wait);

        homeFactoryPage.openMonitorsListPage();
        productListFactoryPage.filterMonitorsAndSelect(maxPrice);
        productFactoryPage.addToCompare();
        assertEquals(productFactoryPage.getCounterIconText(), "1");

        int priceOfFirstMonitor1 = productFactoryPage.getMonitorPriceOnSelectionPage();
        String nameOfFirstMonitor1 = productFactoryPage.getMonitorName();

        productFactoryPage.navigateBack();

        productListFactoryPage.filterMonitorsAndSelect(priceOfFirstMonitor1);
        productFactoryPage.addToCompare();
        assertEquals(productFactoryPage.getCounterIconText(), "2");

        int priceOfFirstMonitor2 = productFactoryPage.getMonitorPriceOnSelectionPage();
        String nameOfFirstMonitor2 = productFactoryPage.getMonitorName();

        productFactoryPage.openComparePage();

        assertEquals(comparisonFactoryPage.getBlocksCount(), 2);

        String actualNameOfMonitor1 = comparisonFactoryPage.getMonitorName(2);
        assertEquals(actualNameOfMonitor1, nameOfFirstMonitor1);

        String actualNameOfMonitor2 = comparisonFactoryPage.getMonitorName(1);
        assertEquals(actualNameOfMonitor2, nameOfFirstMonitor2);

        int actualpriceOfMonitor1 = comparisonFactoryPage.getMonitorPriceOnComparePage(2);
        assertEquals(actualpriceOfMonitor1, priceOfFirstMonitor1);

        int actualpriceOfMonitor2 = comparisonFactoryPage.getMonitorPriceOnComparePage(1);
        assertEquals(actualpriceOfMonitor2, priceOfFirstMonitor2);

    }
}


