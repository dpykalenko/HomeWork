package pagesTask2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductFactoryPage extends BasicFactoryPage {

    @FindBy(xpath="//*[@class='compare-button ng-star-inserted']")
    WebElement compareButton;
    @FindBy(xpath="//*[@class='product-prices__big product-prices__big_color_red']")
    WebElement priceElement;
    @FindBy(xpath="//*[@class='product__title']")
    WebElement titleOfProduct;
    @FindBy(xpath="//rz-comparison")
    WebElement headerComaparisonButton;

    By counterComparedProducts = By.xpath("//rz-icon-counter//span");
    By comparisonPopup = By.xpath("//a[@class='comparison-modal__link']");
    By informationAboutProduct = By.xpath("//div[@class='product-about__right']");

    public ProductFactoryPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this);
    }

    public void addToCompare(){
        waitForElement(informationAboutProduct);
        compareButton.click();
    }

    public String getCounterIconText(){
        waitForElement(counterComparedProducts);
        return driver.findElement(counterComparedProducts).getText();
    }
    public int getMonitorPriceOnSelectionPage(){
        String priceText = priceElement.getText();
        String priceTextClear = priceText
                .replaceAll("\\s", "")
                .replace("₴","");
        return Integer.parseInt(priceTextClear);
    }
    public String getMonitorName(){
        return titleOfProduct.getText();
    }

    public void openComparePage(){
        headerComaparisonButton.click();
        waitForElement(comparisonPopup);
        driver.findElement(comparisonPopup).click();
    }
}
