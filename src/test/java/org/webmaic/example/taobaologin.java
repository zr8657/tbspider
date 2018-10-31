package org.webmaic.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.webmaic.example.util.InputUtil;
import org.webmaic.example.util.WebdriverUtil;

public class taobaologin {
    private static final int SLEEP_TIME = (int) (1000 + Math.random() * 1000);

    public static void main(String[] arg0) throws Exception{
        WebdriverUtil.fireFoxWebdriver();
        WebDriver driver = new FirefoxDriver();
             driver.get("https://tmallLogin.taobao.com/member/tmallLogin.jhtml?redirectURL=http://list.tmall.com/search_product.htm?spm=a220m.1000858.0.0.6ef61b21BIEZoD&s=119&q=%CD%B6%D3%B0%D2%C7&sort=s&style=g&from=..pc_1_searchbutton&active=2&type=pc#J_Filter");
            driver.manage().window().maximize();

        Thread.sleep(SLEEP_TIME*3);

        //切换到子html
//        driver.switchTo().frame(driver.findElement(By.id("J_loginIframe")));

        //显式等待， 针对某个元素等待
        WebDriverWait wait = new WebDriverWait(driver,60,10);
        wait.until(new ExpectedCondition<WebElement>(){
            @Override
            public WebElement apply(WebDriver text) {
                return text.findElement(By.xpath("//*[@id=\"J_Quick2Static\"]"));
            }
        });

        Thread.sleep(SLEEP_TIME);

        WebElement loginPage = driver.findElement(By.xpath("//*[@id=\"J_Quick2Static\"]"));
        loginPage.click();
        Thread.sleep(SLEEP_TIME);

        //输入账号
        WebElement accounts = driver.findElement(By.id("TPL_username_1"));
        Thread.sleep(SLEEP_TIME);
        //点击输入框
        accounts.click();
        //输入
        Thread.sleep(SLEEP_TIME);
        InputUtil.chineseInput(accounts, "全TM被用了");
        Thread.sleep(SLEEP_TIME);

        //输入密码
        WebElement password = driver.findElement(By.id("TPL_password_1"));
        //点击输入框
        password.click();
        //输入
        InputUtil.chineseInput(password, "hxfzr1314521");
        Thread.sleep(SLEEP_TIME);
        //点击登录
        WebElement tmSearchButton = driver.findElement(By.xpath("//*[@id=\"J_SubmitStatic\"]"));
        Thread.sleep(SLEEP_TIME);
        tmSearchButton.click();
        Thread.sleep(SLEEP_TIME);
        //切换回默认frame
    //    driver.switchTo().defaultContent();
        Thread.sleep(SLEEP_TIME*3);
        driver.close();
    }
}
