package testapp.wework.page;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author jiaoyl
 * @date 2020/6/12 23:32
 */
class ReportPageTest {
    private static MainPage mainPage;
    @BeforeAll
    static void setUp() {
        mainPage = new MainPage();
    }

    @AfterAll
    static void tearDown() {
        mainPage.quit();
    }

    @Test
    void add_Day_Report() {
        mainPage.workStation().toReportpage().add_Day_Report("今日总结1。");
    }

    @Test
    void week_Report_Page() {
    }
}