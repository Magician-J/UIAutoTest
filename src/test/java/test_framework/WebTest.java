package test_framework;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author jiaoyl
 * @date 2020/6/21 19:50
 */
public class WebTest {
    private static BasePage basePage;
    @ParameterizedTest
    //@MethodSource 未指定参数时，从同方法名的方法下读取。
    @MethodSource
    void classic(UIAuto uiAuto){
        basePage.run(uiAuto);
    }

    //参数化方法
    static Stream<UIAuto> classic(){
        basePage = UIAutoFactory.creat("web");
        List<UIAuto> all = new ArrayList<UIAuto>();
        Arrays.asList(
                "/test_framework/webuiauto1.yaml",
                "/test_framework/webuiauto2.yaml"
        ).stream().forEach(path->{
            UIAuto uiAuto = basePage.load(path);
            all.add(uiAuto);
        });
        return all.stream();
    }
}
