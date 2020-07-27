package test_service.framework;
/*
* 1. filter相当于过滤器、拦截器，在请求之前可以改掉请求信息，
* 2. 如URL，然后传给ctx调用链，调用链发送篡改后的请求，调用链获得response，可以进行篡改，篡改之后才会返回response
* 3. 创建全局的filters 每个case都会用到filter内容;   其他不需要filer的case会报错。
* 4. 增加条件判断：如果请求地址包含encode，使用filter；如果不需要使用filter，正常返回即可；这样不管是否加密都通用。
* 5. 本地启用python3服务，需切换到文件所在目录。
* */

import io.restassured.RestAssured;
import io.restassured.builder.ResponseBuilder;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Base64;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class EncodeTest2 {
    @BeforeAll
    static void befortAll(){
        // RestAssured.filters 增加全局性的filter代码
        RestAssured.filters((req , res, ctx)->{
            // 如果请求地址包含encode，使用filter
            if(req.getURI().contains("encode")) {
//            System.out.println(req.getURI());
//            System.out.println(res.getStatusCode());
                // 原始请求发送出去，结果存储在originResponse；返回的Response不具备set方法，无法修改body
                Response originResponse = ctx.next(req, res);

                // ResponseBuilder的作用主要是在Response基础上建设出来一个新的可以修改body的对象
                ResponseBuilder responBuilder = new ResponseBuilder().clone(originResponse);

                // 取出原始响应中的加密body
                String encodeBody = originResponse.getBody().asString();
                //调试代码
//            System.out.println("encodeBody");
//            System.out.println(encodeBody);

                //解密过程; trim() 去掉字符串的头尾空格
                byte[] decodeBody = Base64.getDecoder().decode(encodeBody.replace("\n", "").trim());
                // 调试代码
//            System.out.println("decodeBody");
//            System.out.println(new String(decodeBody));

                // Response 无法直接修改body，所以间接的通过ResponseBuilder构建
                responBuilder.setBody(decodeBody);
                //ResponseBuilder方法在最后通过build方法直接直接创建一个可以返回的不可修改的Response
                Response responseNew = responBuilder.build();
                return responseNew;
            }else {
                //如果不需要使用filter，正常返回即可
               return ctx.next(req,res);
            }

        });
    }
    @Test
    void ceshiren(){
        given().get("https://ceshiren.com/categories.json")
                .then()
                .body("category_list.categories[0].name",equalTo("霍格沃兹测试学院公众号"));
    }
    //运行如何脚本时，地址应该换成阿里云服务器的域名或者ip
    // 原始位加密文件
    @Test
    void rawJson(){
        given().get("http://aliyun:8000/raw.json")
                .then()
                .body("category_list.categories[0].name",equalTo("霍格沃兹测试学院公众号"));
    }

    // 访问加密文件base64
    @Test
    void encodeJson(){
        // req: 请求；res：响应；ctx：调用链，调用链发送篡改后的请求，调用链获得response
        given().get("http://aliyun:8000/encode.json")
                .then()
                .body("category_list.categories[0].name",equalTo("霍格沃兹测试学院公众号"));
    }

//    void  encodeJson2(){
//        given().filter((req , res , ctx)->{
//           Response originResponse = ctx.next(req,res);
//
//        });
//    }


}
