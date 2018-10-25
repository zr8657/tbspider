package org.webmaic.example.controller;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.webmaic.example.service.GoodsListService;
import org.webmaic.example.util.*;
import us.codecraft.webmagic.selector.Html;

import java.util.LinkedHashSet;
import java.util.List;


/**
 * chromeDriver是谷歌的浏览器驱动，用来适配Selenium,有图形页面存在，在调试爬虫下载运行的功能的时候会相对方便
 *
 * @author zr8657
 * @date 10.22.2018
 */
@Controller
public class GoodsListController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    GoodsListService goodsListService;

    @RequestMapping("/getGoods")
    public String getGoods() throws Exception {
        WebdriverUtil.fireFoxWebdriver();
        WebDriver driver = new FirefoxDriver();
        // WebDriver driver = new ChromeDriver();
        try {
            int pageNum = 20;
            for (int i =0;i<pageNum;i++){
                goodsListService.getUrlList(driver);
                WebElement tmallLink = driver.findElement(By.linkText("下一页"));

                //切换IP点击下一页

                tmallLink.click();
                Thread.sleep(20000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.close();
        }
        return "爬取商品列表成功";
    }


}