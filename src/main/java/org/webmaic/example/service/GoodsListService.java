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
        logger.info("当前窗口为：" + driver.getTitle().trim());

        //如果出现登录
        if (driver.getTitle().trim().equals("天猫tmall.com--理想生活上天猫")) {
            loginUtil.taobaoLogin(driver);
            logger.info("登录完毕");
            Thread.sleep(SLEEP_TIME * 3);
            driver.get(redisUtil.get("lastListUrl", 0));
            logger.info("网址跳转完毕");
            Thread.sleep(SLEEP_TIME * 3);
            //下拉七次
            for (int i = 0; i < 5; i++) {
                ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, " + ROLL_LENGTH + ")");
                logger.info("下移了第"+i+"次");
                Thread.sleep(SLEEP_TIME);
            }
            logger.info("下拉完毕,接下来点击下一步");
            //进入下一页
            WebElement tmallLink = driver.findElement(By.linkText("下一页>>"));
            Thread.sleep(10000);
            tmallLink.click();
        } else {
            String lastListUrl = driver.getCurrentUrl();
            redisUtil.set("lastListUrl", lastListUrl, 0);
            Thread.sleep(SLEEP_TIME);
            logger.info("执行的else");
        }

        //下移次数
        int rollCount = 7;
        for (int i = 0; i < rollCount; i++) {
            Thread.sleep(SLEEP_TIME*2);
            logger.info("下移了第"+i+"次");
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, " + ROLL_LENGTH + ")");
        }

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
