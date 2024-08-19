package Pages;

import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    private final SelenideElement
            cookie = $(".cookie__btn button"),
            headerLogo = $(".header-upd__logo img"),
            headerSearchButton = $(".main-nav_btn-search"),
            headerSearchTextArea = $("input.main-nav_search.active"),
            searchResults = $(".gsc-expansionArea"),
            signButton = $(".sign-btn"),
            loginForm = $("#login-form-modal"),
            loginFormPhoneInput = $("#login_form_phone"),
            loginFormSubmitButton = $("div[data-login-form-btn='step-1']");

    public MainPage openMainPage() {
        open("https://myfin.by/");
        return this;
    }

    public void acceptCookie() {
        if ($(cookie).exists()) {
            $(cookie).click();
        }
    }
    public void checkMainPageHeaderLinks(List<String> expectedLinks) {
        $$(".header-upd__links-list li")
                .filter(visible)
                .shouldHave(texts(expectedLinks));
    }
    public MainPage checkMainPageHeaderLogo(String expectedSrc, String expectedAlt, String expectedTitle) {
        headerLogo
                .shouldHave(attribute("src", expectedSrc))
                .shouldHave(attribute("alt", expectedAlt))
                .shouldHave(attribute("title", expectedTitle));
        return this;
    }
    public void checkMainPageHeaderMenu(List<String> expectedMenuSection) {
        $$(".main-nav_link")
                .filter(visible)
                .shouldHave(texts(expectedMenuSection));
    }
    public MainPage checkAndClickHeaderSearchButton() {
        headerSearchButton
                .shouldBe(visible)
                .click();
        return this;
    }
    public void checkSearchResultAndCorrectUrls(String searchQuery, String expectedHref) {
        headerSearchTextArea
                .shouldBe(visible)
                .setValue(searchQuery)
                .pressEnter();
        searchResults.$$("a")
                .findBy(href(expectedHref))
                .shouldBe(visible);
    }
    public void loginWithIncorrectPhoneCode (String incorrectPhoneNumber) {
        signButton.click();
        loginForm
                .shouldBe(visible)
                .shouldHave(text("Вход или регистрация"));
        loginFormPhoneInput
                .setValue(incorrectPhoneNumber);
        loginFormSubmitButton.shouldHave(cssClass("disabled"));
        executeJavaScript("arguments[0].click();", $(".custom-modal__close"));

    }
}

