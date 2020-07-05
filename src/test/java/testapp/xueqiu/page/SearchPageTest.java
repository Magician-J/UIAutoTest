package testapp.xueqiu.page;

import testapp.xueqiu.page.MainPage;
import testapp.xueqiu.page.SearchPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author jiaoyl
 * @date 2020/6/7 18:41
 */
public class SearchPageTest {
    static MainPage mainPage;
    static SearchPage searchPage;


    @BeforeAll
    static void beforeAll(){
        mainPage = new MainPage();
        // searchPage 实例化，是通过从MianPage返回的SearchPage实例化的。
        searchPage = mainPage.toSearch();
    }
    @ParameterizedTest
    @CsvSource({
            "alibaba,         阿里巴巴",
            "jd,        京东"
    })
    void search(String keywords, String name){
        assertEquals(searchPage.search(keywords).getSearchList().get(0),name);

    }
    @Test
    void getPrice(){
        assertTrue(searchPage.search("alibaba").getPrice() > 200);
    }
    @AfterAll
    public static void tearDown() {
        searchPage.quit();
    }
}
