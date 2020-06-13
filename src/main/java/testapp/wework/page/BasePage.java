package testapp.wework.page;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * @author jiaoyl
 * @date 2020/6/4 23:20
 */
public class BasePage {
//     AndroidDriver<MobileElement> driver;
     AppiumDriver<MobileElement> driver;
     WebDriverWait wait;
     String packageName;
     String activityName;
     int timeout=60;

    public BasePage(String packageName, String activityName) {
        this.packageName = packageName;
        this.activityName = activityName;
        startApp(this.packageName,this.activityName);
    }

    public BasePage(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver,timeout);
    }


    public void startApp(String packageName, String activityName) {
        //企业微信
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("deviceName", "127.0.0.1:7555");
        desiredCapabilities.setCapability("appPackage", packageName);
        desiredCapabilities.setCapability("appActivity", activityName);
        desiredCapabilities.setCapability("noReset", "true");

        URL remoteUrl = null;
        try {
            remoteUrl = new URL("http://localhost:4723/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,timeout);
    }
    //quit
    public void quit(){

        driver.quit();
    }
    //click
    public void click(By by){
        //移动端即时页面不可点击其实也是可点击的，故不用clickable
        wait.until(ExpectedConditions.visibilityOfElementLocated(by)).click();
//        while (driver.findElements(by).size()==0){
//            click(by);
//        }
    }

    // sendKeys
    public void sendKeys(By by, String content){
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        driver.findElement(by).sendKeys(content);
    }

    //find
    public MobileElement find(By by){
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return driver.findElement(by);
    }

    // byTest
    public By byTest(String text){
        return By.xpath("//*[@text='"+text+"']");
    }

    //定位从左上角坐标为[0,0]，往下越来越大。
    // 向上滑动
    public void swipe(){
        //获取手机屏幕宽，高
       int width= driver.manage().window().getSize().getWidth();
       int height=driver.manage().window().getSize().getHeight();
        try {
            //等待页面加载时间
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TouchAction touchAction = new TouchAction(driver);
        touchAction.press(PointOption.point((int)(width*0.5),(int)(height*0.9))).waitAction(WaitOptions.waitOptions(Duration.ofMillis(3000)))
                .moveTo(PointOption.point((int)(width*0.5),(int)(height*0.3))).release().perform();

    }

}
