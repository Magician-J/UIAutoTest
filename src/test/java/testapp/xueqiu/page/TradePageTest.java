package testapp.xueqiu.page;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author jiaoyl
 * @date 2020/7/4 22:14
 */
class TradePageTest {
    private static TradePage tradePage;
    private static MainPage mainPage;
    @BeforeAll
    static void setUp() {
        mainPage = new MainPage();
        tradePage = mainPage.toTradePage();
    }

    @Test
    void registAStockTest() {
        tradePage.registAStock("18511111111","123456");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    static void tearDown() {
        tradePage.quit();
    }

}