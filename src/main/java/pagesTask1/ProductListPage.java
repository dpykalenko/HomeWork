package pagesTask1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ProductListPage extends BasicPage {
    By uploadedElements = By.xpath("//div[@class='goods-tile__inner']");
    By monitorsBlocks = By.xpath("//div[@class='goods-tile__inner']");

    public ProductListPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void filterMonitorsAndSelect(int priceLimit){
        waitForElement(uploadedElements);
        List<WebElement> resultTiles = driver.findElements(monitorsBlocks);
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
