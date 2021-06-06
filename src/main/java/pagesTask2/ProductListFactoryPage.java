package pagesTask2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class ProductListFactoryPage extends BasicFactoryPage {
    @FindBy(xpath="//div[@class='goods-tile__inner']")
    WebElement uploadedElements;
    @FindBy(xpath="//div[@class='goods-tile__inner']")
    List <WebElement> monitorsBlocks;
    public ProductListFactoryPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this);
    }

    public void filterMonitorsAndSelect(int priceLimit){
        wait.until(visibilityOf(uploadedElements));
        List<WebElement> resultTiles = monitorsBlocks;
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
