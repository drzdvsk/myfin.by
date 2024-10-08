import Pages.PotrebitelskiePage;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PotrebitelskieRequestTest {

    PotrebitelskiePage potrebitelskiePage = new PotrebitelskiePage();

    @BeforeAll
    static void beforeAll() {
        Configuration.timeout = 20000;
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://myfin.by/";
        Configuration.pageLoadStrategy = "eager";
        Configuration.headless = false;
        Configuration.holdBrowserOpen = false;
        System.setProperty("file.encoding", "UTF-8");
    }

    @Test
    public void timerTimeMassFormRequestTest() {
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
    @Test
    public void listingProductFormRequestTest() {
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





