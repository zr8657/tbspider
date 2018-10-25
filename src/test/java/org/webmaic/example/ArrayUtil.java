package org.webmaic.example;

public class ArrayUtil {

    /**
     * 生成10个随机数字的数组
     * @return
     */
    public static int[] generatingArray(){
        int n[]=new int[10];
        for(int i=0;i<10;i++)
        {
            n[i]=(int) ( Math.random() *100 );
        }
        return n;
    }

    public static void sysOutArray(int temp[]){
        for(int i=0;i<10;i++)
        {
           System.out.print(temp[i]+",");
        }
        System.out.println();
    }
}
