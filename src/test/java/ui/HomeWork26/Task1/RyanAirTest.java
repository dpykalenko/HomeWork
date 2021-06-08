package ui.HomeWork26.Task1;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class RyanAirTest {
    @Test
    public void ticketsSearchTest() {
        Configuration.timeout = 5000;
        open("https://www.ryanair.com/");
        $("[data-ref='cookie.accept-all']").click();
        $x("//input[@class='input-button__input ng-star-inserted'][@placeholder='Відправлення']").click();
        $x("//span[contains(text(),' Австрія ')]").click();
        $x("//span[@data-id='VIE']").click();
        $x("//input[@class='input-button__input ng-star-inserted'][@placeholder='Призначення']").val("Kyiv");
        $x("//span[@data-id='KBP']").click();
        $x("//div[contains(text(),' Відправлення ')]").click();
        $x("//div[contains(text(),' лют. ')]").click();
        $x("//div[@data-id='2022-02-19']").click();
        $x("//div[@data-id='2022-02-22']").click();
        $x("//div[contains(text(),'1')]//..//div[@class='counter__button-wrapper--enabled']").click();

        $x("//button[@data-ref='flight-search-widget__cta']").click();

        $$x("//flight-card").shouldHaveSize(2);

        $$x("//span[contains(text(),'19')]").get(0).shouldHave(Condition.text("19"));
        $$x("//span[contains(text(),'лют.')]").get(0).shouldHave(Condition.text("лют."));

        $$x("//span[contains(text(),'22')]").get(0).shouldHave(Condition.text("22"));
        $$x("//span[contains(text(),'лют.')]").get(0).shouldHave(Condition.text("лют."));

    }
}
