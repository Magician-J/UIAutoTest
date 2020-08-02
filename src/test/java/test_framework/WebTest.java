package test_framework;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * @author jiaoyl
 * @date 2020/6/21 19:50
 */
public class WebTest {
    private static BasePage basePage;

    @BeforeAll
    static void beforeAll(){
        //todo:加载通用配置
    }

    @BeforeEach
    void  befortEach(){
        //todo:每个用例相关
    }


    @ParameterizedTest(name = "{index} {1}")    // 自定义显示名称
    @MethodSource    //@MethodSource 未指定参数时，从同方法名的方法下读取。
    void classic(UIAuto uiAuto, String path){
        basePage.run(uiAuto);
    }


    //同名参数化方法classic
    static List<Arguments> classic(){
        basePage = UIAutoFactory.creat("web");
        basePage.loadpages("src/main/resources/test_framework/");
        List<Arguments> all = new ArrayList<Arguments>();
        Arrays.asList(
//                "/test_framework/webuiauto1.yaml",
//                "/test_framework/webuiauto2.yaml",
                "/test_framework/webuiauto3.yaml"
        ).stream().forEach(path->{
            UIAuto uiAuto = basePage.load(path);
            //描述，运行时的描述信息
            uiAuto.description=path;
            arguments(uiAuto,uiAuto.description);
            all.add(arguments(uiAuto,uiAuto.description));
        });
        return all;
    }
}
