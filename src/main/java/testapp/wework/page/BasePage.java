package testapp.wework.page;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
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

    public void quit(){
        driver.quit();
    }
    public void click(By by){
        //移动端即时页面不可点击其实也是可点击的，故不用clickable
        wait.until(ExpectedConditions.visibilityOfElementLocated(by)).click();
    }

    // sendKeys
    public void sendKeys(By by, String content){
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        driver.findElement(by).sendKeys(content);
    }

    //find
    public MobileElement find(By by){

        return driver.findElement(by);
    }

    // byTest
    public By byTest(String text){
        return By.xpath("//*[@text='"+text+"']");
    }

}
