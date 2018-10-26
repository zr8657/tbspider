package org.webmaic.example.controller;

import org.openqa.selenium.By;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.webmaic.example.service.GoodsListService;
import org.webmaic.example.util.*;

import java.util.ArrayList;


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

    @ResponseBody
    @RequestMapping("/getGoods")
    public String getGoods(Integer pageNum) {

        WebdriverUtil.fireFoxWebdriver();
        WebDriver driver = new FirefoxDriver();
        // WebDriver driver = new ChromeDriver();

        try {
            //已经获取天猫商品列表
            driver = BaiduEntryUtil.baiduJumpTmall(driver);

            //获取ipList
           ArrayList<String> urlList = TextUtil.textReader("ipList.txt");

            //爬取页数
            pageNum = pageNum == null ? 20 : pageNum;
            for (int i = 0; i < pageNum; i++) {
                goodsListService.getUrlList(driver);
                WebElement tmallLink = driver.findElement(By.linkText("下一页>>"));

                //切换ip
                ArrayList<String> list = TextUtil.textReader("ipList.txt");
                System.getProperties().setProperty("http.proxyHost",list.get(0) );
                System.getProperties().setProperty("http.proxyPort", "80");
                list.remove(0);

                //点击下一页
                tmallLink.click();
                logger.info("第"+i+"页获取完毕，60秒后进入下一页");
                Thread.sleep(60000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.close();
        }
        return "爬取商品列表成功";
    }


}