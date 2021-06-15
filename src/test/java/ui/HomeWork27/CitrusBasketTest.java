package ui.HomeWork27;

import Pages.Citrus.CitrusHomePage;
import Pages.Citrus.CitrusProductListPage;
import Pages.Citrus.CitrusProductPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

public class CitrusBasketTest {

    CitrusHomePage citrusHomePage;
    CitrusProductListPage citrusProductListPage;
    CitrusProductPage citrusProductPage;
    String productName = "Apple iPhone 11 64Gb Black (MHDA3) Slim Box";

    @BeforeClass
    public void setup() {
        open("https://www.citrus.ua/");
        citrusHomePage = new CitrusHomePage();
        citrusProductListPage = new CitrusProductListPage();
        citrusProductPage = new CitrusProductPage();

    }
    @BeforeMethod
    public void cleanBasket(){
        Selenide.clearBrowserLocalStorage();
    }

    @Test
    public void productPageBasketTest() {
        citrusHomePage.closeBanner();
        citrusHomePage.hoverMenuLineSmartphony();
        citrusHomePage.clickLinkInMenu("Apple");

        citrusProductListPage.clickOnProductByName(productName);

        String productPrice = citrusProductPage.getProductPrice();
        citrusProductPage.clickOnBuyButton();

        citrusProductPage.getBasket().shouldBe(Condition.visible);
        citrusProductPage.getProductNameFromBasket().shouldHaveSize(1);
        citrusProductPage.getProductNameFromBasket().get(0).shouldHave(Condition.text(productName));
        citrusProductPage.getBasketTotalPrice().shouldHave(Condition.text(productPrice));
    }

    @Test
    public void productListingPageBasketTest() {
        citrusHomePage.closeBanner();
        citrusHomePage.searchProduct(productName);
        citrusHomePage.closeBanner();

        String productPrice = citrusProductListPage.getProductPriceByName(productName);

        citrusProductListPage.addProductToBasket(productName);

        citrusProductListPage.getBasket().shouldBe(Condition.visible);
        citrusProductListPage.getProductNameFromBasket().shouldHaveSize(1);
        citrusProductListPage.getProductNameFromBasket().get(0).shouldHave(Condition.text(productName));
        citrusProductListPage.getBasketTotalPrice().shouldHave(Condition.text(productPrice));
    }
}
