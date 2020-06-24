package test_framework;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.Test;
import org.omg.CORBA.ObjectHelper;
import org.openqa.selenium.By;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;


/**
 * @author jiaoyl
 * @date 2020/6/17 20:17
 */
//自动化领域建模
public class BasePage {
    List<PageObjectModel> pages = new ArrayList<>();
    public void click(HashMap<String ,Object> map){
//        driver.findElement(By.id(""));
        System.out.println("click");
        System.out.println(map);
    }
    public void sendKeys(HashMap<String, Object> map){
        System.out.println("sendKeys");
        System.out.println(map);
    }
    //定义通用action
    public void action(HashMap<String,Object> map){
        System.out.println("action");
        System.out.println(map);
        String action = map.get("action").toString().toLowerCase();

    }
    public void find(){

    }
    public void send(){

    }
    public void getText(){

    }
    public void run(UIAuto uiAuto){
        //m的类型为map，steps 为List类型，包含多个map
        uiAuto.steps.stream().forEach(m->{
            //keySet()：map中返回键值
//            if (m.keySet().contains("click")){
//                //click 指定了参数类型
//                click((HashMap<String, Object>)(m.get("click")));
//            }
//            System.out.println(m);
            // 对key值判断。
            if (m.containsKey("click")){
                // click 取值出来是map类型（  - click: {id: search}），和click方法输入类型一致。
                HashMap<String ,Object> by = (HashMap<String ,Object>) m.get("click");
                click(by);
            }
            if (m.containsKey("sendKeys")){
                //由于定位符即为m自己，所以传值直接为m
                sendKeys(m);
            }
            //通用action,给一些未知的操作
            if (m.containsKey("action")){
                action(m);
            }
//            if (m.containsKey("page")){
//                action(m);
//            }

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
    //loadpage 单页面 yaml文件
    public PageObjectModel loadpage(String path){
        //初始化mapper对象
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        PageObjectModel pom= null;
        try {
            //读取给定的资源路径
            pom = mapper.readValue(
                    BasePage.class.getResourceAsStream(path),
                    PageObjectModel.class //强行转成UIAuto模型
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pom;
    }

    //loadpages 多页面 yaml文件
    public void loadpages(String dir){
        Stream.of(new File(dir).list()).forEach(path->{
            pages.add(loadpage(path));
        });
    }


}
