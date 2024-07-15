import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class TimerTimePotrebTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://myfin.by/";
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 20000;
        Configuration.headless = true;
        Configuration.holdBrowserOpen = false;
        System.setProperty("file.encoding", "UTF-8");

    }

    @Test
    public void testTimerTimePotreb() {
        open("/kredity/potrebitelskie");
        $(".cookie__btn button").click();
        $("#mass-form-in-modal").should(appear);
        $("#mass-form-in-modal").shouldHave(Condition.text("Подбор кредита"));
        $("#mass-form-in-modal #w0").sendKeys("292806639");
        $("#mass-form-in-modal #onl_fio").should(appear);
        $("#mass-form-in-modal #onl_fio").setValue("Тест");
        $("#mass-form-in-modal #onl_summa").should(appear);
        $("#mass-form-in-modal #onl_summa").setValue("1234");
        $("#mass-form-in-modal #check_consent").shouldHave(attribute("data-error"));
        executeJavaScript("arguments[0].click();",
                $("#mass-form-in-modal #check_consent"));
        $("#mass-form-in-modal [data-request-form=\"submit-btn\"]").scrollTo();
        $("#mass-form-in-modal [data-request-form=\"submit-btn\"]").click();
        $("#mass-form-in-modal").should(text("Спасибо"));
        }
    }

