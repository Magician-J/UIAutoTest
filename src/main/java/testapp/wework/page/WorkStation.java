package testapp.wework.page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class WorkStation extends BasePage {
    public WorkStation(AppiumDriver<MobileElement> driver) {
        super(driver);
    }
    public ReportPage toReportpage(){
        swipe();
        click(By.id("d0c"));
        return new ReportPage(driver);
    }
}
