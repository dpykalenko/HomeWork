package Pages.Citrus;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class CitrusProductPage extends BasePage {
    SelenideElement productPrice = $x("//b[@class='buy-section__new-price']");
    SelenideElement buyButton = $x("//button[@class='btn orange buy-section__btn buy-section__buy-btn available full']");
    SelenideElement basketWidget = $x("span[class='el-dialog__title']");
    ElementsCollection basketProductName = $$x("a[class='ex-active active ctrs-basket-product__name']");
    SelenideElement basketTotalPrice = $x("span[class='ctrs-main-price ctrs-basket-footer__new-price']");

    public String getProductPrice() {
        return productPrice.getText().trim();
    }

    public void clickOnBuyButton() {
        buyButton.click();
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
    public  void closeBanner(){
        super.closeBanner();
    }
}
