package testapp.wework.page;

import org.openqa.selenium.By;

public class Wework extends BasePage {
    public Wework() {
        super("com.tencent.wework", ".launch.WwMainActivity");
    }
    public 日程Page  日程(){
        click(By.id("ag9"));

        return  new 日程Page(driver);
    }
}
