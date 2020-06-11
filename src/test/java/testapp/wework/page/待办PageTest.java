package testapp.wework.page;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * @author jiaoyl
 * @date 2020/6/11 22:07
 */
class 待办PageTest {
    private  static Wework wework;
    @BeforeAll
    static void setUp() {
        wework = new Wework();
    }

    @AfterAll
    static void tearDown() {
        wework.quit();
    }

    @Test
    void 添加待办() {

        assertTrue(wework.待办().添加待办("今日待办").获取待办().contains("今日待办"));
        //此处想打印结果，就必须返回到首页在进来否则报错，定位不到首页【待办】按钮。todo:为啥日程可以？
//        System.out.println(wework.待办().获取待办(null));
    }
}