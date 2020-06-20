package test_web.weiwork.testcase;

import org.junit.jupiter.api.Test;

/**
 * @author jiaoyl
 * @date 2020/6/4 0:20
 */
public class testresouse {
    @Test
    public void test(){
        System.out.println(getClass().getResource("/contactimport.xlsx").getPath());
    }
}
