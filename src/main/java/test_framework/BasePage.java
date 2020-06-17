package test_framework;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.openqa.selenium.By;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author jiaoyl
 * @date 2020/6/17 20:17
 */
//自动化领域建模
public class BasePage {
    public void click(HashMap<String ,Object> map){
//        driver.findElement(By.id(""));
        System.out.println(map);
    }
    public void find(){

    }
    public void send(){

    }
    public void getText(){

    }

    public void run(UIAuto uiAuto){
        uiAuto.steps.stream().forEach(m->{
            if (m.keySet().contains("click")){
                click((HashMap<String, Object>)(m.get("click")));
            }
        });
    }

    public UIAuto load(String path){
        //初始化mapper对象
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        UIAuto uiAuto= null;
        try {
            //读取给定的资源路径
            uiAuto = mapper.readValue(
                     BasePage.class.getResourceAsStream(path),
                     UIAuto.class //强行转成UIAuto模型
             );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uiAuto;


    }


}
