package test_framework;

import org.junit.jupiter.api.Test;

/**
 * @author jiaoyl
 * @date 2020/6/21 17:25
 */
class UIAutoFactoryTest {

    @Test
    void creat() {
        //定义引擎
        BasePage web = UIAutoFactory.creat("web");
        UIAuto uiAuto = web.load("/test_framework/webuiauto.yaml");
        //运行BasePage 下的 run方法：
        web.run(uiAuto);
    }
}