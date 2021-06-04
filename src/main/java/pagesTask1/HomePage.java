package pagesTask1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasicPage {
    By monitorXpath = By.xpath("//a[@class='menu__link'][@href='https://hard.rozetka.com.ua/monitors/c80089/']");
    By menuCategories = By.xpath("//ul[@class='menu-categories menu-categories_type_main']/li[1]");

    public HomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void openMonitorsListPage(){
        Actions builder = new Actions(driver);
        WebElement laptopsLink = driver.findElement(menuCategories);
        builder.moveToElement(laptopsLink).perform();
        waitForElement(monitorXpath);
        builder.moveToElement(driver.findElement(monitorXpath)).click().perform();
    }
}
