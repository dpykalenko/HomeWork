package pagesTask2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class BasicFactoryPage {
    WebDriver driver;
    private WebDriverWait wait;

    public BasicFactoryPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void navigateBack(){
        driver.navigate().back();
    }

    public void waitForElement(By selector)
    {
        wait.until(presenceOfElementLocated(selector));
    }
}
