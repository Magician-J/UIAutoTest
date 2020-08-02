package test_framework_service;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class ApiObjectModelTest {

    private static ApiObjectModel api;

    @BeforeAll
    public static void beforeAll(){
        try {
            api = ApiObjectModel.load("src/main/resources/test_framework_service/api/wework_api.yaml");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    void load() {
        assertThat(api.name,equalTo("wework"));
    }

    @Test
    void run() {
        Response response =api.methods.get("get_token").run();
        response.then().statusCode(200);
    }
}