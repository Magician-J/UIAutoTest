package testapp.wework.page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author jiaoyl
 * @date 2020/6/13 21:15
 */
// 测试用类，不是本项目所需
class huadongTest_bak {
   static AppiumDriver<MobileElement> driver;
   static WebDriverWait wait;
    @BeforeAll
    static void setUp() {
            //企业微信
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability("platformName", "android");
            desiredCapabilities.setCapability("deviceName", "127.0.0.1:7555");
            desiredCapabilities.setCapability("appPackage", "com.tencent.wework");
            desiredCapabilities.setCapability("appActivity", ".launch.WwMainActivity");
            desiredCapabilities.setCapability("noReset", "true");

            URL remoteUrl = null;
            try {
                remoteUrl = new URL("http://localhost:4723/wd/hub");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            driver = new AndroidDriver(remoteUrl, desiredCapabilities);
            driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
            wait = new WebDriverWait(driver,10);
        }
    @AfterAll
    static void tearDown() {

    }

    @Test
    public void swipe(){
        try{
            Thread.sleep(5000);
            driver.findElement(By.xpath("//*[@text='工作台']")).click();
//            Thread.sleep(5000);
            //获取手机屏幕宽，高
            int width= driver.manage().window().getSize().getWidth();
            int height=driver.manage().window().getSize().getHeight();
            Thread.sleep(6000);
            TouchAction touchAction = new TouchAction(driver);
            touchAction.press(PointOption.point((int)(width*0.5),(int)(height*0.9))).waitAction(WaitOptions.waitOptions(Duration.ofMillis(3000)))
                    .moveTo(PointOption.point((int)(width*0.5),(int)(height*0.2))).release().perform();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    //手势滑动
    public void touchSwipe(By by){
        TouchAction touchAction = new TouchAction(driver);
        //等待时间，等待页面渲染
        Duration duration = Duration.ofMillis(5000);
        driver.findElement(by).click();
        //避免界面未渲染出来就滑动，一下加等待时间
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //关于坐标建议使用相对位置，就是相对于评估的大小*比例，如(int)(width*0.5),(int)(height*0.9))
        touchAction.press(PointOption.point(248,389)).waitAction(WaitOptions.waitOptions(duration))
                .moveTo(PointOption.point(248,389)).waitAction(WaitOptions.waitOptions(duration))
                .moveTo(PointOption.point(248,389)).waitAction(WaitOptions.waitOptions(duration))
                .moveTo(PointOption.point(248,389)).waitAction(WaitOptions.waitOptions(duration))
                .moveTo(PointOption.point(248,389)).waitAction(WaitOptions.waitOptions(duration))
                .moveTo(PointOption.point(248,389)).waitAction(WaitOptions.waitOptions(duration))
                .release().perform();
    }
}