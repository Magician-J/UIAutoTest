package testapp.wework.page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jiaoyl
 * @date 2020/6/11 21:08
 */
public class 待办Page extends BasePage {
    private By add=By.id("gym");
    private By title=By.id("b2k");
    private By save=By.xpath("//*[@text='保存']");
    private By list=By.id("gw9");

    public 待办Page(AppiumDriver<MobileElement> driver) {
        super(driver);
    }
    // 内容：content   提醒人：user
    public 待办Page 添加待办(String content){
        click(add);
        find(title).sendKeys(content);
        click(save);
        return this;
    }
    //todo:删除待办


    public List<String> 获取待办(){
        //findElements 不能缺少s
        return driver.findElements(list).stream().map(x->x.getText()).collect(Collectors.toList());
    }

}
