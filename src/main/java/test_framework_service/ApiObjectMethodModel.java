package test_framework_service;


import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;


/*
* 代表了一个单一的http接口
* */
public class ApiObjectMethodModel {
    public String method="get";
    public String url;
    public HashMap<String,Object> query;
    public String save;
    public HashMap<String,Object> json;
    public String get;
    public String post;


    public Response run() {

        // 多环境支持
//        String url = "";

        // 兼容 get：url 写法
        if (get!=null){
           return given().log().all().queryParams(query).get(get)
                   .then().log().all().extract().response();
            // 多环境支持
//            url = get;
//            method = "get";
        }

        if (null!=post){
            return given().log().all().queryParams(query).post(post)
                    .then().log().all().extract().response();

            // 多环境支持
//            url = post;
//            method = "post";

        }

//        url=url.replaceAll("domain", "ip");
        //兼容 method：get   get：url 写法
        return given().log().all().queryParams(query).request(method,url)
                .then().log().all().extract().response();
    }
}
