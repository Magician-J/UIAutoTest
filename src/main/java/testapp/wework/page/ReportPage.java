package testapp.wework.page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class ReportPage extends AppBasePage {

    public ReportPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }
    public void  add_Day_Report(String content){
        click(byTest("日报"));
        find(By.xpath("//*/android.widget.EditText[1]")).clear();
        find(By.xpath("//*/android.widget.EditText[1]")).sendKeys(content);
        swipe();
        find(By.xpath("//*[@content-desc='提交']")).click();
    }
    public void week_Report_Page(){

    }
}
