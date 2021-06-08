package ui.HomeWork26.Task1;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class WizzAirTest{
    @Test
    public void ticketsSearchTest(){
        Configuration.timeout = 70000;
        open("https://wizzair.com/");

        $("#search-departure-station").val("Vienna");
        $x("//mark[contains(text(),'Vienna')]").click();
        $("#search-arrival-station").val("Kyiv");
        $x("//mark[contains(text(),'Kyiv')]").click();
        $("#search-departure-date").click();
        $x("//button[@data-pika-year='2021'][@data-pika-month='5'][@data-pika-day='18']").click();
        $x("//button[@data-pika-year='2021'][@data-pika-month='5'][@data-pika-day='25']").click();
        $x("//button[@data-test='calendar-shrinkable-ok-button']").click();
        $x("//div[@data-test='flight-search-search-passenger']").click();
        $x("//span[contains(text(),'adult')]/../../button[@data-test='services-flight-search-increment']").click();
        $x("//button[@data-test='flight-search-hide-panel']").click();
        $x("//button[@data-test='flight-search-submit']").click();

        switchTo().window(1);
        $$x("//dev[@class='flight-select__fare-selector transition-fade-in transition-zoom-in transition-fade-in-enter transition-zoom-in-enter']").shouldHaveSize(2);
        $$x("//time[@data-test='flight-select-flight-info-details-time']").get(0).shouldHave(Condition.text(" Fri, 18 Jun 2021 "));
        $$x("//time[@data-test='flight-select-flight-info-details-time']").get(0).shouldHave(Condition.text(" Fri, 25 Jun 2021 "));
        $x("//aside//div[@data-test='search-change__details__passengers']").shouldHave(Condition.text(" 2 Passengers "));
    }
}
