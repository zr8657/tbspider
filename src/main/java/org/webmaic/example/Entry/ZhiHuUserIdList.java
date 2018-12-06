package org.webmaic.example.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.webmaic.example.model.DataSource;
import org.webmaic.example.model.ExtractGoodsArgs;
import org.webmaic.example.util.MatcherUtil;
import org.webmaic.example.util.RedisUtil;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.JsonPathSelector;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ZhiHuUserIdList implements PageProcessor {
    @Autowired
    RedisUtil redisUtil;

    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me()
            .setRetryTimes(3)
            .setSleepTime(1000)
            .addHeader("Accept","application/json, text/javascript, */*; q=0.01")

            .addHeader("Accept-Language","zh-CN,zh;q=0.9")
            .addHeader("Connection","keep-alive")
            .addHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8")
            .addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.84 Safari/537.36")
            .addHeader("X-Requested-With","XMLHttpRequest");


    @Override
    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {
        String handleRawText = page.getRawText();
        List<String> idList = new JsonPathSelector("$.data[*].url_token").selectList(handleRawText);
        for (String s : idList) {
            //把待爬取的存入redis仓库3
            redisUtil.set(s,s,3);
        }
        //总数：paging.totals
    }

    @Override
    public Site getSite() {
        return site;
    }

}
