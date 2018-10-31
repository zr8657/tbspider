//package org.webmaic.example.service;
//
//import org.openqa.selenium.*;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.webmaic.example.util.loginUtil;
//import org.webmaic.example.util.TextUtil;
//import org.webmaic.example.util.WebdriverUtil;
//
//import java.util.ArrayList;
//import java.util.Set;
//
//public class CookieAgentService {
//    private static final int SLEEP_TIME = (int) (1000 + Math.random() * 1000);
//
//    public void cookieAgent() throws Exception {
//        WebdriverUtil.fireFoxWebdriver();
//        WebDriver driver = new FirefoxDriver();
//        //登录
//        loginUtil.tmallLogin(driver);
//
//        //存cookie
//        Set<Cookie> cookies = driver.manage().getCookies();
//        StringBuilder sb = new StringBuilder();
//        for (Cookie cookie : cookies) {
//            sb.append(cookie.getName() + ":" + cookie.getValue()+";");
//            System.out.println(cookie.getName() + "value :" + cookie.getValue());
//        }
//        ArrayList<String> list = new ArrayList<>();
//        list.add(sb.toString());
//        TextUtil.textWriter("cookies.txt",list);
//    }
//
//    public static void main(String[] arg0) throws Exception {
//        CookieAgentService cas = new CookieAgentService();
//        cas.cookieAgent();
//    }
//}
