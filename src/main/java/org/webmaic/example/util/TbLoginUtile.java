package org.webmaic.example.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TbLoginUtile {
    private static final int SLEEP_TIME = (int) (1000 + Math.random() * 1000);

    public static void login(WebDriver driver) throws Exception{
        driver.get("https://login.tmall.com/");
        driver.manage().window().maximize();

        Thread.sleep(SLEEP_TIME*3);

        //切换到子html
        driver.switchTo().frame(driver.findElement(By.id("J_loginIframe")));

        //显式等待， 针对某个元素等待
        WebDriverWait wait = new WebDriverWait(driver,10,1);
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
        InputUtil.chineseInput(accounts, "");
        Thread.sleep(SLEEP_TIME);

        //输入密码
        WebElement password = driver.findElement(By.id("TPL_password_1"));
        //点击输入框
        password.click();
        //输入
        InputUtil.chineseInput(password, "");
        Thread.sleep(SLEEP_TIME);
        //点击登录
        WebElement tmSearchButton = driver.findElement(By.xpath("//*[@id=\"J_SubmitStatic\"]"));
        Thread.sleep(SLEEP_TIME);
        tmSearchButton.click();
        //切换回默认frame
        driver.switchTo().defaultContent();
        Thread.sleep(SLEEP_TIME*3);


    }
}
