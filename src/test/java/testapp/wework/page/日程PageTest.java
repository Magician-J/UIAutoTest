package testapp.wework.page;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class 日程PageTest {
    private static Wework wework;

    @BeforeAll
   static void beforeAll() {
         wework = new Wework();
    }

    @AfterAll
    static void tearDown() {
    }

    @Test
    void 添加() {
        assertTrue(wework.日程().添加("上班打卡", null).获取日程(null).contains("上班打卡"));
//        System.out.println(wework.日程().获取日程(null));
    }

}