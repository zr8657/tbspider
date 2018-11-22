package org.webmaic.example.service;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ZhiHuUserInfoService {

    public static void main(String[] args) {
        getUserInfo("");
    }



    public static void getUserInfo(String userUrl){
        //路径配置
        System.setProperty("webdriver.firefox.bin", "D:\\Mozilla Firefox\\firefox.exe");

        //初始化一个火狐浏览器实例
        WebDriver driver = new FirefoxDriver();
        //最大化窗口
        driver.manage().window().maximize();
        // get()打开一个站点
        driver.get("https://www.zhihu.com/people/se-shang-fang-bu-bo-shi/activities");
        //getTitle()获取当前页面title的值
        System.out.println("当前打开页面的标题是： " + driver.getTitle());
        try {
            //强制等待等7秒
            Thread.sleep(7000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String html = driver.getPageSource();
        System.out.println(html);
        //关闭并退出浏览器
        driver.quit();
    }
}
