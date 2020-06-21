package testapp.wework.page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class WorkStation extends AppBasePage {
    public WorkStation(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public ReportPage toReportpage() {
        swipe();
//        click(byTest("汇报"));
        while (driver.findElements(byTest("汇报")).size() == 0) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        click(byTest("汇报"));
        return new ReportPage(driver);
    }
}