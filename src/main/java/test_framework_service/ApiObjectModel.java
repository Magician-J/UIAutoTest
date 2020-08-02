package test_framework_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/*
* 1、代表了每个api object
* */
public class ApiObjectModel {


    public String name;
    public HashMap<String,ApiObjectMethodModel> methods;

    // api object 支持从yaml中读取
   public static ApiObjectModel load(String path) throws IOException {
        // 导入包时需注意导入的是fasterxml.jackson.databind.ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
            return objectMapper.readValue(new File(path),ApiObjectModel.class);
    }


    // 运行这个api object中的某个封装方法
    public void run(ApiObjectMethodModel method){

        method.run();
    }

}
