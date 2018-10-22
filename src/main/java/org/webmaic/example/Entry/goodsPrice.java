package org.webmaic.example.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.webmaic.example.util.BaiduEntryUtil;

import java.io.File;
import java.io.IOException;


/**
 * chromeDriver是谷歌的浏览器驱动，用来适配Selenium,有图形页面存在，在调试爬虫下载运行的功能的时候会相对方便
 * @author zr8657
 * @date 10.22.2018
 */
public class goodsPrice {


    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.chrome.driver","src\\main\\resources\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver = BaiduEntryUtil.baiduJumpTmall(driver);

    }



}