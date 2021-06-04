package pagesTask2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomeFactoryPage extends BasicFactoryPage {
    By monitorXpath = By.xpath("//a[@class='menu__link'][@href='https://hard.rozetka.com.ua/monitors/c80089/']");

    @FindBy(xpath = "//ul[@class='menu-categories menu-categories_type_main']/li[1]")
    WebElement laptopsLink;
    @FindBy(xpath = "//a[@class='menu__link'][@href='https://hard.rozetka.com.ua/monitors/c80089/']")
    WebElement monitorsLink;

    public HomeFactoryPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this);
    }

    public void openMonitorsListPage(){
        Actions builder = new Actions(driver);
        builder.moveToElement(laptopsLink).perform();
        waitForElement(monitorXpath);
        builder.moveToElement(monitorsLink).click().perform();
    }
}
