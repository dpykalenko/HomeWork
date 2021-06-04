package pagesTask1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends BasicPage {
    By informationAboutProduct = By.xpath("//div[@class='product-about__right']");
    By compareButton = By.xpath("//*[@class='compare-button ng-star-inserted']");
    By counterComparedProducts = By.xpath("//rz-icon-counter//span");
    By priceOfProduct = By.xpath("//*[@class='product-prices__big product-prices__big_color_red']");
    By titleOfProduct = By.xpath("//*[@class='product__title']");
    By headerComaparisonButton = By.xpath("//rz-comparison");
    By comparisonPopup = By.xpath("//a[@class='comparison-modal__link']");


    public ProductPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void addToCompare(){
        waitForElement(informationAboutProduct);
        driver.findElement(compareButton).click();
    }

    public String getCounterIconText(){
        waitForElement(counterComparedProducts);
        return driver.findElement(counterComparedProducts).getText();
    }
    public int getMonitorPriceOnSelectionPage(){
        WebElement priceElement = driver.findElement(priceOfProduct);
        String priceText = priceElement.getText();
        String priceTextClear = priceText
                .replaceAll("\\s", "")
                .replace("₴","");
        return Integer.parseInt(priceTextClear);
    }
    public String getMonitorName(){
        return driver.findElement(titleOfProduct).getText();
    }

    public void openComparePage(){
        driver.findElement(headerComaparisonButton).click();
        waitForElement(comparisonPopup);
        driver.findElement(comparisonPopup).click();
    }
}
