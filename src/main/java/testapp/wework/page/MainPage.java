package testapp.wework.page;

import org.openqa.selenium.By;

public class MainPage extends AppBasePage {
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
        click(byTest("工作台"));
        //此处不能用while如下判断否则报错
//        while (driver.findElements(By.xpath("//*[@text='工作台']")).size()==0){
//            driver.findElement(By.xpath("//*[@text='工作台']")).click();
//        }
        return  new WorkStation(driver);
    }

}
