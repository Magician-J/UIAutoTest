package test_web.weiwork.testcase;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import testweb.weiwork.page.ContantPage;
import testweb.weiwork.page.MainPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author jiaoyl
 * @date 2020/6/1 21:35
 */
public class TestContact {
    static MainPage mainPage;
    static ContantPage contantPage;
    @BeforeAll
    public static void beforeAll(){
        mainPage = new MainPage();
        contantPage = mainPage.toContact();
    }

    @Test
    void testAddMember(){
        String username =contantPage.addMember("test2","002","18518770002").serach("test2").getName();
        // todo： assert 搜索用户断言用户添加结果。
        assertEquals(username,"test2");
    }
    @Test
    void testSearch(){
        //搜索后删除,避免和新增case关联性强，建议删除另外的user
        contantPage.serach("test2").Delete();
    }
    @Test
    void testImportFromFile(){
        contantPage.imprtFromFile(this.getClass().getResource("/contactimport.xlsx").getPath());
    }
    @AfterAll
    public static void tearDown(){
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        contantPage.quit();
    }
}
