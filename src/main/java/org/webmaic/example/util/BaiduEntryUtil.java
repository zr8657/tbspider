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
    private static final int SLEEP_TIME = (int) (2000 + Math.random() * 1000);

    //跳天猫
    public static WebDriver baiduJumpTmall(WebDriver driver) throws Exception{
        // 让浏览器访问 Baidu
        driver.get("https://www.baidu.com/");
        driver.manage().window().maximize();
        //从百度进入淘宝
        WebElement searchBox = driver.findElement(By.id("kw"));
        searchBox.sendKeys("天猫");
        WebElement searchButton = driver.findElement(By.id("su"));
        searchButton.submit();
        Thread.sleep(SLEEP_TIME);
        //显式等待， 针对某个元素等待
        WebDriverWait wait = new WebDriverWait(driver,10,1);
        wait.until(new ExpectedCondition<WebElement>(){
            @Override
            public WebElement apply(WebDriver text) {
                return text.findElement(By.linkText("天猫tmall.com--理想生活上天猫"));
            }
        });
        //标签页切换
        WebElement tmallLink = driver.findElement(By.linkText("天猫tmall.com--理想生活上天猫"));
        tmallLink.click();
        //关闭第一页并切换到第二页
        driver = SwitchPage.deleteSwitchTwoPage(driver);

        Thread.sleep(SLEEP_TIME);

        //等待出现天猫搜索框
        WebDriverWait baiduWait = new WebDriverWait(driver, 10, 1);
        baiduWait.until(new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver text) {
                return text.findElement(By.id("mq"));
            }
        });

        //开始操作天猫
        WebElement tmSearchBox = driver.findElement(By.id("mq"));
        InputUtil.chineseInput(tmSearchBox, "投影仪");
        //点击搜索按钮
        WebElement tmSearchButton = driver.findElement(By.xpath("//*[@id=\"mallSearch\"]/form/fieldset/div/button"));
        Thread.sleep(SLEEP_TIME);
        tmSearchButton.submit();

        //等待搜索完成
        WebDriverWait tmWait = new WebDriverWait(driver, 60, 10);
        wait.until(new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver text) {
                return text.findElement(By.id("J_ItemList"));
            }
        });
        return driver;
    }
}
