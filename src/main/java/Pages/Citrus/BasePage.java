package Pages.Citrus;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Selenide.$x;

public class BasePage {

    SelenideElement bannerCloseButton = $x("//i[contains(@class,'el-dialog__close')]");
    protected void closeBanner(){
        new WebDriverWait(WebDriverRunner.getWebDriver(),10).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        if (bannerCloseButton.isDisplayed()){
            bannerCloseButton.click();
        }
    }
}
