package org.webmaic.example.controller;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.webmaic.example.pojo.ResponseData;
import org.webmaic.example.service.ZhiHuUserInfoService;
import org.webmaic.example.util.RedisUtil;

@RestController
public class ZhiHuUserController {

    @Autowired
    ZhiHuUserInfoService zhiHuUserInfoService;
    @Autowired
    RedisUtil redisUtil;

    @RequestMapping("/getZhihuUser")
    public ResponseData getZhihuUser(){
        ResponseData responseData = new ResponseData();
        try {
            //zhiHuUserInfoService.getUserInfoService("hydro-ding");
            //待爬取库不为空就爬
            while (redisUtil.randomkey(3) != null){
                String userId = redisUtil.randomkey(3);
                zhiHuUserInfoService.getUserInfoService(userId);
                //爬完删掉
                redisUtil.del(3,userId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseData;
    }
}
