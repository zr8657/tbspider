package org.webmaic.example.util;

import org.openqa.selenium.WebDriver;

/**
 * 切换TAB页
 */
public class SwitchPage {

    /**
     * 用于有两个TB页情况，关闭当前TAB页并将句柄切换到下一个TAB页
     * @param driver
     * @return
     */
    public static WebDriver deleteSwitchTwoPage(WebDriver driver){
        // 获取当前页面句柄
        String handle = driver.getWindowHandle();
        System.out.println("当前句柄为："+handle);
        // 获取所有页面的句柄，并循环判断不是当前的句柄
        for (String temhandle : driver.getWindowHandles()) {
            if(!temhandle.equals(handle)){
                driver.close();
            }
            driver.switchTo().window(temhandle);
            System.out.println("句柄已经切换到："+temhandle+" 当前窗口："+driver.getTitle());
        }
        return driver;
    }
    /**
     * 用于有两个TB页情况，将句柄切换到下一个TAB页
     * @param driver
     * @return
     */
    public static WebDriver switchTwoPage(WebDriver driver){
        // 获取当前页面句柄
        String handle = driver.getWindowHandle();
        System.out.println("当前句柄为："+handle);
        // 获取所有页面的句柄，并循环判断是否为当前的句柄
        for (String temhandle : driver.getWindowHandles()) {
            if(temhandle.equals(handle)){
                continue;
            }
            driver.switchTo().window(temhandle);
            System.out.println("句柄已经切换到："+temhandle+" 当前窗口："+driver.getTitle());
        }
        return driver;
    }
}
