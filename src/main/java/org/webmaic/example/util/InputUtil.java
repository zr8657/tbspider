package org.webmaic.example.util;

import org.openqa.selenium.WebElement;

public class InputUtil {
    private static final int SLEEP_TIME = (int)(Math.random()*1000);

    public static WebElement chineseInput(WebElement we, String s) throws Exception{
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            we.sendKeys(c + "");
            Thread.sleep(SLEEP_TIME);
        }
        return we;
    }
}
