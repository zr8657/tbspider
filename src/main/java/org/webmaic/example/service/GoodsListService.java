package org.webmaic.example.service;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webmaic.example.util.*;
import us.codecraft.webmagic.selector.Html;

import java.util.LinkedHashSet;
import java.util.List;

@Service
public class GoodsListService {
    private static final int SLEEP_TIME = (int) (1000 + Math.random() * 1000);
    private static final int ROLL_LENGTH = (int) (Math.random() * 1000);
    @Autowired
    RedisUtil redisUtil;

    public void getUrlList(WebDriver driver) throws Exception{

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
        //点击搜索按钮
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

        //存储本页url
        this.obtainGoodsList(driver.getPageSource());



        //解析商品列表并存储
        int pageNum = 20;
        for (int i=0;i<pageNum;i++){
            //移动到指定的坐标(相对当前的坐标移动)
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, "+ROLL_LENGTH+")");
            Thread.sleep(SLEEP_TIME);
        }


    }

    private void obtainGoodsList(String pageContent){
        //解析并获取所有商品的url
        Html html = new Html(pageContent);
        List<String> goodsList = html.xpath("//*[@id=\"J_ItemList\"]").links().regex("^//detail.*").all();
        LinkedHashSet<String> set = new LinkedHashSet<String>(goodsList.size());
        set.addAll(goodsList);
        goodsList.clear();
        goodsList.addAll(set);
        for (String s:goodsList){
            s="https:" + s;
            System.out.println(s);
        }
        //存储goodsurl
        redisUtil.set("goodsUrlList".getBytes(), SerializeUtil.serialize(goodsList),0);
    }
}
