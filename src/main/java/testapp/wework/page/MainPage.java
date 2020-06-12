package testapp.wework.page;

import org.openqa.selenium.By;

public class MainPage extends BasePage {
    public MainPage() {

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
        return new 待办Page(driver);
    }

    // 跳转工作台
    public WorkStation workStation(){
        click(By.id("dsp"));
        return  new WorkStation(driver);
    }

}
