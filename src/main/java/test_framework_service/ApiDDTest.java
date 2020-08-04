package test_framework_service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import test_framework.BasePage;
import test_framework.UIAuto;
import test_framework.UIAutoFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * @author jiaoyl
 * @date 2020/6/21 19:50
 *
 * testcase 的数据驱动实现
 */

//
public class ApiDDTest {

    private static BaseApi baseApi;

    @ParameterizedTest(name = "{index} {1}")// name：表示使用第几个参数。0表示第一个，1表示第二个（这里取的name =add）
    @MethodSource
    void apiTest(ApiTestCaseModel apiTestCaseModel,String name){
        apiTestCaseModel.run(baseApi);

    }
    static List<Arguments> apiTest(){

        // 加载所有的api object
        baseApi = new BaseApi();
        //打印环境变量
//        System.out.println(System.getProperties());

        // 传入一个api参数，运行jar包时传入
        if (System.getProperty("api")!=null){
            baseApi.load(System.getProperty("api"));
        }else {
            System.out.println("load error，使用调试默认值");
            baseApi.load("src/main/resources/test_framework_service/api");
        }


        // 用来传递给参数化用例
        List<Arguments> testcases = new ArrayList<>();

        //读取所有的测试用例。
        String testcaseDir = "";
        if (System.getProperty("test")!=null){
            testcaseDir = System.getProperty("test");

        }else {
            System.out.println("load error，使用调试默认值");
//            baseApi.load("src/main/resources/test_framework_service/testcase");
            testcaseDir ="src/main/resources/test_framework_service/testcase";
        }

        String finalTestcaseDir = testcaseDir;
        Arrays.stream(new File(testcaseDir).list()).forEach(
                name->{
                    String path= finalTestcaseDir +"/"+name;
                    try {
                        ApiTestCaseModel apiTestCase = ApiTestCaseModel.load(path);
                        //arguments(apiTestCase) 生成一个参数传给apiTest；其中 name 给上面index 1 用的
                        testcases.add(arguments(apiTestCase,apiTestCase.name));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );
        return testcases;
    }
}
