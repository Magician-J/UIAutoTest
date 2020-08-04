package test_framework_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;


/*
* 1、代表的是测试用例，提供读取测试用例、执行功能
* */
public class ApiTestCaseModel {
    public String name="";
    public String description="";
    public List<HashMap<String,Object>> steps;

    // 加载一个yaml文件，并转成测试用例模型
    public static ApiTestCaseModel load(String path) throws IOException {
        // 导入包时需注意导入的是fasterxml.jackson.databind.ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        return objectMapper.readValue(new File(path),ApiTestCaseModel.class);
    }


    // 借助于baseApi，去运行对应的测试用例，主要是运行其中的steps，将来可能会有断言。
    public void run(BaseApi baseApi) {
        //第一种方法

        steps.stream().forEach(step -> {
//            //返回此映射中包含的映射关系的 Set 视图, Map.Entry表示映射关系;
//            //迭代后可以e.getKey()，e.getValue()取key和value。返回的是Entry接口
//            step.entrySet().forEach(entry->{
//                baseApi.run(entry.getKey(), String.valueOf(entry.getValue()));
//            });
//        });

            baseApi.run(step.get("api").toString(), step.get("action").toString());
            // 断言,遍历steps中的step

//            System.out.println("====="+step.get("actual"));

            if(step.get("actual")!= null){

                assertAll(
                        ()->{
                            if (step.get("matcher").equals("equalTo")){
                                assertThat(step.get("actual"),equalTo(step.get("expect")));
                            }
                        }
                );
            }



        });
    }

}
