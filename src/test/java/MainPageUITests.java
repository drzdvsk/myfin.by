import Pages.MainPage;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Selenide.$$;


public class MainPageUITests {

    static MainPage mainPage = new MainPage();

    @BeforeAll
    static void beforeAll() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = false;
        System.setProperty("file.encoding", "UTF-8");
        mainPage
                .openMainPage()
                .acceptCookie();
    }
    static Stream<Arguments> checkLogoAndLinksAtMainPageHeader() {
        return Stream.of(
                Arguments.of(
                        "https://myfin.by/images/new/logo-myfin--new.svg",
                        "MYFIN",
                        "MYFIN",
                        List.of("Все банки Реклама Стать автором Войти")
                )
        );
    }
    @MethodSource("checkLogoAndLinksAtMainPageHeader")
    @ParameterizedTest(name = "Лого в Header содержит изображение {0} alt {1} title {2}, Header содержит следующие ссылки {3}")
    @DisplayName("Проверка хэдера на содержание логотипа и ссылок")
    void checkLogoAndLinksAtMainPageHeader(String expectedLogoSrc, String expectedLogoAlt, String expectedLogoTitle, List<String> expectedLinks) {
        mainPage
                .checkMainPageHeaderLogo(expectedLogoSrc, expectedLogoAlt, expectedLogoTitle)
                .checkMainPageHeaderLinks(expectedLinks);
    }

    static Stream<Arguments> checkHeaderMenuAtMainPage() {
        return Stream.of(
                Arguments.of(
                        List.of("Курсы валют", "Новости", "Кредиты", "Вклады", "Карты", "Бизнес", "Онлайн-сервисы", "Инвестиции", "Еще")

                )
        );
    }
    @MethodSource("checkHeaderMenuAtMainPage")
    @ParameterizedTest(name = "Меню в Header содержит {0}")
    @DisplayName("Проверка меню хэдэра на содержание всех разделов ")
    void checkHeaderMenuAtMainPage(List<String> expectedMenuSection) {
        mainPage
                .checkMainPageHeaderMenu(expectedMenuSection);
    }
}

