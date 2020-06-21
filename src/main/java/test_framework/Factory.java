package test_framework;

import testapp.wework.page.AppBasePage;
import testweb.weiwork.page.WebBasePage;

/**
 * @author jiaoyl
 * @date 2020/6/21 17:04
 */
public class Factory  {
    // 解决驱动引擎：判断driver类型，返回父类BasePage
    public static BasePage creat(String driverName){
        if (driverName=="web" || driverName=="selenium"){
            return new WebBasePage();
        }
        if (driverName=="app" || driverName=="appium") {
            return new AppBasePage();
        }
        //占位
        if (driverName=="uiautomator"){
            //             return new AppBasePage();
        }
        if (driverName=="atx"){
            //             return new AppBasePage();
        }
        if (driverName=="macaca"){
            //             return new AppBasePage();
        }

        return null;
    }
}
