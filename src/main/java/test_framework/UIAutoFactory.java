package test_framework;

import testapp.wework.page.AppBasePage;
import testweb.weiwork.page.WebBasePage;

/**
 * @author jiaoyl
 * @date 2020/6/21 17:04
 */
public class UIAutoFactory {
    // 解决驱动引擎：判断driver类型，返回父类BasePage
    public static BasePage creat(String driverName){
        if (driverName.equals("web") || driverName.equals("selenium")){
            return new WebBasePage();
        }
        if (driverName.equals("app") || driverName.equals("appium")) {
            return new AppBasePage();
        }
        //占位
        if (driverName.equals("uiautomator")){
            //             return new AppBasePage();
        }
        if (driverName.equals("atx")){
            //             return new AppBasePage();
        }
        if (driverName.equals("macaca")){
            //             return new AppBasePage();
        }

        return null;
    }
}
