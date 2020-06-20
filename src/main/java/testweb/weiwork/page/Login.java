package testweb.weiwork.page;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;

/**
 * @author jiaoyl
 * @date 2020/5/30 11:18
 */
public class Login {
     WebDriver driver;

    @Test
    public void testSelenium(){
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
        driver = new ChromeDriver(options);
        //先将cookie保存到cookie.txt，使用浏览器复用方式,后面就可以用来loadcookie
        //add cookie前后都要get一下网页才行
        driver.get("https://work.weixin.qq.com/wework_admin/frame");
        saveCookie(driver);
//        loadCookie(driver);
        driver.get("https://work.weixin.qq.com/wework_admin/frame");
        try {
            Thread.sleep(15000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void loadCookie(WebDriver driver) {
        // cookie处理
        // 每次只获取一条cookie处
        try {
            FileReader fileReader = new FileReader("cookie.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while((line=bufferedReader.readLine()) !=null){
                // StringTokenizer可以分解字符串，返回的是原字符串的一个子字符串
                StringTokenizer stringTokenizer = new StringTokenizer(line,";");
                // nextToken()从第一个开始取
                String name = stringTokenizer.nextToken();
                String value = stringTokenizer.nextToken();
                String domain = stringTokenizer.nextToken();
                String path = stringTokenizer.nextToken();
                Date expiry = null;
                String dt = stringTokenizer.nextToken();
                if (!dt.equals("null")){
                    SimpleDateFormat sdf = new SimpleDateFormat("EEE MMMM dd HH:mm:ss z yyyy",Locale.ENGLISH);
                    //根据cookie中的过期日期来定义格式  Mon Jan 18 08:00:00 CST 2038   设置为英文格式。
                    // 把String类型转成日期date
                    expiry = sdf.parse(dt);
                }
                //String 转 布尔型
                Boolean isSecure = Boolean.parseBoolean(stringTokenizer.nextToken());
                Cookie cookie = new Cookie(name, value, domain, path, expiry, isSecure);
                driver.manage().addCookie(cookie);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void saveCookie(WebDriver driver){
        try {
            FileWriter fileWriter = new FileWriter("cookie.txt");
            // BufferedWriter存入缓冲中，使用缓冲的目的是减少io读写，降低资源使用
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Cookie cookie : driver.manage().getCookies()) {
                // 获取cookie所有字段，存入到文件中
                bufferedWriter.write(cookie.getName() + ";" +
                        cookie.getValue() + ";" +
                        cookie.getDomain() + ";" +
                        cookie.getPath() + ";" +
                        cookie.getExpiry() + ";"+
                        cookie.isSecure());
                //写完一行追加换行
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
