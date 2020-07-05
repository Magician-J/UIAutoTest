package testapp.xueqiu.page;

import org.openqa.selenium.By;

/**
 * @author jiaoyl
 * @date 2020/6/7 0:10
 */
public class MainPage extends BasePage {

    // 到搜索页面
    public SearchPage toSearch(){
        click(By.id("home_search"));
//        MobileElement el1 = (MobileElement) driver.findElementById("com.xueqiu.android:id/home_search");
//        el1.click();
        return new SearchPage(driver);

    }
    //
    public void toStock(){
    }
    // 到交易页面
    public TradePage toTradePage(){
        click(By.xpath("//*[@text='交易']"));
        return new TradePage(driver);
    }
}
