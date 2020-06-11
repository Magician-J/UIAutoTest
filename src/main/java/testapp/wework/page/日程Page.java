package testapp.wework.page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

public class 日程Page extends BasePage {
    //初始化By变量
    //多版本app，多平台app，定位符通常是有差别的
    private By add = By.id("gym");
    private By taskName = By.id("b2k");
    private By save = byTest("保存");
    private By taskList = By.id("gg_");

    public 日程Page(AppiumDriver<MobileElement> driver) {

        super(driver);
    }

    public 日程Page 添加(String name,String time){
        click(add);
        sendKeys(taskName,name);
        click(save);
//        driver.findElement(save).click();
        return this;
    }
    public List<String> 获取日程(String day){
        if (day!=null){
            //todo
        }
        return driver.findElements(taskList).stream().map(x->x.getText()).collect(Collectors.toList());

    }
}
