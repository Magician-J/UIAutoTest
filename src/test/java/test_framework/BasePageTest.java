package test_framework;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * @author jiaoyl
 * @date 2020/6/17 20:37
 */
class BasePageTest {
    private static BasePage basePage;
    @BeforeAll
    static void beforeAll(){
         basePage = new BasePage();
    }

    @Test
    void click() {
    }

    @Test
    void find() {
    }

    @Test
    void send() {
    }

    @Test
    void getText() {
    }

    @Test
    void run() {
        UIAuto uiAuto= basePage.load("/test_framework/uiauto.yaml");
        basePage.run(uiAuto);
    }

    @Test
    void runPom(){
        basePage.loadpages("src/main/resources/test_framework/");
        UIAuto uiAuto= basePage.load("/test_framework/webuiauto3.yaml");
        basePage.run(uiAuto);
    }

    @Test
    void load() throws JsonProcessingException {
        //"/text.txt": 以这种模式传入的参数，将会从对应的resource下查找文件。
       UIAuto uiAuto= basePage.load("/test_framework/uiauto.yaml");
        ObjectMapper mapper = new ObjectMapper();
        //调试使用，打印uiAuto对象
        System.out.println(mapper.writeValueAsString(uiAuto));
    }
}