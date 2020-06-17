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
    void load() throws JsonProcessingException {
       UIAuto uiAuto= basePage.load("/test_framework/uiauto.yaml");
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(uiAuto));
    }
}