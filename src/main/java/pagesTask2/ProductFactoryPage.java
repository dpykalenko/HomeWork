package pagesTask2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class ProductFactoryPage extends BasicFactoryPage {

    @FindBy(xpath="//*[@class='compare-button ng-star-inserted']")
    WebElement compareButton;
    @FindBy(xpath="//*[@class='product-prices__big product-prices__big_color_red']")
    WebElement priceElement;
    @FindBy(xpath="//*[@class='product__title']")
    WebElement titleOfProduct;
    @FindBy(xpath="//rz-comparison")
    WebElement headerComaparisonButton;
    @FindBy(xpath="//rz-icon-counter//span")
    WebElement counterComparedProducts;
    @FindBy(xpath="//a[@class='comparison-modal__link']")
    WebElement comparisonPopup;
    @FindBy(xpath="//div[@class='product-about__right']")
    WebElement informationAboutProduct;

    public ProductFactoryPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this);
    }

    public void addToCompare(){
        wait.until(visibilityOf(informationAboutProduct));
        compareButton.click();
    }

    public String getCounterIconText(){
        wait.until(visibilityOf(counterComparedProducts));
        return counterComparedProducts.getText();
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
        wait.until(visibilityOf(comparisonPopup));
        comparisonPopup.click();
    }
}
