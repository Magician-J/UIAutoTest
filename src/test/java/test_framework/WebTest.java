package test_framework;

import org.junit.jupiter.api.Test;

/**
 * @author jiaoyl
 * @date 2020/6/21 19:50
 */
public class WebTest {
    @Test
    void classic(){
        BasePage web = UIAutoFactory.creat("web");
        UIAuto uiAuto = web.load("/test_framework/webuiauto.yaml");
        web.run(uiAuto);
    }
}
