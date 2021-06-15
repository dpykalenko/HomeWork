package Pages.Citrus;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class CitrusProductListPage extends BasePage{
    String buyButtonByProductName = "*//h5[contains(text(),'%s')]/../../..//i[contains(@class, 'icon-new')]";
    String productPriceByName= ("*//h5[contains(text(),'%s')]/../../..//span[@class='price-number']");
    SelenideElement basketWidget = $x("span[class='el-dialog__title']");
    ElementsCollection basketProductName = $$x("a[class='ex-active active ctrs-basket-product__name']");
    SelenideElement basketTotalPrice = $x("span[class='ctrs-main-price ctrs-basket-footer__new-price']");
    String productNameLink = "//a/span[text()='%s']";
    public void clickOnProductByName(String productName) {
        $x(String.format(productNameLink, productName)).click();
    }

    public SelenideElement getBasket() {
        return basketWidget;
    }

    public ElementsCollection getProductNameFromBasket() {
        return basketProductName;
    }

    public SelenideElement getBasketTotalPrice() {
        return basketTotalPrice;
    }

    public String getProductPriceByName(String productName) {
        return $x(String.format(productPriceByName, productName)).getText();
    }

    public void addProductToBasket(String productName) {
        $x(String.format(buyButtonByProductName, productName)).click();
    }
    public  void closeBanner(){
        super.closeBanner();
    }
}
