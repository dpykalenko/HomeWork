package pagesTask2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class СomparisonFactoryPage extends BasicFactoryPage {
    @FindBy(xpath="//li[@class='products-grid__cell ng-star-inserted']")
    List<WebElement> compareBlocks;

    By productsGrid = By.xpath("//ul[@class='products-grid']");

    public СomparisonFactoryPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this);
    }
    public int getBlocksCount(){
        waitForElement(productsGrid);
        return compareBlocks.size();
    }
    public String getMonitorName(int numberOfMonitor){
        WebElement monitorElement = driver.findElement(By.xpath("(//a[@class='product__heading'])["+ numberOfMonitor +"]"));
        String actualNameOfMonitor = monitorElement.getText();
        return actualNameOfMonitor;
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
}
