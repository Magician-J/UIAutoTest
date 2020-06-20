package testweb.weiwork.page;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * @author jiaoyl
 * @date 2020/5/31 23:00
 */
public class ContantPage extends BasePage {
    By addmember = By.linkText("添加成员");
    By username = By.name("username");
    //此处删除按钮的定位不建议使用linktest，会点击到列表页的删除按钮，报错。
    By delete= By.cssSelector(".js_del_member");
//    By delete= By.linkText("删除");
    public ContantPage(RemoteWebDriver driver){
        super(driver);
    }
    public ContantPage addMember(String username, String accid, String mobile){
//        //添加成员需加入显示等待，否则页面加载不完全可能报错;10超时时间，超过报异常
//        //可见
//        new WebDriverWait(MainPage.driver, 10).until(ExpectedConditions.visibilityOfElementLocated(addmember));
//        //可点击，两步判断稳妥
//        new WebDriverWait(MainPage.driver, 10).until(ExpectedConditions.elementToBeClickable(addmember));


        //技巧: 就算可点击，仍然有一定的概率是点击不成功的，使用显示等待特殊用法，
        //技巧1：findElements,才能调用size方法。一直点击知道页面展示点击的按钮
//        while (MainPage.driver.findElements(addmember).size()>0){
//            MainPage.driver.findElement(addmember).click();
//        }
        // 技巧2：一直点击输入username框，知道找到元素，size==0
        while (driver.findElements(this.username).size()==0){
//            MainPage.driver.findElement(addmember).click();
            click(addmember);
        }
//        driver.findElement(this.username).sendKeys(username);
//        driver.findElement(By.name("acctid")).sendKeys(accid);
//        driver.findElement(By.name("mobile")).sendKeys(mobile);
        sendKyes(this.username,username);
        sendKyes(By.name("acctid"),accid);
        sendKyes(By.name("mobile"),mobile);
//        driver.findElement(By.cssSelector(".js_btn_save")).click();
        click(By.cssSelector(".js_btn_save"));
        // return this 表示返回自己，即返回当前页面。
        return this;
    }
    //搜索是在通讯录页面，所以在此页面增加一个po
    public ContantPage serach(String keyword){
//        driver.findElement(By.id("memberSearchInput")).sendKeys(keyword);
        sendKyes(By.id("memberSearchInput"),keyword);
        //todo serach后要加一个停顿，否则搜索完直接删除会报错。
        //Duration.ofSeconds(10)报错，可以通过升级selenium版本解决
//        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(delete));
        return this;
    }
    public String getName(){
        return driver.findElement(By.cssSelector(".member_display_cover_detail_name")).getText();
    }
    //删除用户
    public ContantPage Delete(){
        click(delete);
        click(By.linkText("确认"));
        click(By.id("clearMemberSearchInput"));
//        MainPage.driver.findElement(delete).click();
//        MainPage.driver.findElement(By.linkText("确认")).click();
//        MainPage.driver.findElement(By.id("clearMemberSearchInput")).click();
        return this;
    }
    public ContantPage imprtFromFile(String path){
        //todo
        System.out.println(path);

//        String path_utf="";
//        try {
//            path_utf=URLDecoder.decode(path.getFile(), "UTF-8");
//            System.out.println(path_utf);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }

        click(By.cssSelector(".ww_operationBar:nth-child(1) .ww_btn_PartDropdown_left"));
        click(By.linkText("文件导入"));
        //windows使用demo代码，路径前面多了一个/ 导致报错。MAC无此问题,so写死路径（虽然不推荐）
        upload(By.name("file"), "H:/hogwarts/testproject/PageObjectTest/target/classes/contactimport.xlsx");
//        upload(By.name("file"), path_utf);
        //导入这块直接send文件，没有可视化页面，故不能用click方法
//        click(By.name("file"));
//        sendKyes(By.name("file"),"C:\\fakepath\\contactsImport.xlsx");
        click(By.cssSelector("#submit_csv"));
        click(By.linkText("前往查看"));
        return this;
    }


}
