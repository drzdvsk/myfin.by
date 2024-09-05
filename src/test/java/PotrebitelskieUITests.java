import Pages.PotrebitelskiePage;
import Pages.PotrebitelskiePageSteps;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class PotrebitelskieUITests {

    @BeforeAll
    static void beforeAll() {
        Configuration.timeout = 20000;
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://myfin.by/";
        Configuration.pageLoadStrategy = "eager";
        Configuration.headless = true;
        Configuration.holdBrowserOpen = false;
        System.setProperty("file.encoding", "UTF-8");
    }
    @Test
    public void checkBreadcrumbsPotrebitelskiePageTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://myfin.by/kredity/potrebitelskie");
        if ($(".cookie__btn button").exists()) {
            $(".cookie__btn button").click();
            $("ul.breadcrumb")
                    .shouldBe(visible)
                    .shouldHave(text("Потребительские кредиты в Беларуси"));


        }
    }
    @Test
    public void checkBreadcrumbsPotrebitelskiePageStepsTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        PotrebitelskiePageSteps potrebitelskiePageSteps = new PotrebitelskiePageSteps();

            potrebitelskiePageSteps.openPage();
            potrebitelskiePageSteps.ifCookieExistClick();
            potrebitelskiePageSteps.checkBreadcrumbs();
    }
    @Test
    public void checkBreadcrumbsPotrebitelskiePageLambdaTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем страницу потребительскиххх кредитов", () -> {
            open("https://myfin.by/kredity/potrebitelskie");
        });
        step("Если появились Cookie, то подтверждаем согласие", () -> {
            if ($(".cookie__btn button").exists()) {
                $(".cookie__btn button").click();
            }
        });
        step("Смотрим есть ли хлебные крошки", () -> {
            $("ul.breadcrumb")
                    .shouldBe(visible)
                    .shouldHave(text("Потребительские кредиты в Беларуси"));
        });
    }
}
