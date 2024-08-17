import Pages.PotrebitelskiePage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.awaitility.Awaitility;
import org.awaitility.core.ConditionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;

import static com.codeborne.selenide.Selenide.$;

public class CheckAnalyticsSendTest {

    private static ConditionFactory WAITER;

    @BeforeAll
    public static void setUp() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences loggingPreferences = new LoggingPreferences();

        loggingPreferences.enable(LogType.BROWSER, Level.ALL);
        loggingPreferences.enable(LogType.PERFORMANCE, Level.ALL);

        capabilities.setCapability("goog:loggingPrefs", loggingPreferences);
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        Configuration.browserCapabilities = capabilities;
        Configuration.timeout = 10000;
        Configuration.pageLoadTimeout = 10000;
        Configuration.holdBrowserOpen = true;

        WAITER = Awaitility.given()
                .ignoreExceptions()
                .pollInterval(1, TimeUnit.SECONDS)
                .await()
                .dontCatchUncaughtExceptions()
                .atMost(10, TimeUnit.SECONDS);
    }

    private boolean waitLogs(String expectedMessage) {
        WebDriver driver = WebDriverRunner.getWebDriver();
        AtomicBoolean isLogPresent = new AtomicBoolean(false);

        WAITER.until(() -> {
            LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
            printLogs(logEntries);
            isLogPresent.set(logEntries.getAll().stream()
                    .anyMatch(x -> x.getMessage().contains(expectedMessage)));
            return isLogPresent.get();
        });

        return isLogPresent.get();
    }

    private void printLogs(LogEntries logEntries) {
        for (LogEntry entry : logEntries) {
            System.out.println(entry.getMessage());
        }
    }

    @Test
    public void checkLogsTest() {
        Selenide.open("https://myfin.by/kredity/potrebitelskie");
        PotrebitelskiePage potrebitelskiePage = new PotrebitelskiePage();
        potrebitelskiePage
                .acceptCookie()
                .bvePotrebNujdy();

        boolean isLogExist = waitLogs("https://myfin.by/minify/b08ed08a3152a6e3eaa280e3e10c36bd18793e9a.js 1085:8 Object");
        Assertions.assertTrue(isLogExist, "Лог не пришел");
    }
}
