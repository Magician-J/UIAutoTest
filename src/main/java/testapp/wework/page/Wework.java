package testapp.wework.page;

import org.openqa.selenium.By;

public class Wework extends BasePage {
    public Wework() {

        super("com.tencent.wework", ".launch.WwMainActivity");
    }
    //跳转日程页
    public 日程Page  日程(){
        click(By.xpath("//*[@text='日程']"));
        return  new 日程Page(driver);
    }
    //跳转待办页
    public 待办Page 待办(){
        click(By.id("gwu"));
//        while (driver.findElements(By.id("gwu")).size()==0){
//            click(By.id("gwu"));
//        }

        return new 待办Page(driver);
    }

}
