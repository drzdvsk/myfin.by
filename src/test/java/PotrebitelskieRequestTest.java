import Pages.PotrebitelskiePage;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class PotrebitelskieRequestTest {


    PotrebitelskiePage potrebitelskiePage = new PotrebitelskiePage();

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
    public void timerTimeMassRequestTest() {
        potrebitelskiePage
                .openPage()
                .acceptCookie()
                .timerMassFormAppear()
                .setTimerUserPhone("292806639")
                .setTimerUserFio("Тест")
                .setTimerSumma("1234")
                .checkConsent()
                .clickSubmit()
                .happyMessage();
    }
    @DisplayName("второй тест")
    @Test
    public void listingProductRequestTest() {
        potrebitelskiePage
                .openPage()
                .acceptCookie()
                .bvePotrebNujdy()
                .bvePotrebForm()
                .setProductUserPhone("292806639")
                .setProductUserFio("Тест")
                .checkPrConsent()
                .clickPrSubmit();
    }
}





