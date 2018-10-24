package org.webmaic.example.util;

public class WebdriverUtil {
    /**
     * fireFox:53.0.3
     * selenium:3.14
     * geckodriver:0.17
     */
    public static void fireFoxWebdriver(){
        System.setProperty("webdriver.firefox.bin","D:\\Mozilla Firefox\\firefox.exe");
        System.setProperty("webdriver.gecko.driver","src\\main\\resources\\geckodriver.exe");
    }
    /**
     * chrome:68.0.3440.84
     * selenium:3.14
     * chromedriver:忘了
     * chrome会被检测到window.navigator.webdriver
     */
    public static void chromeWebdriver(){
        System.setProperty("webdriver.chrome.driver","src\\main\\resources\\chromedriver.exe");
    }
}
