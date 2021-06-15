package Pages.Citrus;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;


public class CitrusHomePage extends BasePage{
    SelenideElement searchInput = $("#search-input");
    SelenideElement smartphonyMenuLine = $("//main[@class='home-page']//a[@href='/uk/smartfony/']/span[@class='title']");
    String menuLink = "//main[@class='home-page']//a/span[text()='%s']";


    public void hoverMenuLineSmartphony() {
       smartphonyMenuLine.hover();

    }

    public void clickLinkInMenu(String linkText) {
$x(String.format(menuLink, linkText)).click();
    }

    public void searchProduct(String productName) {
       searchInput.val(productName).pressEnter();
    }
    public  void closeBanner(){
        super.closeBanner();
    }
}
