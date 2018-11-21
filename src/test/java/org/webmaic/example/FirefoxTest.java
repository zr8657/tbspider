package org.webmaic.example;

import org.apache.tomcat.jni.Time;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class FirefoxTest {

    public static void main(String[] args) {
        //路径配置
        System.setProperty("webdriver.firefox.bin","D:\\Mozilla Firefox\\firefox.exe");

        //初始化一个火狐浏览器实例
        WebDriver driver = new FirefoxDriver();
        //最大化窗口
        driver.manage().window().maximize();
        // get()打开一个站点
        driver.get("https://www.baidu.com");
        //getTitle()获取当前页面title的值
        System.out.println("当前打开页面的标题是： "+ driver.getTitle());
        try{
            Thread.sleep(10000);
        }catch (Exception e){
            e.printStackTrace();
        }
        //关闭并退出浏览器
        driver.quit();

    }
}
