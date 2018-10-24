package org.webmaic.example.EntrySelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.webmaic.example.util.BaiduEntryUtil;
import org.webmaic.example.util.InputUtil;
import org.webmaic.example.util.WebdriverUtil;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selector;
import us.codecraft.webmagic.selector.Selectors;
import us.codecraft.webmagic.selector.XpathSelector;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;


/**
 * chromeDriver是谷歌的浏览器驱动，用来适配Selenium,有图形页面存在，在调试爬虫下载运行的功能的时候会相对方便
 *
 * @author zr8657
 * @date 10.22.2018
 */
public class GoodsList {
    private static final int SLEEP_TIME = (int) (1000 + Math.random() * 1000);

    public static void main(String[] args) throws Exception {

        WebdriverUtil.fireFoxWebdriver();
        WebDriver driver = new FirefoxDriver();
        // WebDriver driver = new ChromeDriver();

        driver = BaiduEntryUtil.baiduJumpTmall(driver);
        Thread.sleep(SLEEP_TIME);
        //等待搜索完成
        WebDriverWait baiduWait = new WebDriverWait(driver, 10, 1);
        baiduWait.until(new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver text) {
                return text.findElement(By.id("mq"));
            }
        });
        //开始操作天猫
        WebElement searchBox = driver.findElement(By.id("mq"));
        InputUtil.chineseInput(searchBox, "投影仪");

        WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"mallSearch\"]/form/fieldset/div/button"));
        Thread.sleep(SLEEP_TIME);
        searchButton.submit();
        //等待搜索完成
        WebDriverWait wait = new WebDriverWait(driver, 10, 1);
        wait.until(new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver text) {
                return text.findElement(By.id("J_ItemList"));
            }
        });
        Thread.sleep(SLEEP_TIME);

        //移动到指定的坐标(相对当前的坐标移动)
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 700)");

        //解析并获取所有商品的url
        Html html = new Html(driver.getPageSource());
        List<String> goodsList = html.xpath("//*[@id=\"J_ItemList\"]").links().regex("^//detail.*").all();
        LinkedHashSet<String> set = new LinkedHashSet<String>(goodsList.size());
        set.addAll(goodsList);
        goodsList.clear();
        goodsList.addAll(set);
        for (String s:goodsList){
            s="https:" + s;
            System.out.println(s);
        }


        driver.close();
    }


}