package testweb.weiwork.page;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test_framework.BasePage;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author jiaoyl
 * @date 2020/6/3 19:44
 */
public class WebBasePage extends BasePage {
    RemoteWebDriver driver;
    WebDriverWait wait;

    public WebBasePage() {
        driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait=new WebDriverWait(driver, 10);
    }

    public WebBasePage(RemoteWebDriver driver) {
        this.driver = driver;
        wait=new WebDriverWait(driver,10);

    }


    public void quit() {
        driver.quit();
    }


    public void click(By by){
        //todo: 异常处理
        wait.until(ExpectedConditions.elementToBeClickable(by));
        driver.findElement(by).click();
    }
    public void sendKyes(By by, String content){
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        driver.findElement(by).sendKeys(content);
    }
    public void upload(By by, String path){
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        driver.findElement(by).sendKeys(path);
    }

    //重载
    //用意：
    @Override
    public void click(HashMap<String, Object> map) {
        super.click(map);
        String key = (String) map.keySet().toArray()[0];
        String value= (String) map.values().toArray()[0];
        By by = null;
        // toLowerCase() 强制转为小写
        if (key.toLowerCase().equals("id")){
            by = By.id(value);
        }
        if (key.toLowerCase().equals("LinkText".toLowerCase())){
            by = By.linkText(value);
        }
        if (key.toLowerCase().equals("partialLinkText".toLowerCase())){
            //partialLinkText 模糊匹配
            by = By.partialLinkText(value);
        }
        click(by);
    }

    @Override
    public void action(HashMap<String, Object> map) {
        super.action(map);
        // 个别步骤不包含action，加判断
        if (map.containsKey("action")) {
            String action = map.get("action").toString().toLowerCase();
            if (action.equals("get")) {
                driver.get(map.get("url").toString().toLowerCase());
            }
            // todo:课间作业：完成get attribute 获取文本 的封装
        }
    }
}
