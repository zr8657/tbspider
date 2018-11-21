package org.webmaic.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

public class ZhiHuUserTest {

    public static void main(String[] args) {
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
        //名称
        String userName = driver.findElement(By.xpath("//*[@id=\"ProfileHeader\"]/div/div[2]/div/div[2]/div[1]/h1/span[1]")).getText();
        //title
        String userTitle = driver.findElement(By.xpath("//*[@id=\"ProfileHeader\"]/div/div[2]/div/div[2]/div[1]/h1/span[2]")).getText();
        //公司
        String company = driver.findElement(By.xpath("//*[@id=\"ProfileHeader\"]/div/div[2]/div/div[2]/div[2]/div/div/div[1]")).getText();
        //性别
        String sex = driver.findElement(By.xpath("//*[@id=\"ProfileHeader\"]/div/div[2]/div/div[2]/div[2]/div/div/div[2]/div")).getAttribute("class");
        if (sex.contains("female")) {
            sex = "男";
        } else {
            sex = "女";
        }
        System.out.println("姓名：" + userName + " title：" + userTitle + " 公司：" + company + " 性别：" + sex);

        //点击按钮
        driver.findElement(By.xpath("//*[@id=\"ProfileHeader\"]/div/div[2]/div/div[2]/div[3]/button")).click();
        try {
            //强制等待等7秒
            Thread.sleep(7000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //居住地
        String live = driver.findElement(By.xpath("//*[@id=\"ProfileHeader\"]/div/div[2]/div/div[2]/div[2]/div/div/div[1]/div")).getText();
        //行业
        String trade = driver.findElement(By.xpath("//*[@id=\"ProfileHeader\"]/div/div[2]/div/div[2]/div[2]/div/div/div[2]/div")).getText();
        //职业经历
        String jobHistory = driver.findElement(By.xpath("//*[@id=\"ProfileHeader\"]/div/div[2]/div/div[2]/div[2]/div/div/div[3]/div")).getText();
        //个人简介
        String individualResume = driver.findElement(By.xpath("//*[@id=\"ProfileHeader\"]/div/div[2]/div/div[2]/div[2]/div/div/div[4]/div")).getText();
        System.out.println("居住地:" + live + " 行业: " + trade + " 职业经历: " + jobHistory + " 个人简介: " + individualResume);

        //关闭并退出浏览器
        driver.quit();

    }
}
