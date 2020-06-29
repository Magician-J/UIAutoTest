package test_framework;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.Test;
import org.omg.CORBA.ObjectHelper;
import org.openqa.selenium.By;

import java.io.File;
import java.io.FilenameFilter;
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
        //如果是配置级别的关键字
        if (map.containsKey("page")){
            String action = map.get("action").toString();
            String pageName = map.get("page").toString();
            //打印pom.name
            pages.forEach(pom->System.out.println(pom.name));
            pages.stream()
                    // filter 过滤文件；filter() 方法创建一个新的数组，新数组中的元素是通过检查指定数组中符合条件的所有元素。
                    .filter(pom-> pom.name.equals(pageName))
                    .findFirst()//返回集合的第一个对象
                    .get()//获取第一个对象的值的值
                    .methods.get(action).forEach(step->{
                action(step);
            });
        }else{
            //如果是自动化级别
            if (map.containsKey("click")){
                // click 取值出来是map类型（  - click: {id: search}）。
                HashMap<String ,Object> by = (HashMap<String ,Object>) map.get("click");
                click(by);
            }
            if (map.containsKey("sendKeys")){
                //由于定位符即为m自己，所以传值直接为m
                sendKeys(map);
            }
        }
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
    //loadpage 单页面 po
    public PageObjectModel loadpage(String path){
        //初始化mapper对象
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        PageObjectModel pom= null;
        try {
            //读取给定的资源路径
            pom = mapper.readValue(
                    // 当传入一个路径时用下面的getResourceAsStream
//                    BasePage.class.getResourceAsStream(path),
                    //以下传入的文件名
                    //File通过将给定路径名字符串转换成抽象路径名来创建一个新 File 实例
                    new File(path),
                    PageObjectModel.class //强行转成UIAuto模型
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pom;
    }

    //loadpages 多页面 po
    public void loadpages(String dir){
        //list()列出所有文件
        //FilenameFilter用来把符合要求的文件或目录返回
        Stream.of(new File(dir).list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.contains("_page");
            }
        })).forEach(path->{
            // path 是文件名，需拼装成路径
            path = dir + "/" + path;
            System.out.println(path);
            pages.add(loadpage(path));
        });
    }
}
