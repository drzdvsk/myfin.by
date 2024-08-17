package Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class PotrebitelskiePage {
    private final SelenideElement
            cookie = $(".cookie__btn button"),
            timerTimeMassForm = $("#mass-form-in-modal"),
            timerTimeMob = $("#mass-form-in-modal #w0"),
            timerTimeFio = $("#mass-form-in-modal #onl_fio"),
            timerTimeSumma = $("#mass-form-in-modal #onl_summa"),
            massConsent = $("#mass-form-in-modal #check_consent"),
            timerTimeSubmitButton = $("#mass-form-in-modal [data-request-form='submit-btn']"),
            bvePotrebForm = $("[onclick*='bvebank_potreb']"),
            productForm = $("#product-form-in-modal"),
            productFormMob = $("#product-form-in-modal #w0"),
            productFormFio = $("#product-form-in-modal #onl_fio"),
            productConsent = $("#product-form-in-modal #check_consent"),
            productSubmitButton = $("#product-form-in-modal [data-request-form='button']");


    public PotrebitelskiePage acceptCookie() {
        if ($(cookie).exists()) {
            $(cookie).click();
        }
        return this;
    }
    public PotrebitelskiePage timerMassFormAppear() {
        $(timerTimeMassForm)
                .should(appear)
                .shouldHave(Condition.text("Подбор кредита"));
        return this;
    }
    public PotrebitelskiePage setTimerUserPhone(String value) {
        $(timerTimeMob).setValue(value);
        return this;
    }
    public PotrebitelskiePage setTimerUserFio(String value) {
        $(timerTimeFio)
                .should(appear)
                .setValue(value);
        return this;
    }
    public PotrebitelskiePage setTimerSumma(String value) {
        $(timerTimeSumma)
                .should(appear)
                .setValue(value);
        return this;
    }
    public PotrebitelskiePage clickSubmit() {
        $(timerTimeSubmitButton)
                .scrollTo()
                .click();
        return this;
    }
    public void happyMessage() {
        $(timerTimeMassForm).should(text("Спасибо"));
    }
    public PotrebitelskiePage checkConsent() {
        $(massConsent).shouldHave(attribute("data-error"));
        executeJavaScript("arguments[0].click();",
                $(massConsent));
        return this;
    }
    public PotrebitelskiePage openPage() {
        open("/kredity/potrebitelskie");
        return this;
    }
    public PotrebitelskiePage bvePotrebNujdy() {
        Boolean hasSendClickData = executeJavaScript(
                "return arguments[0].getAttribute('onclick').includes('sendClickDataToGA4');",
                $(bvePotrebForm)
        );
        if (!hasSendClickData) {
            throw new AssertionError("Element does not contain sendClickDataToGA4 in onclick attribute");
        }
        $(bvePotrebForm).click();
        return this;
    }
    public PotrebitelskiePage bvePotrebForm() {
        $(productForm)
                .should(appear)
                .shouldHave(Condition.text("На потребительские нужды"));
        return this;
    }
    public  PotrebitelskiePage setProductUserPhone(String value) {
        $(productFormMob)
                .should(appear)
                .setValue(value);
        return this;
    }
    public PotrebitelskiePage setProductUserFio(String value) {
        $(productFormFio)
                .should(appear)
                .setValue(value);
        return this;
    }
    public PotrebitelskiePage checkPrConsent() {
        $(productConsent).shouldHave(attribute("data-error"));
        executeJavaScript("arguments[0].click();",
                $(productConsent));
        return this;
    }
    public void clickPrSubmit() {
        $(productSubmitButton).click();
    }
}

