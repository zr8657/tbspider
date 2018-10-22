package org.webmaic.example.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * 百度进入其他网页
 */
public class BaiduEntryUtil {

    //跳天猫
    public static WebDriver baiduJumpTmall(WebDriver driver){
        // 让浏览器访问 Baidu
        driver.get("https://www.baidu.com/");
        //从百度进入淘宝
        WebElement searchBox = driver.findElement(By.id("kw"));
        searchBox.sendKeys("天猫");
        WebElement searchButton = driver.findElement(By.id("su"));
        searchButton.submit();

        //显式等待， 针对某个元素等待
        WebDriverWait wait = new WebDriverWait(driver,10,1);
        wait.until(new ExpectedCondition<WebElement>(){
            @Override
            public WebElement apply(WebDriver text) {
                return text.findElement(By.linkText("天猫tmall.com--理想生活上天猫"));
            }
        });

        WebElement tmallLink = driver.findElement(By.linkText("天猫tmall.com--理想生活上天猫"));
        tmallLink.click();

        driver = SwitchPage.deleteSwitchTwoPage(driver);

        return driver;
    }
}
