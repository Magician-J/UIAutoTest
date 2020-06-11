package testapp.xueqiu.page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jiaoyl
 * @date 2020/6/7 15:58
 */
public class SearchPage extends BasePage{
    private By nameLocator = By.id("name");

    public SearchPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public SearchPage search(String keyword){
        //判断搜索到的元素出现
        do {
            sendKeys(By.id("search_input_text"),keyword);
            System.out.println("secdkeys");

        }while(driver.findElements(nameLocator).size()<=0);

//        MobileElement el2 = driver.findElementById("com.xueqiu.android:id/search_input_text");
//        el2.sendKeys(keyword);
        return this;
    }

    public List<String> getSearchList(){
        List<String> namelist= new ArrayList<String>();
        //todo Arrays.asList 方法了解
        //循环搜索到的列表
//        for (Object element:driver.findElements(nameLocator)){
//            //强转为WebElement格式，为了使用getText（）方法
//            namelist.add(((WebElement)element).getText());
//        }
        // stream lamda 优化,流式表达式
        driver.findElements(nameLocator).forEach(element ->namelist.add(element.getText()));
        return namelist;
}
    public double getPrice(){
        click(nameLocator);
        return Double.valueOf(find(By.id("current_price")).getText());
    }
}
