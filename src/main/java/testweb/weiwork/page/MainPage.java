package testweb.weiwork.page;

import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

/**
 * @author jiaoyl
 * @date 2020/5/31 22:58
 */
public class MainPage extends WebBasePage {
//    public MainPage(){
//        super();
//    }
    public ContantPage toContact(){
        //todo
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        Login login = new Login();
        driver.get("https://work.weixin.qq.com/wework_admin/frame");
        login.loadCookie(driver);
        driver.get("https://work.weixin.qq.com/wework_admin/frame");
        driver.findElement(By.cssSelector("#menu_contacts ")).click();

        return new ContantPage(driver);
    }
}
