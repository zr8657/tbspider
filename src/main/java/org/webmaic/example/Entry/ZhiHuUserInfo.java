package org.webmaic.example.Entry;

import org.webmaic.example.model.DataSource;
import org.webmaic.example.model.ExtractAssessDetail;
import org.webmaic.example.util.DateUtil;
import org.webmaic.example.util.MatcherUtil;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.JsonPathSelector;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ZhiHuUserInfo implements PageProcessor {
    public static String goodsId = new String();

    private Site site = Site.me()
            .setRetryTimes(3)
            .setSleepTime(1000)
            .setCharset("UTF-8")
            .addHeader("accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
            .addHeader("accept-language","zh-CN,zh;q=0.9")
            .addHeader("Connection","keep-alive")
            .addHeader("Content-Type","charset=UTF-8")
            .addHeader("Referer","https://s.taobao.com/search?q=%E6%8A%95%E5%BD%B1%E4%BB%AA&imgfile=&commend=all&ssid=s5-e&search_type=item&sourceId=tb.index&spm=a21bo.2017.201856-taobao-item.1&ie=utf8&initiative_id=tbindexz_20170306")
            .addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.84 Safari/537.36")
            .addHeader("X-Requested-With","XMLHttpRequest");


    @Override
    public void process(Page page) {
        String  handleRawText = page.getRawText();
        System.out.println(handleRawText);
    }


    @Override
    public Site getSite() {
        return site;
    }


    public static void main(String[] arg0){
                Spider.create(new ZhiHuUserInfo())
                .addUrl("https://www.zhihu.com/people/excited-vczh/answers")
                .run();
    }
}
