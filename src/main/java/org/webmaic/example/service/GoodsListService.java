package org.webmaic.example.service;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webmaic.example.util.*;
import us.codecraft.webmagic.selector.Html;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;

@Service
public class GoodsListService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final int SLEEP_TIME = new Random().nextInt(6000) + 8000;
    private static final int ROLL_LENGTH = new Random().nextInt(500) + 500;

    @Autowired
    RedisUtil redisUtil;

    public void getUrlList(WebDriver driver) throws Exception {

        Thread.sleep(SLEEP_TIME);
        logger.info("当前窗口为："+driver.getTitle().trim());

        //如果出现登录
        if (driver.getTitle().trim().equals("天猫tmall.com--理想生活上天猫")) {
            loginUtil.taobaoLogin(driver);
            Thread.sleep(SLEEP_TIME);
            driver.get(redisUtil.get("lastListUrl",0));
            Thread.sleep(SLEEP_TIME);
            //下拉七次
           for (int i =0;i<7;i++){
               ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, " + ROLL_LENGTH + ")");
               Thread.sleep(SLEEP_TIME);
           }
            return;
        }else{
            String lastListUrl = driver.getCurrentUrl();
            redisUtil.set("lastListUrl",lastListUrl,0);
            Thread.sleep(SLEEP_TIME);
        }
        //显式等待， 针对某个元素等待
        WebDriverWait wait = new WebDriverWait(driver,100,5);
        wait.until(new ExpectedCondition<WebElement>(){
            @Override
            public WebElement apply(WebDriver test) {
                //移动到指定的坐标(相对当前的坐标移动)
                ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, " + ROLL_LENGTH + ")");
                //检查"下一页"是否存在
                return test.findElement(By.linkText("下一页>>"));
            }
        });

        Thread.sleep(SLEEP_TIME);

        //存储本页url
        this.obtainGoodsList(driver.getPageSource());

        Thread.sleep(SLEEP_TIME);
    }

    private void obtainGoodsList(String pageContent) {
        //解析并获取所有商品的url
        Html html = new Html(pageContent);
        List<String> goodsList = html.xpath("//*[@id=\"J_ItemList\"]").links().regex("^//detail.*").all();
        LinkedHashSet<String> set = new LinkedHashSet<String>(goodsList.size());
        set.addAll(goodsList);
        goodsList.clear();
        goodsList.addAll(set);
        for (String s : goodsList) {
            s = "https:" + s;
            // System.out.println(s);
        }
        //存储goodsurl
        redisUtil.set("goodsUrlList".getBytes(), SerializeUtil.serialize(goodsList), 0);
        logger.info("本页url存储成功");
    }
}
