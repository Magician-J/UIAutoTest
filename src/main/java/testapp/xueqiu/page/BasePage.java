package testapp.xueqiu.page;
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

    public BasePage(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver,10);
    }

    public BasePage() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("deviceName", "127.0.0.1:7555");
        desiredCapabilities.setCapability("appPackage", "com.xueqiu.android");
        desiredCapabilities.setCapability("appActivity", ".common.MainActivity");
        desiredCapabilities.setCapability("noReset", "true");

        URL remoteUrl = null;
        try {
            remoteUrl = new URL("http://localhost:4723/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,10);
    }

    public void quit(){
        driver.quit();
    }
    public void click(By by){
        //移动端即时页面不可点击其实也是可点击的，故不用clickable
//        wait.until(ExpectedConditions.elementToBeClickable(by));
        driver.findElement(by).click();
    }
    public void sendKeys(By by, String content){
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        driver.findElement(by).sendKeys(content);
    }
    public MobileElement find(By by){
        return driver.findElement(by);
    }

}
