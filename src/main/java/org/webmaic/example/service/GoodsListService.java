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
    private static final int SLEEP_TIME = (int) (3000 + Math.random() * 1000);
    private static final int ROLL_LENGTH = (int) (Math.random() * 1000);
    @Autowired
    RedisUtil redisUtil;

    public void getUrlList(WebDriver driver) throws Exception{

        Thread.sleep(SLEEP_TIME);

        //存储本页url
        this.obtainGoodsList(driver.getPageSource());

        //解析商品列表并存储
        int frequency = 16;
        for (int i=0;i<frequency;i++){
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
           // System.out.println(s);
        }
        //存储goodsurl
        redisUtil.set("goodsUrlList".getBytes(), SerializeUtil.serialize(goodsList),0);
    }
}
