package Pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PotrebitelskiePageSteps {
    @Step("Открываем страницу потребительских кредитов")
        public void openPage() {
            open("https://myfin.by/kredity/potrebitelskie");
    }
    @Step("Если появились Cookie, то подтверждаем согласие")
        public void ifCookieExistClick() {
        if ($(".cookie__btn button").exists()) {
            $(".cookie__btn button").click();
        }
    }
    @Step("Смотрим есть ли хлебные крошки")
        public void checkBreadcrumbs() {
        $("ul.breadcrumb")
                .shouldBe(visible)
                .shouldHave(text("Потребительские кредиты в Беларуси"));
    }

}
