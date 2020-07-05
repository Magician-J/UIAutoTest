package testapp.xueqiu.page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

/**
 * @author jiaoyl
 * @date 2020/7/4 20:54
 * 点击交易跳转页面
 * WebView 页面
 */
public class TradePage extends BasePage {

    public TradePage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }
    public TradePage registAStock(String mobile, String code){
        // 切换上线文到webview
        switchContext("WEBVIEW_");
        // 切换window
        switchWindow("实盘交易");
        click(By.cssSelector(".trade_home_info_3aI"));
        //加入等待机制，等到页面加载结束
        while (0!=findelements(By.cssSelector(".btn-submit")).size()){
            // 切换到注册页面
            switchWindow("平安证券 极速开户");
        }
        // 切换到注册页面
        switchWindow("平安证券 极速开户");
        // 注册
        sendKeys(By.cssSelector("#phone-number"),mobile);
        sendKeys(By.cssSelector("#code"),code);
        click(By.cssSelector(".btn-submit"));
        return this;
    }

}
