package testapp.DemoTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * @author jiaoyl
 * @date 2020/7/3 23:33
 */
public class WebView1 {
    private static AppiumDriver<MobileElement> driver;
    private static WebDriverWait wait;

    @BeforeAll
    public static void bf(){
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("deviceName", "emulator-5554");
        desiredCapabilities.setCapability("appPackage", "com.xueqiu.android");
        desiredCapabilities.setCapability("appActivity", ".view.WelcomeActivityAlias");
//        desiredCapabilities.setCapability("appActivity", ".common.MainActivity");
        desiredCapabilities.setCapability("noReset", "true");
        // 指定chrome driver版本，解决版本不匹配问题
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
        driver.findElement(By.xpath("//*[@text='交易']"));
    }
    @Test
    public void webview_native() {
        driver.findElement(By.xpath("//*[@text='交易']")).click();
        driver.findElement(By.xpath("//*[@text='基金开户']")).click();
    }
    @Test
    public void webview_web() throws InterruptedException {
        driver.findElement(By.xpath("//*[@text='交易']")).click();
        for (int i=0;i<5;i++) {
            //打印上下文
            driver.getContextHandles().forEach(context->System.out.println(context.toString()));
//            System.out.println(driver.getContextHandles());
            Thread.sleep(2000);
        }
        //切换到webview
        driver.context(driver.getContextHandles().toArray()[1].toString());
        driver.getWindowHandles().forEach(window->{
            System.out.println(window);
            //打印窗口title
            System.out.println(driver.getTitle());
            driver.switchTo().window(window);
            System.out.println(driver.getPageSource());
                }
        );
//        driver.getWindowHandles().stream().filter(win->{
//            driver.switchTo().window(win);
//            return driver.getTitle().contains("实盘交易");
//        });
        //切换到最后一个窗口
        Object[] array = driver.getWindowHandles().toArray();
        driver.switchTo().window(array[array.length - 1].toString());

        driver.findElement(By.cssSelector(".trade_home_info_3aI"));
    }
    @AfterAll
    public static void tearDown() throws InterruptedException {
        Thread.sleep(20000);
        driver.quit();
    }
}
