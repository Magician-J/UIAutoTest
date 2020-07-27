package test_service.framework;
/*
* filter相当于过滤器、拦截器，在请求之前可以改掉请求信息，
* 如URL，然后传给ctx调用链，调用链发送篡改后的请求，调用链获得response，可以进行篡改，篡改之后才会返回response
*
* */

import io.restassured.builder.ResponseBuilder;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.junit.jupiter.api.Test;
import java.util.Base64;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class EncodeTest {
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
        given().filter((req ,res, ctx)->{
            System.out.println(req.getURI());
            System.out.println(res.getStatusCode());
            // 原始请求发送出去，结果存储在originResponse；返回的Response不具备set方法，无法修改body
            Response originResponse = ctx.next(req,res);

            // ResponseBuilder的作用主要是在Response基础上建设出来一个新的可以修改body的对象
            ResponseBuilder responBuilder = new ResponseBuilder().clone(originResponse);

            // 取出原始响应中的加密body
            String encodeBody = originResponse.getBody().asString();
            //调试代码
            System.out.println("encodeBody");
            System.out.println(encodeBody);

            //解密过程; trim() 去掉字符串的头尾空格
            byte[] decodeBody = Base64.getDecoder().decode(encodeBody.replace("\n","").trim());
            // 调试代码
            System.out.println("decodeBody");
            System.out.println(new String(decodeBody));

            // Response 无法直接修改body，所以间接的通过ResponseBuilder构建
            responBuilder.setBody(decodeBody);
            //ResponseBuilder方法在最后通过build方法直接直接创建一个可以返回的不可修改的Response
            Response responseNew = responBuilder.build();
            return responseNew;

        }).get("http://aliyun:8000/encode.json")
                .then()
                .body("category_list.categories[0].name",equalTo("霍格沃兹测试学院公众号"));
    }

    @Test
    // 方法1：双层Base64 加密文件--解密
    void encodeJson1(){
        // req: 请求；res：响应；ctx：调用链，调用链发送篡改后的请求，调用链获得response
        given().filter((req ,res, ctx)->{
            System.out.println(req.getURI());
            System.out.println(res.getStatusCode());
            // 原始请求发送出去，结果存储在originResponse；返回的Response不具备set方法，无法修改body
            Response originResponse = ctx.next(req,res);

            // ResponseBuilder的作用主要是在Response基础上建设出来一个新的可以修改body的对象
            ResponseBuilder responBuilder = new ResponseBuilder().clone(originResponse);

            // 取出原始响应中的加密body
            String encodeBody = originResponse.getBody().asString();
            //调试代码
//            System.out.println("encodeBody");
//            System.out.println(encodeBody);

            //解密过程; trim() 去掉字符串的头尾空格;decode 方法的返回值类型位byte
            byte[] decodeBody = Base64.getDecoder().decode(encodeBody.replace("\n","").trim());
            //new String()的方法，因为Base64加解密是一种转换编码格式的原理；newString打印的数据，toString打印的是内存中的地址
            byte[] decodeBody1 = Base64.getDecoder().decode(new String(decodeBody).replace("\n","").trim());
            // 调试代码
            System.out.println("decodeBody1");
            System.out.println(new String(decodeBody1));

            // Response 无法直接修改body，所以间接的通过ResponseBuilder构建
            responBuilder.setBody(decodeBody1);
            //ResponseBuilder方法在最后通过build方法直接直接创建一个可以返回的不可修改的Response
            Response responseNew = responBuilder.build();
            return responseNew;

        }).get("http://aliyun:8000/encode2.json")
                .then()
                .body("category_list.categories[0].name",equalTo("霍格沃兹测试学院公众号"));
    }



    // 双层解密，方法2： ，定义公共方法，然后调用两次。
    @Test
    void ceshiren_encode2() {
        given()
                .filter(base64Decode())
                .filter(base64Decode())
                .get("http://aliyun:8000/encode2.json")
                .then()
                .body("category_list.categories[0].name", equalTo("霍格沃兹测试学院公众号"));

    }

    // 封装公用解密方法
    public static Filter base64Decode() {
        return new Filter() {
            @Override
            public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
                Response originalResponse = ctx.next(requestSpec, responseSpec);
                Response newResponse = originalResponse;

                if(requestSpec.getURI().contains("encode")) {
                    ResponseBuilder responseBuilder = new ResponseBuilder().clone(originalResponse);

                    responseBuilder.setBody(Base64.getDecoder().decode(originalResponse.getBody().asString().replace("\n","")));
                    newResponse = responseBuilder.build();
                }

                return newResponse;
            }
        };
    }
}



