package org.webmaic.example;

import org.hibernate.validator.constraints.SafeHtml;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.webmaic.example.util.WebdriverUtil;

import java.util.HashSet;

public class L_414_Third_Maximum_Number {
    public static void main(String[] arg0) {
        int nums[] = {2,2,3,1};
        System.out.println(thirdMax(nums));
    }

    public static int thirdMax(int[] nums) {
        HashSet<Integer> hs = new HashSet();
        for (int i =0;i<nums.length;i++){
            hs.add(nums[i]);
        }

        for (Integer temp:hs){

        }
        return 0;
    }
}
