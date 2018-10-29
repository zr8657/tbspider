package org.webmaic.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.webmaic.example.util.WebdriverUtil;

public class L_88_Merge_Sorted_Array {
    public static void main(String[] arg0) {
        String a = "abcde";
        String b = "cdeab";
        System.out.println(rotateString(a, b));
    }

    public static boolean rotateString(String A, String B) {
        if (A.length() != B.length()) {
            return false;
        }
        A += A;
        if (A.indexOf(B) == -1) {
            return false;
        }
        return true;
    }
}
