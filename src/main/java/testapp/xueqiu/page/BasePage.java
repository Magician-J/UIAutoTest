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
import java.util.ArrayList;
import java.util.List;
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
//        desiredCapabilities.setCapability("deviceName", "127.0.0.1:7555");
        desiredCapabilities.setCapability("deviceName", "emulator-5554");
        desiredCapabilities.setCapability("appPackage", "com.xueqiu.android");
        desiredCapabilities.setCapability("appActivity", ".view.WelcomeActivityAlias");
        desiredCapabilities.setCapability("noReset", "true");
        desiredCapabilities.setCapability("chromedriverExecutable","H:\\hogwarts\\dxb3\\tools\\chromedriver_win32\\chromedriver.exe");


        URL remoteUrl = null;
        try {
            remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
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
    // find element
    public MobileElement find(By by){

        return driver.findElement(by);
    }

    // find elements
    public List<MobileElement> findelements(By by){
        List<MobileElement> list = new ArrayList();
        list = driver.findElements(by);
        return list;
    }

    public void switchContext(String text){
        driver.getContextHandles().stream().forEach(ch->{
            if (ch.contains(text)){
                driver.context(ch.toString());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void switchWindow(String title){
        Object win =driver.getWindowHandles().stream().filter(window->{
            // 切换到页面判断页面信息中包含关键字，返回handle
            driver.switchTo().window(window);
            return driver.getPageSource().contains(title);
        }).toArray()[0];
        driver.switchTo().window(win.toString());
    }
}
